import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPanel extends JPanel {
    private AuthFrame authFrame;
    private JTextField nameField, phoneField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;

    public SignUpPanel(AuthFrame authFrame) {
        this.authFrame = authFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Sign Up");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1;
        phoneField = new JTextField(20);
        add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Role:"), gbc);
        gbc.gridx = 1;
        String[] roles = {"donor", "receiver", "admin"};
        roleComboBox = new JComboBox<>(roles);
        add(roleComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUp();
            }
        });
        add(signUpButton, gbc);

        gbc.gridy = 6;
        JButton backButton = new JButton("Back to Sign In");
        backButton.addActionListener(e -> authFrame.showSignIn());
        add(backButton, gbc);
    }

    private void signUp() {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String password = new String(passwordField.getPassword());
        String role = (String) roleComboBox.getSelectedItem();

        if (name.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (FileHandler.userExists(phone)) {
            JOptionPane.showMessageDialog(this, "User already exists", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user;
        if (role.equals("donor")) {
            user = new Donor(name, phone, password, role);
        } else if (role.equals("receiver")) {
            user = new Receiver(name, phone, password, role);
        } else {
            user = new User(name, phone, password, role);
        }

        FileHandler.saveUser(user);
        JOptionPane.showMessageDialog(this, "Sign up successful! Please sign in.", "Success", JOptionPane.INFORMATION_MESSAGE);
        authFrame.showSignIn();
    }
}