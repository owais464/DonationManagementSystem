import javax.swing.*;
import java.awt.*;

public class AuthFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private SignUpPanel signUpPanel;
    private SignInPanel signInPanel;

    public AuthFrame() {
        setTitle("Donation System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        signUpPanel = new SignUpPanel(this);
        signInPanel = new SignInPanel(this);

        mainPanel.add(signInPanel, "SignIn");
        mainPanel.add(signUpPanel, "SignUp");

        add(mainPanel);
        showSignIn();
    }

    public void showSignIn() {
        cardLayout.show(mainPanel, "SignIn");
    }

    public void showSignUp() {
        cardLayout.show(mainPanel, "SignUp");
    }

    public void loginSuccess(User user) {
        dispose();
        switch (user.getRole()) {
            case "donor":
                new DonorPanel((Donor) user).setVisible(true);
                break;
            case "receiver":
                new ReceiverPanel((Receiver) user).setVisible(true);
                break;
            case "admin":
                new AdminPanel().setVisible(true);
                break;
        }
    }
}