import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReceiverPanel extends JFrame {
    private Receiver receiver;
    private JTabbedPane tabbedPane;

    public ReceiverPanel(Receiver receiver) {
        this.receiver = receiver;
        setTitle("Receiver Dashboard - " + receiver.getName());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        
        // Add tabs
        tabbedPane.addTab("Profile", createProfilePanel());
        tabbedPane.addTab("Make Request", createRequestPanel());
        tabbedPane.addTab("View History", new HistoryPanel(receiver.getPhone(), "receiver"));

        add(tabbedPane);
    }

    private JPanel createProfilePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Name: " + receiver.getName()), gbc);

        gbc.gridy = 1;
        panel.add(new JLabel("Phone: " + receiver.getPhone()), gbc);

        gbc.gridy = 2;
        panel.add(new JLabel("Role: " + receiver.getRole()), gbc);

        return panel;
    }

    private JPanel createRequestPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Make a Request");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        panel.add(new JLabel("Item needed:"), gbc);
        gbc.gridx = 1;
        JTextField itemField = new JTextField(20);
        panel.add(itemField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Quantity needed:"), gbc);
        gbc.gridx = 1;
        JTextField quantityField = new JTextField(20);
        panel.add(quantityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Is this urgent?"), gbc);
        gbc.gridx = 1;
        JCheckBox urgentCheckbox = new JCheckBox("Yes");
        panel.add(urgentCheckbox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        JTextField addressField = new JTextField(20);
        panel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Aadhar Number:"), gbc);
        gbc.gridx = 1;
        JTextField aadharField = new JTextField(20);
        panel.add(aadharField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        JTextField emailField = new JTextField(20);
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Reason for request:"), gbc);
        gbc.gridx = 1;
        JTextArea reasonArea = new JTextArea(3, 20);
        panel.add(new JScrollPane(reasonArea), gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        JButton requestButton = new JButton("Submit Request");
        requestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = itemField.getText().trim();
                String quantity = quantityField.getText().trim();
                boolean urgent = urgentCheckbox.isSelected();
                String address = addressField.getText().trim();
                String aadhar = aadharField.getText().trim();
                String email = emailField.getText().trim();
                String reason = reasonArea.getText().trim();

                if (item.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Please enter the item you need", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Request request = new Request(receiver.getPhone(), item, quantity, urgent, address, aadhar, email, reason);
                FileHandler.saveRequest(request);
                JOptionPane.showMessageDialog(panel, "Request submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                // Clear fields
                itemField.setText("");
                quantityField.setText("");
                urgentCheckbox.setSelected(false);
                addressField.setText("");
                aadharField.setText("");
                emailField.setText("");
                reasonArea.setText("");
            }
        });
        panel.add(requestButton, gbc);

        return panel;
    }
}