package main;

import perfil.UserInputPage;
import restaurante.CadastroRestauranteGUI;
import perfil.ProfilePage;
import perfil.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main extends JFrame {
    private JButton btnRestaurantRegistration;
    private JButton btnClientRegistration;
    private JButton btnRestaurantLogin;
    private JButton btnClientLogin;
    private String restauranteLogado;

    public Main() {
        setTitle("Main Interface");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2, 10, 10));

        btnRestaurantRegistration = new JButton("Registro de Restaurante ");
        btnRestaurantRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRestaurantRegistration();
            }
        });
        add(btnRestaurantRegistration);

        btnClientRegistration = new JButton("Registro de Cliente");
        btnClientRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openUserInputPage();
            }
        });
        add(btnClientRegistration);

        btnRestaurantLogin = new JButton("Login de Restaurante");
        btnRestaurantLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginWindow("Restaurant");
            }
        });
        add(btnRestaurantLogin);

        btnClientLogin = new JButton("Login de Cliente");
        btnClientLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginWindow("Client");
            }
        });
        add(btnClientLogin);
    }

    private void openRestaurantRegistration() {
        CadastroRestauranteGUI registration = new CadastroRestauranteGUI();
        registration.setVisible(true);
    }

    private void openUserInputPage() {
        UserInputPage userInputPage = new UserInputPage();
        userInputPage.setVisible(true);
    }

    private void openLoginWindow(String userType) {
        // Implementar a lógica de abrir a janela de login para diferentes tipos de usuários
    }

    public static void showProfilePage(User user) {
        ProfilePage profilePage = new ProfilePage(user);
        profilePage.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

