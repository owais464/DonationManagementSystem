import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HistoryPanel extends JPanel {
public HistoryPanel(String userPhone, String userRole) {
setLayout(new BorderLayout());

    String[] columnNames;
    Object[][] data;

    if (userRole.equals("donor")) {
        columnNames = new String[]{"Item", "Quantity", "Amount", "Date"};
        List<Donation> donations = FileHandler.getDonationsForUser(userPhone);
        data = new Object[donations.size()][4];
        
        for (int i = 0; i < donations.size(); i++) {
            Donation donation = donations.get(i);
            data[i][0] = donation.getItem();
            data[i][1] = donation.getQuantity();
            data[i][2] = donation.getAmount();
            data[i][3] = "N/A"; // You can add date field to Donation class if needed
        }
    } else if (userRole.equals("receiver")) {
        columnNames = new String[]{"Item", "Quantity", "Urgent", "Status"};
        List<Request> requests = FileHandler.getRequestsForUser(userPhone);
        data = new Object[requests.size()][4];
        
        for (int i = 0; i < requests.size(); i++) {
            Request request = requests.get(i);
            data[i][0] = request.getItem();
            data[i][1] = request.getQuantity();
            data[i][2] = request.isUrgent() ? "Yes" : "No";
            data[i][3] = "Pending"; // You can add status tracking if needed
        }
    } else { // admin view
        columnNames = new String[]{"Donor/Receiver", "Item", "Quantity", "Amount", "Type"};
        List<Donation> donations = FileHandler.getAllDonations();
        data = new Object[donations.size()][5];
        
        for (int i = 0; i < donations.size(); i++) {
            Donation donation = donations.get(i);
            data[i][0] = donation.getDonorPhone();
            data[i][1] = donation.getItem();
            data[i][2] = donation.getQuantity();
            data[i][3] = donation.getAmount();
            data[i][4] = "Donation";
        }
    }

    JTable table = new JTable(new DefaultTableModel(data, columnNames));
    JScrollPane scrollPane = new JScrollPane(table);
    add(scrollPane, BorderLayout.CENTER);

    if (userRole.equals("admin")) {
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            // Refresh logic would go here
            JOptionPane.showMessageDialog(this, "Data refreshed", "Info", JOptionPane.INFORMATION_MESSAGE);
        });
        add(refreshButton, BorderLayout.SOUTH);
    }
}
}