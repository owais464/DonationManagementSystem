import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UrgentRequestPanel extends JPanel {
private String userPhone;

public UrgentRequestPanel(String userPhone) {
    this.userPhone = userPhone;
    setLayout(new BorderLayout());

    String[] columnNames = {"Item", "Quantity", "Address", "Reason"};
    List<Request> urgentRequests = FileHandler.getUrgentRequests();
    Object[][] data = new Object[urgentRequests.size()][4];

    for (int i = 0; i < urgentRequests.size(); i++) {
        Request request = urgentRequests.get(i);
        data[i][0] = request.getItem();
        data[i][1] = request.getQuantity();
        data[i][2] = request.getAddress();
        data[i][3] = request.getReason();
    }

    JTable table = new JTable(new DefaultTableModel(data, columnNames));
    JScrollPane scrollPane = new JScrollPane(table);
    add(scrollPane, BorderLayout.CENTER);

    if (!userPhone.equals("admin")) {
        JPanel buttonPanel = new JPanel();
        JButton donateButton = new JButton("Donate to Selected Request");
        donateButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                Request selectedRequest = urgentRequests.get(selectedRow);
                openDonationDialog(selectedRequest);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a request to donate", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonPanel.add(donateButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}

private void openDonationDialog(Request request) {
    JDialog dialog = new JDialog();
    dialog.setTitle("Donate to Request");
    dialog.setSize(400, 300);
    dialog.setModal(true);
    dialog.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    gbc.gridx = 0;
    gbc.gridy = 0;
    dialog.add(new JLabel("Item: " + request.getItem()), gbc);

    gbc.gridy = 1;
    dialog.add(new JLabel("Quantity: " + request.getQuantity()), gbc);

    gbc.gridy = 2;
    dialog.add(new JLabel("Your donation amount:"), gbc);
    gbc.gridx = 1;
    JTextField amountField = new JTextField(15);
    dialog.add(amountField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    dialog.add(new JLabel("Your Aadhar Number:"), gbc);
    gbc.gridx = 1;
    JTextField aadharField = new JTextField(15);
    dialog.add(aadharField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 4;
    dialog.add(new JLabel("Your Email:"), gbc);
    gbc.gridx = 1;
    JTextField emailField = new JTextField(15);
    dialog.add(emailField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.gridwidth = 2;
    JButton submitButton = new JButton("Submit Donation");
    submitButton.addActionListener(e -> {
        String amount = amountField.getText().trim();
        String aadhar = aadharField.getText().trim();
        String email = emailField.getText().trim();

        if (amount.isEmpty() || aadhar.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(dialog, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Donation donation = new Donation(userPhone, request.getItem(), request.getQuantity(), 
                                       amount, aadhar, email);
        FileHandler.saveDonation(donation);
        JOptionPane.showMessageDialog(dialog, "Donation submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        dialog.dispose();
    });
    dialog.add(submitButton, gbc);

    dialog.setVisible(true);
}
}
