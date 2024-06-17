package src.restaurante;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TelaPrincipalRestauranteGUI extends JFrame {
    private JTextField txtNomeProduto;
    private JTextField txtQuantidade;
    private JTextArea txtAreaEstoque;
    private JButton btnAdicionar;
    private JButton btnRetirar;
    private JButton btnExcluir;
    private JButton btnMostrar;

    // Mapa para armazenar os itens do estoque
    private Map<String, Integer> estoque = new HashMap<>();
    private static final String PASTA_RESTAURANTE = "restaurante/";
    private static final String ARQUIVO_ESTOQUE = PASTA_RESTAURANTE + "estoque.txt";

    public TelaPrincipalRestauranteGUI(String nomeRestaurante) {
        super("Tela Principal - " + nomeRestaurante);

        // Carregar o estoque do arquivo
        carregarEstoque();

        // Criando componentes
        JLabel lblNomeProduto = new JLabel("Nome do Produto:");
        JLabel lblQuantidade = new JLabel("Quantidade:");

        txtNomeProduto = new JTextField(20);
        txtQuantidade = new JTextField(5);
        txtQuantidade.setText("0"); // Inicializa com zero

        btnAdicionar = new JButton("+");
        btnRetirar = new JButton("-");
        btnExcluir = new JButton("Excluir");
        btnMostrar = new JButton("Mostrar");

        txtAreaEstoque = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(txtAreaEstoque);

        // Configurando layout
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.add(lblNomeProduto);
        panel.add(txtNomeProduto);
        panel.add(lblQuantidade);
        panel.add(createQuantityPanel());
        panel.add(btnAdicionar);
        panel.add(btnRetirar);
        panel.add(btnExcluir);
        panel.add(btnMostrar);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.add(panel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Adicionando ações aos botões
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProduto();
            }
        });

        btnRetirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retirarProduto();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirProduto();
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEstoque();
            }
        });
    }

    // Método para criar o painel de quantidade com os botões de adicionar e retirar
    private JPanel createQuantityPanel() {
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

        txtQuantidade.setHorizontalAlignment(JTextField.RIGHT);

        quantityPanel.add(txtQuantidade);
        quantityPanel.add(btnAdicionar);
        quantityPanel.add(btnRetirar);

        return quantityPanel;
    }

    // Método para adicionar um produto ao estoque
    private void adicionarProduto() {
        String nomeProduto = txtNomeProduto.getText().trim();
        String quantidadeStr = txtQuantidade.getText().trim();

        if (!nomeProduto.isEmpty() && isNumeric(quantidadeStr)) {
            int quantidade = Integer.parseInt(quantidadeStr);
            estoque.put(nomeProduto, estoque.getOrDefault(nomeProduto, 0) + quantidade);
            atualizarArquivoEstoque();
            txtAreaEstoque.setText("Produto adicionado ao estoque: " + nomeProduto);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, insira um nome de produto e uma quantidade válida.");
        }
    }

    // Método para retirar um produto do estoque
    private void retirarProduto() {
        String nomeProduto = txtNomeProduto.getText().trim();
        String quantidadeStr = txtQuantidade.getText().trim();

        if (!nomeProduto.isEmpty() && isNumeric(quantidadeStr)) {
            int quantidade = Integer.parseInt(quantidadeStr);
            int estoqueAtual = estoque.getOrDefault(nomeProduto, 0);
            if (estoqueAtual >= quantidade) {
                estoque.put(nomeProduto, estoqueAtual - quantidade);
                atualizarArquivoEstoque();
                txtAreaEstoque.setText("Produto retirado do estoque: " + nomeProduto);
            } else {
                JOptionPane.showMessageDialog(this, "Quantidade insuficiente no estoque.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, insira um nome de produto e uma quantidade válida.");
        }
    }

    // Método para excluir todos os itens com o nome especificado do estoque
    private void excluirProduto() {
        String nomeProduto = txtNomeProduto.getText().trim();

        if (!nomeProduto.isEmpty()) {
            estoque.remove(nomeProduto);
            atualizarArquivoEstoque();
            txtAreaEstoque.setText("Todos os itens com o nome '" + nomeProduto + "' foram removidos do estoque.");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, insira um nome de produto.");
        }
    }

    // Método para mostrar a quantidade de itens com o nome especificado no estoque
    private void mostrarEstoque() {
        String nomeProduto = txtNomeProduto.getText().trim();

        if (!nomeProduto.isEmpty()) {
            int quantidade = estoque.getOrDefault(nomeProduto, 0);
            txtAreaEstoque.setText("Quantidade de '" + nomeProduto + "' no estoque: " + quantidade);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, insira um nome de produto.");
        }
    }

    // Método auxiliar para verificar se uma string é numérica
    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    // Método para salvar o estoque em um arquivo de texto
    private void atualizarArquivoEstoque() {
        // Certificar que a pasta existe
        File pasta = new File(PASTA_RESTAURANTE);
        if (!pasta.exists()) {
            pasta.mkdirs();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_ESTOQUE))) {
            for (Map.Entry<String, Integer> entry : estoque.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar o estoque: " + e.getMessage());
        }
    }

    // Método para carregar o estoque de um arquivo de texto
    private void carregarEstoque() {
        File file = new File(ARQUIVO_ESTOQUE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String nomeProduto = parts[0].trim();
                        int quantidade = Integer.parseInt(parts[1].trim());
                        estoque.put(nomeProduto, quantidade);
                    }
                }
            } catch (IOException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar o estoque: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipalRestauranteGUI("Nome do Restaurante");
            }
        });
    }
}
