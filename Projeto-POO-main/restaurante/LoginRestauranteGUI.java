package restaurante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginRestauranteGUI extends JPanel {
    private JTextField txtNomeRestaurante;
    private JPasswordField txtSenha;
    private MainFrame mainFrame;
    private static final String ARQUIVO_DADOS = "restaurante/DadosRestaurante.txt";

    public LoginRestauranteGUI(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        // Criando componentes
        JLabel lblNomeRestaurante = new JLabel("Nome do Restaurante:");
        JLabel lblSenha = new JLabel("Senha:");

        txtNomeRestaurante = new JTextField(20);
        txtSenha = new JPasswordField(20);

        JButton btnLogin = new JButton("Login");
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnCadastrar = new JButton("Cadastrar");

        // Configurando layout
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.add(lblNomeRestaurante);
        panel.add(txtNomeRestaurante);
        panel.add(lblSenha);
        panel.add(txtSenha);
        panel.add(btnCancelar);
        panel.add(btnLogin);
        panel.add(new JLabel()); // Espaço em branco
        panel.add(btnCadastrar);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        // Evento de clique do botão de login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeRestaurante = txtNomeRestaurante.getText();
                String senha = new String(txtSenha.getPassword());

                if (verificarCredenciais(nomeRestaurante, senha)) {
                    abrirTelaPrincipalRestaurante(nomeRestaurante);
                } else {
                    JOptionPane.showMessageDialog(LoginRestauranteGUI.this, "Credenciais inválidas. Tente novamente.");
                }
            }
        });

        // Evento de clique do botão de cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Fechar a aplicação
            }
        });

        // Evento de clique do botão de cadastrar
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showCadastro();
            }
        });
    }

    // Método para verificar as credenciais no arquivo
    private boolean verificarCredenciais(String nome, String senha) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_DADOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length > 1 && dados[0].equals(nome) && dados[1].equals(senha)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para abrir a tela principal do restaurante
    private void abrirTelaPrincipalRestaurante(String nomeRestaurante) {
        // Implemente aqui a lógica para abrir a tela principal do restaurante
        // Por exemplo:
        // TelaPrincipalRestauranteGUI telaPrincipal = new TelaPrincipalRestauranteGUI(nomeRestaurante);
        // telaPrincipal.setVisible(true);
        // Lembre-se de criar a classe TelaPrincipalRestauranteGUI conforme necessário
        JOptionPane.showMessageDialog(this, "Login bem-sucedido. Abrindo a tela principal...");
    }
}

