package restaurante;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class CadastroRestauranteGUI extends JFrame {
    private final JTextField txtNome;
    private final JPasswordField txtSenha;
    private final JTextField txtLocalizacao;
    private final JTextField txtContato;
    private final JTextField txtHorarioInicio;
    private final JTextField txtHorarioFim;
    private final JTextField txtTipoComida;

    public CadastroRestauranteGUI() {
        super("Cadastro de Restaurante");

        // Criando componentes
        JLabel lblNome = new JLabel("Nome do Restaurante:");
        JLabel lblSenha = new JLabel("Senha:");
        JLabel lblLocalizacao = new JLabel("Localização:");
        JLabel lblContato = new JLabel("Número para Contato:");
        JLabel lblHorario = new JLabel("Horário de Funcionamento (Início - Fim):");
        JLabel lblTipoComida = new JLabel("Tipo de Comida:");

        txtNome = new JTextField(20);
        txtSenha = new JPasswordField(20);
        txtLocalizacao = new JTextField(20);
        txtContato = new JTextField(20);
        txtHorarioInicio = new JTextField(5);
        txtHorarioFim = new JTextField(5);
        txtTipoComida = new JTextField(20);

        // Adicionando verificação para permitir somente números no campo de contato
        txtContato.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, javax.swing.text.AttributeSet a) throws javax.swing.text.BadLocationException {
                if (str != null && str.matches("[0-9]*")) {
                    super.insertString(offs, str, a);
                }
            }
        });

        JButton btnConfirmar = new JButton("Confirmar Inserção");

        // Configurando layout
        setLayout(new GridLayout(8, 2, 5, 5));
        add(lblNome);
        add(txtNome);
        add(lblSenha);
        add(txtSenha);
        add(lblLocalizacao);
        add(txtLocalizacao);
        add(lblContato);
        add(txtContato);
        add(lblHorario);
        JPanel horarioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        horarioPanel.add(txtHorarioInicio);
        horarioPanel.add(new JLabel(" - "));
        horarioPanel.add(txtHorarioFim);
        add(horarioPanel);
        add(lblTipoComida);
        add(txtTipoComida);
        add(new JLabel()); // Espaço em branco
        add(btnConfirmar);

        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        // Evento de clique do botão de confirmação
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    abrirTelaRevisao();
                }
            }
        });

        // Configurações da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 350);
        setLocationRelativeTo(null); // Centralizar na tela
        setResizable(false); // Não permitir redimensionamento
        setVisible(true);
    }

    // Método para validar os campos
    private boolean validarCampos() {
        String senha = new String(txtSenha.getPassword());
        if (senha.length() < 8 || senha.length() > 12) {
            JOptionPane.showMessageDialog(this, "A senha deve ter entre 8 e 12 caracteres.");
            return false;
        }

        String contato = txtContato.getText();
        if (contato.length() < 10 || contato.length() > 11) {
            JOptionPane.showMessageDialog(this, "O número de contato deve ter 10 ou 11 dígitos.");
            return false;
        }

        String horarioInicio = txtHorarioInicio.getText();
        String horarioFim = txtHorarioFim.getText();
        if (!Pattern.matches("\\d{2}:\\d{2}", horarioInicio) || !Pattern.matches("\\d{2}:\\d{2}", horarioFim)) {
            JOptionPane.showMessageDialog(this, "Horários devem estar no formato HH:MM.");
            return false;
        }

        return true;
    }

    // Método para abrir a tela de revisão
    private void abrirTelaRevisao() {
        JFrame frameRevisao = new JFrame("Revisão dos Dados do Restaurante");
        frameRevisao.setLayout(new GridLayout(9, 2, 5, 5));

        JLabel lblNome = new JLabel("Nome do Restaurante:");
        JLabel lblSenha = new JLabel("Senha:");
        JLabel lblLocalizacao = new JLabel("Localização:");
        JLabel lblContato = new JLabel("Número para Contato:");
        JLabel lblHorario = new JLabel("Horário de Funcionamento (Início - Fim):");
        JLabel lblTipoComida = new JLabel("Tipo de Comida:");

        JLabel lblNomeValue = new JLabel(txtNome.getText());
        JLabel lblSenhaValue = new JLabel(new String(txtSenha.getPassword()));
        JLabel lblLocalizacaoValue = new JLabel(txtLocalizacao.getText());
        JLabel lblContatoValue = new JLabel(txtContato.getText());
        JLabel lblHorarioValue = new JLabel(txtHorarioInicio.getText() + " - " + txtHorarioFim.getText());
        JLabel lblTipoComidaValue = new JLabel(txtTipoComida.getText());

        JButton btnConfirmar = new JButton("Confirmar Dados");
        JButton btnCancelar = new JButton("Cancelar");

        frameRevisao.add(lblNome);
        frameRevisao.add(lblNomeValue);
        frameRevisao.add(lblSenha);
        frameRevisao.add(lblSenhaValue);
        frameRevisao.add(lblLocalizacao);
        frameRevisao.add(lblLocalizacaoValue);
        frameRevisao.add(lblContato);
        frameRevisao.add(lblContatoValue);
        frameRevisao.add(lblHorario);
        frameRevisao.add(lblHorarioValue);
        frameRevisao.add(lblTipoComida);
        frameRevisao.add(lblTipoComidaValue);
        frameRevisao.add(new JLabel()); // Espaço em branco
        frameRevisao.add(new JLabel()); // Espaço em branco
        frameRevisao.add(btnCancelar);
        frameRevisao.add(btnConfirmar);

        ((JPanel) frameRevisao.getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarRestaurante();
                limparCampos();
                frameRevisao.dispose();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameRevisao.dispose();
            }
        });

        frameRevisao.setSize(600, 400);
        frameRevisao.setLocationRelativeTo(null);
        frameRevisao.setResizable(false);
        frameRevisao.setVisible(true);
    }

    // Método para cadastrar o restaurante
    private void cadastrarRestaurante() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DadosRestaurante.txt", true))) {
            String dadosRestaurante = String.join(",",
                txtNome.getText(),
                new String(txtSenha.getPassword()),
                txtLocalizacao.getText(),
                txtContato.getText(),
                txtHorarioInicio.getText() + " - " + txtHorarioFim.getText(),
                txtTipoComida.getText()
            );
            writer.write(dadosRestaurante + "\n");
            JOptionPane.showMessageDialog(this, "Restaurante cadastrado com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar restaurante: " + e.getMessage());
        }
    }

    // Método para limpar os campos de entrada
    private void limparCampos() {
        txtNome.setText("");
        txtSenha.setText("");
        txtLocalizacao.setText("");
        txtContato.setText("");
        txtHorarioInicio.setText("");
        txtHorarioFim.setText("");
        txtTipoComida.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CadastroRestauranteGUI();
            }
        });
    }
}
