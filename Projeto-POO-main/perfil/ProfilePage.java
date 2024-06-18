package perfil;

import javax.swing.*;
import main.Main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilePage extends JFrame {
    private JLabel lblNome;
    private JLabel lblEmail;
    private JLabel lblTipo;
    private JButton btnLogout;

    private User user;

    public ProfilePage(User user) {
        this.user = user;
        setTitle("Profile Page");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        lblNome = new JLabel("Nome: " + user.getNome());
        lblEmail = new JLabel("Email: " + user.getEmail());
        lblTipo = new JLabel("Tipo: " + user.getTipo());

        btnLogout = new JButton("Logout");
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });

        add(lblNome);
        add(lblEmail);
        add(lblTipo);
        add(btnLogout);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void logout() {
        dispose();
        new Main().setVisible(true);
    }

    public static void showProfilePage(User user) {
        new ProfilePage(user);
    }

    public static void main(String[] args) {
        User user = new User("João Silva", "joao.silva@example.com", "Cliente");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ProfilePage(user);
            }
        });
    }
}
