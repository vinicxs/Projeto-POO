package Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginClienteGUI extends JFrame {
    private JTextField txtEmail;
    private JPasswordField txtSenha;

    private static final String PASTA_CLIENTE = "cliente/";
    private static final String ARQUIVO_CLIENTE = PASTA_CLIENTE + "clientes.txt";

    public LoginClienteGUI() {
        super("Login do Cliente");

        // Criando componentes
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblSenha = new JLabel("Senha:");

        txtEmail = new JTextField(20);
        txtSenha = new JPasswordField(20);

        JButton btnLogin = new JButton("Login");
        JButton btnCancelar = new JButton("Cancelar");

        // Configurando layout
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblSenha);
        panel.add(txtSenha);
        panel.add(btnCancelar);
        panel.add(btnLogin);

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Evento de clique do botão de login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText().trim();
                String senha = new String(txtSenha.getPassword()).trim();

                if (verificarCredenciais(email, senha)) {
                    JOptionPane.showMessageDialog(LoginClienteGUI.this, "Login efetuado com sucesso!");
                    abrirTelaPrincipalCliente();
                } else {
                    JOptionPane.showMessageDialog(LoginClienteGUI.this, "Credenciais inválidas. Tente novamente.");
                }
            }
        });

        // Evento de clique do botão de cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fechar a janela de login do cliente
            }
        });
    }

    // Método para verificar as credenciais do cliente
    private boolean verificarCredenciais(String email, String senha) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_CLIENTE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length >= 5) {
                    String emailCadastrado = dados[2];
                    String senhaCadastrada = dados[1];
                    if (emailCadastrado.equals(email) && senhaCadastrada.equals(senha)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao verificar as credenciais: " + e.getMessage());
        }
        return false;
    }

    // Método para abrir a tela principal do cliente
    private void abrirTelaPrincipalCliente() {
        TelaPrincipalClienteGUI telaPrincipalCliente = new TelaPrincipalClienteGUI();
        telaPrincipalCliente.setVisible(true);
        dispose(); // Fechar a janela de login do cliente
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginClienteGUI();
            }
        });
    }
}
