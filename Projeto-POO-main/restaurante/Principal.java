import java.awt.*;
import javax.swing.*;

public class Principal {

    private static JFrame frame;

    public static void criarPrincipalGUI() {
        frame = new JFrame("Página Principal");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JButton loginButton = new JButton("Login");
        JButton cadastroButton = new JButton("Cadastro de Restaurante");
        JButton estoqueButton = new JButton("Gestão de Estoque");  // Adicionando botão para estoque

        panel.add(loginButton, gbc);
        gbc.gridy++;
        panel.add(cadastroButton, gbc);
        gbc.gridy++;
        panel.add(estoqueButton, gbc);  // Adicionando botão para estoque

        frame.add(panel);

        loginButton.addActionListener(e -> {
            InterfaceRest.criarLoginRestauranteGUI();
            frame.dispose();
        });

        cadastroButton.addActionListener(e -> {
            InterfaceRest.criarCadastroRestauranteGUI();
            frame.dispose();
        });

        estoqueButton.addActionListener(e -> {
            Estoque estoque = new Estoque(frame);
            estoque.exibirInterface();
            frame.dispose();
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> criarPrincipalGUI());
    }
}
