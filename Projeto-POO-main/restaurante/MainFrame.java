package restaurante;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private LoginRestauranteGUI loginPanel;
    private CadastroRestauranteGUI cadastroPanel;

    public MainFrame() {
        super("Sistema de Restaurante");

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        loginPanel = new LoginRestauranteGUI(this);
        cadastroPanel = new CadastroRestauranteGUI(this);

        cardPanel.add(loginPanel, "login");
        cardPanel.add(cadastroPanel, "cadastro");

        add(cardPanel);
        cardLayout.show(cardPanel, "login");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 350);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void showLogin() {
        cardLayout.show(cardPanel, "login");
    }

    public void showCadastro() {
        cardLayout.show(cardPanel, "cadastro");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}
