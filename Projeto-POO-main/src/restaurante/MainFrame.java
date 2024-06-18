package src.restaurante;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        super("Sistema de Gerenciamento de Restaurante");

        // Configuração inicial da tela principal
        setLayout(new BorderLayout());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicialmente mostrar a tela de login do restaurante
        showLoginRestaurante();
    }

    public void showLoginRestaurante() {
        getContentPane().removeAll();
        new LoginRestauranteGUI(this);
        revalidate();
        repaint();
    }

    public void showRestauranteFeatures(String nomeRestaurante) {
        getContentPane().removeAll();
        new FuncionalidadesRestauranteGUI(nomeRestaurante);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    public void showLogin() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showLogin'");
    }
}
