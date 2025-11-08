import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUICalculator extends JFrame implements ActionListener {
    private JTextField textField;
    private String operator;
    private double num1, num2, result;

    public GUICalculator() {
        setTitle("Java GUI Calculator");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        textField = new JTextField();
        textField.setBounds(30, 40, 270, 40);
        add(textField);

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        int x = 30, y = 100;
        for (int i = 0; i < buttons.length; i++) {
            JButton b = new JButton(buttons[i]);
            b.setBounds(x, y, 60, 40);
            b.addActionListener(this);
            add(b);
            x += 70;
            if ((i + 1) % 4 == 0) {
                x = 30;
                y += 50;
            }
        }

        JButton clearBtn = new JButton("C");
        clearBtn.setBounds(30, y, 270, 40);
        clearBtn.addActionListener(this);
        add(clearBtn);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
            textField.setText(textField.getText() + command);
        } else if (command.equals("C")) {
            textField.setText("");
            num1 = num2 = result = 0;
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/": 
                    if (num2 == 0) {
                        textField.setText("Error");
                        return;
                    }
                    result = num1 / num2; break;
            }
            textField.setText("" + result);
            operator = null;
        } else {
            operator = command;
            num1 = Double.parseDouble(textField.getText());
            textField.setText("");
        }
    }

    public static void main(String[] args) {
        new GUICalculator();
    }
}
