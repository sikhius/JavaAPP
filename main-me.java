import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {

    private JTextField textField;

    private double firstNumber;
    private String operator;

    public Calculator() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        textField = new JTextField(10);
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.RIGHT);

        JPanel buttonsPanel = new JPanel(new GridLayout(4, 4));
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            buttonsPanel.add(button);
        }

        setLayout(new BorderLayout());
        add(textField, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String input = ((JButton) event.getSource()).getText();
            switch (input) {
                case "+":
                case "-":
                case "*":
                case "/":
                    operator = input;
                    firstNumber = Double.parseDouble(textField.getText());
                    textField.setText("");
                    break;
                case "=":
                    double secondNumber = Double.parseDouble(textField.getText());
                    double result = calculate(firstNumber, secondNumber, operator);
                    textField.setText(String.valueOf(result));
                    break;
                default:
                    textField.setText(textField.getText() + input);
                    break;
            }
        }
    }

    private double calculate(double firstNumber, double secondNumber, String operator) {
        switch (operator) {
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
                return firstNumber * secondNumber;
            case "/":
                return firstNumber / secondNumber;
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculator().setVisible(true);
            }
        });
    }
}
