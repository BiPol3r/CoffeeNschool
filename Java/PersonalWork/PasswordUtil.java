import java.security.SecureRandom;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;

public class PasswordUtil {
    private static final SecureRandom RAND = new SecureRandom();
    private static final int SALT_LEN = 16;       // bytes
    private static final int ITERATIONS = 65536;  // PBKDF2 iterations (reasonable default)
    private static final int KEY_LEN = 256;       // bits

    public static byte[] generateSalt() {
        byte[] s = new byte[SALT_LEN];
        RAND.nextBytes(s);
        return s;
    }

    public static String toBase64(byte[] b) {
        return Base64.getEncoder().encodeToString(b);
    }

    public static byte[] fromBase64(String s) {
        return Base64.getDecoder().decode(s);
    }

    public static String hashPasswordToBase64(String password, byte[] salt) throws Exception {
        byte[] hash = pbkdf2(password.toCharArray(), salt, ITERATIONS, KEY_LEN);
        return toBase64(hash);
    }

    public static boolean verifyPassword(String password, String storedHashB64, String storedSaltB64) throws Exception {
        byte[] salt = fromBase64(storedSaltB64);
        byte[] expected = fromBase64(storedHashB64);
        byte[] actual = pbkdf2(password.toCharArray(), salt, ITERATIONS, KEY_LEN);
        if (actual.length != expected.length) return false;
        int diff = 0;
        for (int i = 0; i < actual.length; i++) diff |= actual[i] ^ expected[i];
        return diff == 0;
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int keyLength) throws Exception {
        KeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        return f.generateSecret(spec).getEncoded();
    }
}
