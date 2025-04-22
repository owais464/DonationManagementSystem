import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;  // Only added this one import

public class AdminLoginPanel extends JPanel {
    private AuthFrame authFrame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public AdminLoginPanel(AuthFrame authFrame) {
        this.authFrame = authFrame;
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Admin ID:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> attemptLogin());
        add(loginButton);
    }

    private void attemptLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("AdminName," + username + "," + password + ",admin")) {
                    reader.close();
                    authFrame.loginSuccess(new User("AdminName", username, password, "admin"));
                    return;
                }
            }
            reader.close();
            JOptionPane.showMessageDialog(this, "Invalid admin credentials");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading user data");
        }
    }
}