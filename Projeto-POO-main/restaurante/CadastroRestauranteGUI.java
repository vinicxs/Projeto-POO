package restaurante;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class CadastroRestauranteGUI extends JPanel {
    private final JTextField txtNome;
    private final JPasswordField txtSenha;
    private final JTextField txtLocalizacao;
    private final JTextField txtContato;
    private final JTextField txtHorarioInicio;
    private final JTextField txtHorarioFim;
    private final JTextField txtTipoComida;

    private static final String PASTA_RESTAURANTE = "restaurante/";
    private static final String ARQUIVO_DADOS = PASTA_RESTAURANTE + "DadosRestaurante.txt";
    private MainFrame mainFrame;

    public CadastroRestauranteGUI(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

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
        JButton btnVoltar = new JButton("Voltar");

        // Configurando layout
        setLayout(new GridLayout(9, 2, 5, 5));
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
        add(btnVoltar);
        add(btnConfirmar);

        ((JPanel) this).setBorder(new EmptyBorder(10, 10, 10, 10));

        // Evento de clique do botão de confirmação
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    abrirTelaRevisao();
                }
            }
        });

        // Evento de clique do botão de voltar
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showLogin();
            }
        });
    }

    // Método para validar os campos
    private boolean validarCampos() {
        String senha = new String(txtSenha.getPassword());
        if (senha.length() < 8 || senha.length() > 12) {
            JOptionPane.showMessageDialog(this, "A senha deve ter entre 8 e 12 caracteres.");
            return false;
        }

        String contato = txtContato.getText();
        if (contato.length() != 9) {
            JOptionPane.showMessageDialog(this, "O número de contato deve ter 9 dígitos.");
            return false;
        }

        String horarioInicio = txtHorarioInicio.getText();
        String horarioFim = txtHorarioFim.getText();
        if (!Pattern.matches("\\d{2}:\\d{2}", horarioInicio) || !Pattern.matches("\\d{2}:\\d{2}", horarioFim)) {
            JOptionPane.showMessageDialog(this, "Os horários devem estar no formato HH:mm.");
            return false;
        }

        return true;
    }

    // Método para abrir a tela de revisão
    private void abrirTelaRevisao() {
        String nome = txtNome.getText();
        String senha = new String(txtSenha.getPassword());
        String localizacao = txtLocalizacao.getText();
        String contato = txtContato.getText();
        String horarioInicio = txtHorarioInicio.getText();
        String horarioFim = txtHorarioFim.getText();
        String tipoComida = txtTipoComida.getText();

        // Criando e exibindo a tela de revisão
        JFrame telaRevisao = new JFrame("Revisão de Dados do Restaurante");
        telaRevisao.setSize(400, 300);
        telaRevisao.setLocationRelativeTo(this);

        JTextArea areaRevisao = new JTextArea();
        areaRevisao.setEditable(false);
        areaRevisao.setText("Nome do Restaurante: " + nome + "\n"
                + "Senha: " + senha + "\n"
                + "Localização: " + localizacao + "\n"
                + "Número para Contato: " + contato + "\n"
                + "Horário de Funcionamento: " + horarioInicio + " - " + horarioFim + "\n"
                + "Tipo de Comida: " + tipoComida + "\n");

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        JPanel panelBotoes = new JPanel();
        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnCancelar);

        telaRevisao.add(new JScrollPane(areaRevisao), BorderLayout.CENTER);
        telaRevisao.add(panelBotoes, BorderLayout.SOUTH);

        telaRevisao.setVisible(true);

        // Evento de clique do botão de salvar
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarDados(nome, senha, localizacao, contato, horarioInicio, horarioFim, tipoComida);
                telaRevisao.dispose();
                mainFrame.showLogin(); // Volta para a tela de login após salvar os dados
            }
        });

        // Evento de clique do botão de cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaRevisao.dispose();
            }
        });
    }

    // Método para salvar os dados do restaurante em um arquivo
    private void salvarDados(String nome, String senha, String localizacao, String contato, String horarioInicio, String horarioFim, String tipoComida) {
        try {
            File pastaRestaurante = new File(PASTA_RESTAURANTE);
            if (!pastaRestaurante.exists()) {
                pastaRestaurante.mkdirs();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_DADOS, true))) {
                writer.write(nome + "," + senha + "," + localizacao + "," + contato + "," + horarioInicio + "," + horarioFim + "," + tipoComida);
                writer.newLine();
            }

            JOptionPane.showMessageDialog(this, "Dados do restaurante salvos com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar os dados do restaurante.");
            e.printStackTrace();
        }
    }
}
