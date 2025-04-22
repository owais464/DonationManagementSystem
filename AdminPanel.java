import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel extends JFrame {
    private JTabbedPane tabbedPane;

    public AdminPanel() {
        setTitle("Admin Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        
        // Add tabs
        tabbedPane.addTab("View Donors", createDonorPanel());
        tabbedPane.addTab("View Receivers", createReceiverPanel());
        tabbedPane.addTab("View Donations", new HistoryPanel("all", "all"));
        tabbedPane.addTab("Urgent Requests", new UrgentRequestPanel("admin"));

        add(tabbedPane);
    }

    private JPanel createDonorPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columnNames = {"Name", "Phone", "Total Donations"};
        Object[][] data = FileHandler.getAllDonorsWithDonations();
        
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            Object[][] newData = FileHandler.getAllDonorsWithDonations();
            table.setModel(new javax.swing.table.DefaultTableModel(newData, columnNames));
        });
        buttonPanel.add(refreshButton);
        
        JButton removeButton = new JButton("Remove Selected");
        removeButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String phone = (String) data[row][1];
                FileHandler.removeUser(phone);
                JOptionPane.showMessageDialog(this, "Donor removed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                Object[][] newData = FileHandler.getAllDonorsWithDonations();
                table.setModel(new javax.swing.table.DefaultTableModel(newData, columnNames));
            } else {
                JOptionPane.showMessageDialog(this, "Please select a donor to remove", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonPanel.add(removeButton);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }

    private JPanel createReceiverPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columnNames = {"Name", "Phone", "Total Requests"};
        Object[][] data = FileHandler.getAllReceiversWithRequests();
        
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            Object[][] newData = FileHandler.getAllReceiversWithRequests();
            table.setModel(new javax.swing.table.DefaultTableModel(newData, columnNames));
        });
        buttonPanel.add(refreshButton);
        
        JButton removeButton = new JButton("Remove Selected");
        removeButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String phone = (String) data[row][1];
                FileHandler.removeUser(phone);
                JOptionPane.showMessageDialog(this, "Receiver removed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                Object[][] newData = FileHandler.getAllReceiversWithRequests();
                table.setModel(new javax.swing.table.DefaultTableModel(newData, columnNames));
            } else {
                JOptionPane.showMessageDialog(this, "Please select a receiver to remove", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonPanel.add(removeButton);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
}