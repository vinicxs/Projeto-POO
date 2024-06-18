package Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CadastroClienteGUI extends JFrame {
    private JTextField txtNome;
    private JPasswordField txtSenha;
    private JPasswordField txtConfirmaSenha;
    private JTextField txtEmail;
    private JTextField txtTelefone;
    private JTextField txtIdade;

    private static final String PASTA_CLIENTE = "cliente/";
    private static final String ARQUIVO_CLIENTE = PASTA_CLIENTE + "clientes.txt";

    public CadastroClienteGUI() {
        super("Cadastro de Cliente");

        // Criando componentes
        JLabel lblNome = new JLabel("Nome:");
        JLabel lblSenha = new JLabel("Senha:");
        JLabel lblConfirmaSenha = new JLabel("Confirma Senha:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblTelefone = new JLabel("Telefone:");
        JLabel lblIdade = new JLabel("Idade:");

        txtNome = new JTextField(20);
        txtSenha = new JPasswordField(20);
        txtConfirmaSenha = new JPasswordField(20);
        txtEmail = new JTextField(20);
        txtTelefone = new JTextField(20);
        txtIdade = new JTextField(20);

        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        // Configurando layout
        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adiciona borda
        panel.add(lblNome);
        panel.add(txtNome);
        panel.add(lblSenha);
        panel.add(txtSenha);
        panel.add(lblConfirmaSenha);
        panel.add(txtConfirmaSenha);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblTelefone);
        panel.add(txtTelefone);
        panel.add(lblIdade);
        panel.add(txtIdade);
        panel.add(btnCancelar);
        panel.add(btnConfirmar);

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        // Evento de clique do botão de confirmar
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText().trim();
                String senha = new String(txtSenha.getPassword()).trim();
                String confirmaSenha = new String(txtConfirmaSenha.getPassword()).trim();
                String email = txtEmail.getText().trim();
                String telefone = txtTelefone.getText().trim();
                String idade = txtIdade.getText().trim();

                if (!senha.equals(confirmaSenha)) {
                    JOptionPane.showMessageDialog(CadastroClienteGUI.this, "As senhas não conferem. Tente novamente.");
                    return;
                }

                if (!telefone.matches("\\d+")) {
                    JOptionPane.showMessageDialog(CadastroClienteGUI.this, "Telefone deve conter apenas números.");
                    return;
                }

                if (!idade.matches("\\d+")) {
                    JOptionPane.showMessageDialog(CadastroClienteGUI.this, "Idade deve conter apenas números.");
                    return;
                }

                // Confirmar os dados em uma nova tela
                int resposta = JOptionPane.showConfirmDialog(CadastroClienteGUI.this, 
                        "Nome: " + nome + "\nEmail: " + email + "\nTelefone: " + telefone + "\nIdade: " + idade, 
                        "Confirmação de Cadastro", 
                        JOptionPane.YES_NO_OPTION);

                if (resposta == JOptionPane.YES_OPTION) {
                    // Salvar os dados no arquivo
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_CLIENTE, true))) {
                        writer.write(nome + "," + senha + "," + email + "," + telefone + "," + idade);
                        writer.newLine();
                        JOptionPane.showMessageDialog(CadastroClienteGUI.this, "Cadastro realizado com sucesso!");
                        abrirTelaLoginCliente();
                        dispose();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(CadastroClienteGUI.this, "Erro ao salvar os dados: " + ex.getMessage());
                    }
                }
            }
        });

        // Evento de clique do botão de cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fechar a janela de cadastro do cliente
            }
        });
    }

    private void abrirTelaLoginCliente() {
        LoginClienteGUI loginClienteGUI = new LoginClienteGUI();
        loginClienteGUI.setSize(400, 300); // Aumenta o tamanho da janela de login
        loginClienteGUI.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CadastroClienteGUI();
            }
        });
    }
}
