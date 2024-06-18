package perfil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInputPage extends JFrame {
    private JTextField txtNome;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JPasswordField txtConfirmaSenha;
    private JTextField txtTelefone;
    private JTextField txtIdade;
    private JTextField txtEndereco;
    private JButton btnCadastrar;

    public UserInputPage() {
        setTitle("Cadastro de Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2, 10, 10));

        txtNome = new JTextField();
        txtEmail = new JTextField();
        txtSenha = new JPasswordField();
        txtConfirmaSenha = new JPasswordField();
        txtTelefone = new JTextField();
        txtIdade = new JTextField();
        txtEndereco = new JTextField();
        btnCadastrar = new JButton("Cadastrar");

        add(new JLabel("Nome:"));
        add(txtNome);
        add(new JLabel("Email:"));
        add(txtEmail);
        add(new JLabel("Senha:"));
        add(txtSenha);
        add(new JLabel("Confirma Senha:"));
        add(txtConfirmaSenha);
        add(new JLabel("Telefone:"));
        add(txtTelefone);
        add(new JLabel("Idade:"));
        add(txtIdade);
        add(new JLabel("Endereço:"));
        add(txtEndereco);
        add(btnCadastrar);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCliente();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void cadastrarCliente() {
        String nome = txtNome.getText().trim();
        String email = txtEmail.getText().trim();
        String senha = new String(txtSenha.getPassword()).trim();
        String confirmaSenha = new String(txtConfirmaSenha.getPassword()).trim();
        String telefone = txtTelefone.getText().trim();
        String idade = txtIdade.getText().trim();
        String endereco = txtEndereco.getText().trim();

        if (!senha.equals(confirmaSenha)) {
            JOptionPane.showMessageDialog(this, "As senhas não conferem. Tente novamente.");
            return;
        }

        if (!telefone.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Telefone deve conter apenas números.");
            return;
        }

        if (!idade.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Idade deve conter apenas números.");
            return;
        }

        Client client = new Client(nome, email, senha, telefone, Integer.parseInt(idade), endereco);
        // Adicione o cliente ao handler (exemplo: ClientHandler)
        ClientHandler clientHandler = new ClientHandler();
        clientHandler.addUser(client);

        JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UserInputPage();
            }
        });
    }
}
