import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {

    // Components
    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] operatorButtons;
    private JButton addButton, subButton, mulButton, divButton;
    private JButton decButton, equButton, delButton, clrButton, negButton;

    // Variables for calculation
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public Calculator() {
        // Initialize components
        initializeComponents();
        setupLayout();
        setupEventHandlers();

        // Frame settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(420, 550);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle("Calculator");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initializeComponents() {
        // Display field
        display = new JTextField();
        display.setBounds(50, 25, 300, 50);
        display.setFont(new Font("Arial", Font.BOLD, 20));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setText("0");

        // Number buttons (0-9)
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 18));
            numberButtons[i].setFocusable(false);
        }

        // Operator buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");
        negButton = new JButton("(-)");

        operatorButtons = new JButton[]{
                addButton, subButton, mulButton, divButton,
                decButton, equButton, delButton, clrButton, negButton
        };

        // Set font and properties for operator buttons
        for (JButton button : operatorButtons) {
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setFocusable(false);
        }
    }

    private void setupLayout() {
        // Add display to frame
        this.add(display);

        // Button layout (4x4 grid for numbers and operations)
        // Row 1: Clr, Del, (-), /
        clrButton.setBounds(50, 100, 60, 60);
        delButton.setBounds(120, 100, 60, 60);
        negButton.setBounds(190, 100, 60, 60);
        divButton.setBounds(260, 100, 60, 60);

        // Row 2: 7, 8, 9, *
        numberButtons[7].setBounds(50, 170, 60, 60);
        numberButtons[8].setBounds(120, 170, 60, 60);
        numberButtons[9].setBounds(190, 170, 60, 60);
        mulButton.setBounds(260, 170, 60, 60);

        // Row 3: 4, 5, 6, -
        numberButtons[4].setBounds(50, 240, 60, 60);
        numberButtons[5].setBounds(120, 240, 60, 60);
        numberButtons[6].setBounds(190, 240, 60, 60);
        subButton.setBounds(260, 240, 60, 60);

        // Row 4: 1, 2, 3, +
        numberButtons[1].setBounds(50, 310, 60, 60);
        numberButtons[2].setBounds(120, 310, 60, 60);
        numberButtons[3].setBounds(190, 310, 60, 60);
        addButton.setBounds(260, 310, 60, 60);

        // Row 5: 0 (wider), ., =
        numberButtons[0].setBounds(50, 380, 130, 60);
        decButton.setBounds(190, 380, 60, 60);
        equButton.setBounds(260, 380, 60, 60);

        // Add all components to frame
        for (JButton button : numberButtons) {
            this.add(button);
        }
        for (JButton button : operatorButtons) {
            this.add(button);
        }
    }

    private void setupEventHandlers() {
        // Add action listeners for number buttons
        for (int i = 0; i < 10; i++) {
            numberButtons[i].addActionListener(this);
        }

        // Add action listeners for operator buttons
        for (JButton button : operatorButtons) {
            button.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle number button clicks
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                if (display.getText().equals("0")) {
                    display.setText(String.valueOf(i));
                } else {
                    display.setText(display.getText().concat(String.valueOf(i)));
                }
            }
        }

        // Handle decimal point
        if (e.getSource() == decButton) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText().concat("."));
            }
        }

        // Handle arithmetic operators
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '+';
            display.setText("");
        }

        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '-';
            display.setText("");
        }

        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '*';
            display.setText("");
        }

        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '/';
            display.setText("");
        }

        // Handle equals button
        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(display.getText());

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }

            // Display result
            if (result == (long) result) {
                display.setText(String.valueOf((long) result));
            } else {
                display.setText(String.valueOf(result));
            }

            num1 = result;
        }

        // Handle clear button
        if (e.getSource() == clrButton) {
            display.setText("0");
            num1 = 0;
            num2 = 0;
            result = 0;
        }

        // Handle delete button
        if (e.getSource() == delButton) {
            String str = display.getText();
            if (str.length() > 1) {
                display.setText(str.substring(0, str.length() - 1));
            } else {
                display.setText("0");
            }
        }

        // Handle negative button
        if (e.getSource() == negButton) {
            double temp = Double.parseDouble(display.getText());
            temp *= -1;
            if (temp == (long) temp) {
                display.setText(String.valueOf((long) temp));
            } else {
                display.setText(String.valueOf(temp));
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}