package Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TelaItensRestauranteGUI extends JFrame {
    private JTextArea txtItens;
    private JButton btnAvancar;
    private JButton btnVoltar;

    public TelaItensRestauranteGUI(String restauranteSelecionado) {
        super("Itens do Restaurante");

        // Criando componentes
        txtItens = new JTextArea(10, 30);
        txtItens.setEditable(false);

        btnAvancar = new JButton("Avançar");
        btnVoltar = new JButton("Voltar");

        // Configurando layout
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelPrincipal.add(new JScrollPane(txtItens), BorderLayout.CENTER);

        JPanel panelBotoes = new JPanel(new FlowLayout());
        panelBotoes.add(btnVoltar);
        panelBotoes.add(btnAvancar);

        panelPrincipal.add(panelBotoes, BorderLayout.SOUTH);

        getContentPane().add(panelPrincipal);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Carregar os itens do restaurante
        carregarItensRestaurante(restauranteSelecionado);

        // Evento de clique do botão de avançar
        btnAvancar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir tela de confirmação de pedido
                new ConfirmacaoPedidoGUI(restauranteSelecionado, TelaItensRestauranteGUI.this);
            }
        });

        // Evento de clique do botão de voltar
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose(); // Fechar a tela de itens do restaurante
            }
        });
    }

    // Método para carregar os itens do restaurante do arquivo estoque.txt
   // Método para carregar itens do restaurante
    private void carregarItensRestaurante(String restauranteSelecionado) {
    List<String> itens = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader("restaurante/estoque.txt"))) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(",");
            if (dados.length >= 2 && dados[0].equals(restauranteSelecionado)) {
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
