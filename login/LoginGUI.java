package login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginGUI extends JFrame {
    private final JTextField txtEmail;
    private final JPasswordField txtSenha;

    public LoginGUI() {
        super("Login");

        // Criando componentes
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblSenha = new JLabel("Senha:");
        txtEmail = new JTextField(20);
        txtSenha = new JPasswordField(20);
        JButton btnLogin = new JButton("Login");

        // Configurando layout
        setLayout(new GridLayout(3, 2, 5, 5));
        add(lblEmail);
        add(txtEmail);
        add(lblSenha);
        add(txtSenha);
        add(new JLabel()); // Espaço em branco
        add(btnLogin);

        // Evento de clique do botão de login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                String senha = new String(txtSenha.getPassword());
                if (verificarLogin(email, senha)) {
                    JOptionPane.showMessageDialog(LoginGUI.this, "Login bem-sucedido!");
                } else {
                    JOptionPane.showMessageDialog(LoginGUI.this, "Email ou senha incorretos. Tente novamente.");
                }
            }
        });

        // Configurações da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null); // Centralizar na tela
        setResizable(false); // Não permitir redimensionamento
        setVisible(true);
    }

    // Método para verificar o login
    public boolean verificarLogin(String email, String senha) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/nicolas.bueno/OneDrive - Grupo Marista/programar/PjBL/banco de dados/Cadastros.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dadosUsuario = linha.split(",");
                if (dadosUsuario.length == 3 && dadosUsuario[1].equals(email) && dadosUsuario[2].equals(senha)) {
                    return true; // Usuário encontrado e senha correspondente
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(LoginGUI.this, "Erro ao verificar login. Tente novamente mais tarde.");
        }
        return false; // Usuário não encontrado ou senha incorreta
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginGUI();
            }
        });
    }
}
