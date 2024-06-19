import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceRest {

    private static JFrame frame;

    public static void criarCadastroRestauranteGUI() {
        frame = new JFrame("Cadastro de Restaurante");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField nomeField = new JTextField();
        nomeField.setMaximumSize(new Dimension(300, 30));

        JPasswordField senhaField = new JPasswordField();
        senhaField.setMaximumSize(new Dimension(300, 30));

        JTextField enderecoField = new JTextField();
        enderecoField.setMaximumSize(new Dimension(300, 30));

        JTextField telefoneField = new JTextField();
        telefoneField.setMaximumSize(new Dimension(300, 30));

        JLabel mensagemLabel = new JLabel();
        mensagemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mensagemLabel.setForeground(Color.RED); // Cor vermelha para mensagens de erro
        mensagemLabel.setVisible(false);

        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Senha:"));
        panel.add(senhaField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Endereço:"));
        panel.add(enderecoField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Telefone:"));
        panel.add(telefoneField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(mensagemLabel);

        JButton cadastrarButton = new JButton("Cadastrar");
        JButton voltarButton = new JButton("Voltar");

        panel.add(cadastrarButton);
        panel.add(voltarButton);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String senha = new String(senhaField.getPassword());
                String endereco = enderecoField.getText();
                String telefone = telefoneField.getText();

                Restaurante.salvarDados(nome, senha, endereco, telefone);

                // Fechar a tela de cadastro após o cadastro ser concluído
                frame.dispose();

                // Abrir a tela com os dados do cadastro
                InterfaceRest.criarDadosCadastroRestauranteGUI(nome, senha, endereco, telefone);
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Principal.criarPrincipalGUI(); // Voltar para a tela principal
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void criarDadosCadastroRestauranteGUI(String nome, String senha, String endereco, String telefone) {
        JFrame dadosFrame = new JFrame("Dados do Cadastro");
        dadosFrame.setSize(400, 300);
        dadosFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nomeLabel = new JLabel("Nome: " + nome);
        JLabel senhaLabel = new JLabel("Senha: " + senha);
        JLabel enderecoLabel = new JLabel("Endereço: " + endereco);
        JLabel telefoneLabel = new JLabel("Telefone: " + telefone);

        panel.add(nomeLabel);
        panel.add(senhaLabel);
        panel.add(enderecoLabel);
        panel.add(telefoneLabel);

        JButton loginButton = new JButton("Ir para o Login");
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dadosFrame.dispose();
                InterfaceRest.criarLoginRestauranteGUI();
            }
        });

        dadosFrame.add(panel);
        dadosFrame.setVisible(true);
    }

    public static void criarLoginRestauranteGUI() {
        frame = new JFrame("Login do Restaurante");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField usuarioField = new JTextField();
        usuarioField.setMaximumSize(new Dimension(300, 30));

        JPasswordField senhaField = new JPasswordField();
        senhaField.setMaximumSize(new Dimension(300, 30));

        JLabel mensagemLabel = new JLabel();
        mensagemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mensagemLabel.setForeground(Color.RED); // Cor vermelha para mensagens de erro
        mensagemLabel.setVisible(false);

        panel.add(new JLabel("Usuário:"));
        panel.add(usuarioField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Senha:"));
        panel.add(senhaField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(mensagemLabel);

        JButton loginButton = new JButton("Login");
        JButton voltarButton = new JButton("Voltar");

        panel.add(loginButton);
        panel.add(voltarButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioField.getText();
                String senha = new String(senhaField.getPassword());
                if (Restaurante.autenticar(usuario, senha)) {
                    abrirInterfacePrincipal();
                } else {
                    mensagemLabel.setText("Credenciais inválidas");
                    mensagemLabel.setVisible(true);
                }
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Principal.criarPrincipalGUI(); // Voltar para a tela principal
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void abrirInterfacePrincipal() {
        InterfaceFuncRest.main(new String[0]); // Chamar a interface principal das funcionalidades
        frame.dispose(); // Fechar a tela de login
    }
}
