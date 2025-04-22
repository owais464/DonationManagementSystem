import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInPanel extends JPanel {
    private AuthFrame authFrame;
    private JTextField phoneField;
    private JPasswordField passwordField;

    public SignInPanel(AuthFrame authFrame) {
        this.authFrame = authFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Sign In");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1;
        phoneField = new JTextField(20);
        add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton signInButton = new JButton("Sign In");
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signIn();
            }
        });
        add(signInButton, gbc);

        gbc.gridy = 4;
        JButton signUpButton = new JButton("Don't have an account? Sign Up");
        signUpButton.addActionListener(e -> authFrame.showSignUp());
        add(signUpButton, gbc);
    }

    private void signIn() {
        String phone = phoneField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (phone.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = FileHandler.authenticateUser(phone, password);
        if (user != null) {
            authFrame.loginSuccess(user);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid phone or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}