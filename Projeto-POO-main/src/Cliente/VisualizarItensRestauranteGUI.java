package src.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VisualizarItensRestauranteGUI extends JFrame {
    private JTextArea txtItens;
    private JButton btnRetirarLocal;
    private JButton btnEntregar;
    private JButton btnVoltar;

    private static final String ARQUIVO_ESTOQUE = "restaurante/estoque.txt";
    private String restauranteSelecionado;

    public VisualizarItensRestauranteGUI(String restauranteSelecionado, JFrame telaBuscaRestaurantes) {
        super("Itens do Restaurante " + restauranteSelecionado);
        this.restauranteSelecionado = restauranteSelecionado;

        // Criando componentes
        txtItens = new JTextArea(10, 30);
        txtItens.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtItens);

        btnRetirarLocal = new JButton("Retirar no Local");
        btnEntregar = new JButton("Entregar");
        btnVoltar = new JButton("Voltar");

        // Configurando layout
        JPanel panelBotoes = new JPanel(new FlowLayout());
        panelBotoes.add(btnRetirarLocal);
        panelBotoes.add(btnEntregar);
        panelBotoes.add(btnVoltar);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adiciona borda
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        panelPrincipal.add(panelBotoes, BorderLayout.SOUTH);

        getContentPane().add(panelPrincipal);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        // Carregar itens do restaurante
        carregarItensRestaurante();

        // Evento de clique do botão de retirar no local
        btnRetirarLocal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConfirmacaoPedidoGUI(restauranteSelecionado, VisualizarItensRestauranteGUI.this, true);
                setVisible(false); // Esconde a tela de itens do restaurante, mas não a fecha
            }
        });

        // Evento de clique do botão de entregar
        btnEntregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConfirmacaoPedidoGUI(restauranteSelecionado, VisualizarItensRestauranteGUI.this, false);
                setVisible(false); // Esconde a tela de itens do restaurante, mas não a fecha
            }
        });

        // Evento de clique do botão de voltar
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaBuscaRestaurantes.setVisible(true);
                dispose(); // Fechar a janela de itens do restaurante
            }
        });
    }

    // Método para carregar itens do restaurante
    private void carregarItensRestaurante() {
        List<String> itens = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ESTOQUE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length >= 2 && dados[0].trim().equals(restauranteSelecionado.trim())) {
                    itens.add(dados[1]); // Assumindo que o nome do produto está na segunda posição
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar itens do restaurante: " + e.getMessage());
        }

        exibirItensRestaurante(itens);
    }

    // Método para exibir os itens do restaurante na tela
    private void exibirItensRestaurante(List<String> itens) {
        StringBuilder builder = new StringBuilder();
        for (String item : itens) {
            builder.append(item).append("\n");
        }
        txtItens.setText(builder.toString());
    }
}
