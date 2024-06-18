package main;

import javax.swing.*;
import perfil.UserInputPage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JButton btnCadastroCliente;
    private JButton btnBuscaRestaurantes;
    private JButton btnProfile;

    public Main() {
        setTitle("Main");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 10, 10));

        btnCadastroCliente = new JButton("Cadastrar Cliente");
        btnBuscaRestaurantes = new JButton("Buscar Restaurantes");
        btnProfile = new JButton("Perfil");

        btnCadastroCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserInputPage();
            }
        });

        btnBuscaRestaurantes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuscaRestaurantesGUI();
            }
        });

        btnProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User("João Silva", "joao.silva@example.com", "Cliente");
                ProfilePage.showProfilePage(user);
            }
        });

        add(btnCadastroCliente);
        add(btnBuscaRestaurantes);
        add(btnProfile);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}
