import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SwingTableExample extends JFrame {

    private int totalNumber = 0;
    private JLabel totalNumberLabel;
    private JTextField customNumberField;

    public SwingTableExample() {
        super("Swing Table Example");
        createUI();
    }

    private void createUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new BorderLayout());

        // Panel for '+20' and '-20' buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

        JButton addButton = new JButton("+20");
        addButton.addActionListener(e -> updateTotal(20));

        JButton subtractButton = new JButton("-20");
        subtractButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Do you want to subtract 20?", "Confirm Subtraction", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                updateTotal(-20);
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(subtractButton);

        // Total number label at the bottom right
        totalNumberLabel = new JLabel("Total Number: 0", JLabel.RIGHT);

        // Text field for custom number input
        JPanel customNumberPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Write a number:");
        customNumberField = new JTextField();
        customNumberField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String text = customNumberField.getText();
                    if (!text.isEmpty()) {
                        try {
                            int number = Integer.parseInt(text);
                            updateTotal(number); // Directly update total number with entered value
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Invalid number");
                        }
                        customNumberField.setText(""); // Clear the field after processing
                    }
                }
            }
        });

        customNumberPanel.add(label, BorderLayout.NORTH);
        customNumberPanel.add(customNumberField, BorderLayout.CENTER);

        add(buttonPanel, BorderLayout.NORTH);
        add(totalNumberLabel, BorderLayout.SOUTH);
        add(customNumberPanel, BorderLayout.CENTER);

        // Remove global 'Enter' key press binding to prevent it from adding +20
    }

    private void updateTotal(int value) {
        totalNumber += value;
        totalNumberLabel.setText("Total Number: " + totalNumber);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SwingTableExample frame = new SwingTableExample();
            frame.setVisible(true);
        });
    }
}
