import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Add2Numbers extends JFrame{
    private JButton button;
    private JTextField textField1, textField2;
    private JLabel label;
    private JPanel mainPanel, centerPanel, topPanel;

   public Add2Numbers(){

    initializeFrame();
    initializeComponents();
    panel();
    addToPanel();
    eventHandler();
    add(mainPanel);

    setVisible(true);
   }

        private void initializeFrame(){
            setTitle("Add Two Number");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            setLocationRelativeTo(null);
        }

        private void panel(){
            mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());
            topPanel = new JPanel();
            centerPanel = new JPanel();

            centerPanel.setLayout(new FlowLayout(20));
            topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

            centerPanel.setBackground(new Color(30,62,115));
            topPanel.setBackground(new Color(200, 200, 200));

            mainPanel.add(topPanel,BorderLayout.NORTH);
            mainPanel.add(centerPanel, BorderLayout.CENTER);

        }

        private void initializeComponents(){
            textField1 = new JTextField(15);
            textField2 = new JTextField(15);
            button = new JButton("Add");
            label = new JLabel("Result: ", SwingConstants.CENTER);
            label.setForeground(Color.WHITE);
        }

        private void addToPanel(){
            JLabel label1 = new JLabel("Enter First Number");
            label1.setForeground(Color.WHITE);
            centerPanel.add(label1);
            centerPanel.add(textField1);
            
            JLabel label2 = new JLabel("Enter Second Number");
            label2.setForeground(Color.WHITE);
            centerPanel.add(label2);
            centerPanel.add(textField2);

            centerPanel.add(button);
            centerPanel.add(label); 
        }

        private void performedAddition(){
            try{
                double num1 = Double.parseDouble(textField1.getText());
                double num2 = Double.parseDouble(textField2.getText());
                double sum = num1 + num2;
                label.setText("Result: " + sum);
            } catch(NumberFormatException ex){
                label.setText("Please Enter valid numbers!");
            }
        }

        private void eventHandler(){
            button.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    performedAddition();
                }
            });
        }
   public static void main(String[] args){
    SwingUtilities.invokeLater(() -> new Add2Numbers());
   }

}