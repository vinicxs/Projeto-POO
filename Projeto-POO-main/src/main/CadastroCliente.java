package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CadastroCliente extends JFrame {
    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtSenha;
    private JTextField txtConfirmaSenha;
    private JTextField txtTelefone;
    private JTextField txtIdade;
    private JTextField txtEndereco;
    private JButton btnCadastrar;

    public CadastroCliente() {
        setTitle("Cadastro de Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2));

        txtNome = new JTextField();
        txtEmail = new JTextField();
        txtSenha = new JTextField();
        txtConfirmaSenha = new JTextField();
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
        String senha = txtSenha.getText().trim();
        String confirmaSenha = txtConfirmaSenha.getText().trim();
        String telefone = txtTelefone.getText().trim();
        String idade = txtIdade.getText().trim();
        String endereco = txtEndereco.getText().trim();

        if (!senha.equals(confirmaSenha)) {
            JOptionPane.showMessageDialog(this, "As senhas não conferem. Tente novamente.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("clientes.txt", true))) {
            writer.write(nome + "," + email + "," + senha + "," + telefone + "," + idade + "," + endereco);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar os dados: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new CadastroCliente();
    }
}
