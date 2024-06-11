package Cliente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CadastroClienteGUI extends JFrame {
    private JTextField txtNome;
    private JPasswordField txtSenha;
    private JPasswordField txtConfirmaSenha;
    private JTextField txtEmail;
    private JTextField txtTelefone;
    private JTextField txtIdade;
    private JButton btnCancelar;
    private JButton btnConfirmar;

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
        txtTelefone = new JTextField(15);
        txtIdade = new JTextField(5);

        btnCancelar = new JButton("Cancelar");
        btnConfirmar = new JButton("Confirmar");

        // Configurando layout
        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));
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

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.add(panel, BorderLayout.CENTER);

        getContentPane().add(mainPanel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Adicionando ações aos botões
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarCadastro();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    // Método para confirmar o cadastro do cliente
    private void confirmarCadastro() {
        String nome = txtNome.getText().trim();
        String senha = new String(txtSenha.getPassword()).trim();
        String confirmaSenha = new String(txtConfirmaSenha.getPassword()).trim();
        String email = txtEmail.getText().trim();
        String telefone = txtTelefone.getText().trim();
        String idadeStr = txtIdade.getText().trim();

        if (nome.isEmpty() || senha.isEmpty() || confirmaSenha.isEmpty() || email.isEmpty() || telefone.isEmpty() || idadeStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
            return;
        }

        if (!senha.equals(confirmaSenha)) {
            JOptionPane.showMessageDialog(this, "As senhas não coincidem.");
            return;
        }

        if (!telefone.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "O telefone deve conter apenas números.");
            return;
        }

        if (!idadeStr.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "A idade deve conter apenas números.");
            return;
        }

        int idade = Integer.parseInt(idadeStr);

        // Mostrar tela de confirmação
        JFrame confirmacaoFrame = new JFrame("Confirmação de Dados");
        confirmacaoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea txtAreaConfirmacao = new JTextArea(10, 30);
        txtAreaConfirmacao.setText("Nome: " + nome + "\n" +
                                    "Email: " + email + "\n" +
                                    "Telefone: " + telefone + "\n" +
                                    "Idade: " + idade);
        txtAreaConfirmacao.setEditable(false);

        JButton btnConfirmarCadastro = new JButton("Confirmar");
        JButton btnCancelarCadastro = new JButton("Cancelar");

        btnConfirmarCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarDadosCliente(nome, senha, email, telefone, idade);
                confirmacaoFrame.dispose();
            }
        });

        btnCancelarCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmacaoFrame.dispose();
            }
        });

        JPanel panelConfirmacao = new JPanel(new BorderLayout());
        panelConfirmacao.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelConfirmacao.add(txtAreaConfirmacao, BorderLayout.CENTER);

        JPanel panelBotoes = new JPanel(new FlowLayout());
        panelBotoes.add(btnConfirmarCadastro);
        panelBotoes.add(btnCancelarCadastro);

        panelConfirmacao.add(panelBotoes, BorderLayout.SOUTH);

        confirmacaoFrame.getContentPane().add(panelConfirmacao);
        confirmacaoFrame.pack();
        confirmacaoFrame.setLocationRelativeTo(this);
        confirmacaoFrame.setVisible(true);
    }

    // Método para salvar os dados do cliente em um arquivo de texto
    private void salvarDadosCliente(String nome, String senha, String email, String telefone, int idade) {
        // Certificar que a pasta existe
        File pasta = new File(PASTA_CLIENTE);
        if (!pasta.exists()) {
            pasta.mkdirs();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_CLIENTE, true))) {
            writer.println(nome + "," + senha + "," + email + "," + telefone + "," + idade);
            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
            dispose();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar os dados do cliente: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CadastroClienteGUI();
            }
        });
    }
}
