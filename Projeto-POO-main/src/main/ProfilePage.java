package main;

import javax.swing.*;
import java.awt.*;

public class ProfilePage extends JFrame {
    private JLabel lblNome;
    private JLabel lblEmail;
    private JLabel lblTelefone;
    private JLabel lblIdade;
    private JLabel lblEndereco;
    private JButton btnLogout;

    public ProfilePage(User user) {
        setTitle("Profile Page");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        lblNome = new JLabel("Nome: " + user.getNome());
        lblEmail = new JLabel("Email: " + user.getEmail());
        lblTelefone = new JLabel("Telefone: " + user.getTelefone());
        lblIdade = new JLabel("Idade: " + user.getIdade());
        lblEndereco = new JLabel("Endereço: " + user.getEndereco());

        btnLogout = new JButton("Logout");
        btnLogout.addActionListener(e -> {
            dispose();
            new Main().setVisible(true);
        });

        add(lblNome);
        add(lblEmail);
        add(lblTelefone);
        add(lblIdade);
        add(lblEndereco);
        add(btnLogout);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        User user = new User("João Silva", "joao.silva@example.com", "123456", "123456789", "25", "Rua Exemplo");
        new ProfilePage(user);
    }
}
