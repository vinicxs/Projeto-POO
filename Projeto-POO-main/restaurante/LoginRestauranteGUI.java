package restaurante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginRestauranteGUI extends JFrame {
    private JTextField txtNomeRestaurante;
    private JPasswordField txtSenha;
    private MainFrame mainFrame;

    public LoginRestauranteGUI(MainFrame mainFrame) {
        super("Login do Restaurante");
        this.mainFrame = mainFrame;

        // Criando componentes
        JLabel lblNomeRestaurante = new JLabel("Nome do Restaurante:");
        JLabel lblSenha = new JLabel("Senha:");

        txtNomeRestaurante = new JTextField(20);
        txtSenha = new JPasswordField(20);

        JButton btnLogin = new JButton("Login");
        JButton btnCancelar = new JButton("Cancelar");

        // Configurando layout
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adiciona borda
        panel.add(lblNomeRestaurante);
        panel.add(txtNomeRestaurante);
        panel.add(lblSenha);
        panel.add(txtSenha);
        panel.add(btnCancelar);
        panel.add(btnLogin);

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        // Evento de clique do botão de login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeRestaurante = txtNomeRestaurante.getText();
                String senha = new String(txtSenha.getPassword());

                if (nomeRestaurante.equals("restaurante123") && senha.equals("senha123")) {
                    mainFrame.showRestauranteFeatures(nomeRestaurante);
                    dispose(); // Fechar a janela de login do restaurante
                } else {
                    JOptionPane.showMessageDialog(LoginRestauranteGUI.this, "Credenciais inválidas. Tente novamente.");
                }
            }
        });

        // Evento de clique do botão de cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fechar a janela de login do restaurante
            }
        });
    }
}
