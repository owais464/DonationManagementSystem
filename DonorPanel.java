import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DonorPanel extends JFrame {
    private Donor donor;
    private JTabbedPane tabbedPane;

    public DonorPanel(Donor donor) {
        this.donor = donor;
        setTitle("Donor Dashboard - " + donor.getName());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        
        // Add tabs
        tabbedPane.addTab("Profile", createProfilePanel());
        tabbedPane.addTab("Make Donation", createDonationPanel());
        tabbedPane.addTab("View History", new HistoryPanel(donor.getPhone(), "donor"));
        tabbedPane.addTab("Urgent Requests", new UrgentRequestPanel(donor.getPhone()));

        add(tabbedPane);
    }

    private JPanel createProfilePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Name: " + donor.getName()), gbc);

        gbc.gridy = 1;
        panel.add(new JLabel("Phone: " + donor.getPhone()), gbc);

        gbc.gridy = 2;
        panel.add(new JLabel("Role: " + donor.getRole()), gbc);

        return panel;
    }

    private JPanel createDonationPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Make a Donation");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        panel.add(new JLabel("Item to donate:"), gbc);
        gbc.gridx = 1;
        JTextField itemField = new JTextField(20);
        panel.add(itemField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Quantity:"), gbc);
        gbc.gridx = 1;
        JTextField quantityField = new JTextField(20);
        panel.add(quantityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Monetary donation amount:"), gbc);
        gbc.gridx = 1;
        JTextField amountField = new JTextField(20);
        panel.add(amountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Aadhar Number:"), gbc);
        gbc.gridx = 1;
        JTextField aadharField = new JTextField(20);
        panel.add(aadharField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        JTextField emailField = new JTextField(20);
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        JButton donateButton = new JButton("Submit Donation");
        donateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = itemField.getText().trim();
                String quantity = quantityField.getText().trim();
                String amount = amountField.getText().trim();
                String aadhar = aadharField.getText().trim();
                String email = emailField.getText().trim();

                if (item.isEmpty() && amount.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Please enter either item or amount to donate", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Donation donation = new Donation(donor.getPhone(), item, quantity, amount, aadhar, email);
                FileHandler.saveDonation(donation);
                JOptionPane.showMessageDialog(panel, "Donation submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                // Clear fields
                itemField.setText("");
                quantityField.setText("");
                amountField.setText("");
                aadharField.setText("");
                emailField.setText("");
            }
        });
        panel.add(donateButton, gbc);

        return panel;
    }
}