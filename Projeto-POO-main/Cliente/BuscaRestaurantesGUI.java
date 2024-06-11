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

public class BuscaRestaurantesGUI extends JFrame {
    private JTextField txtBusca;
    private JComboBox<String> cmbFiltro;
    private JList<String> listResultados;
    private JButton btnBuscar;
    private JButton btnAvancar;
    private JButton btnVoltar;

    private static final String ARQUIVO_RESTAURANTES = "restaurante/DadosRestaurante.txt";

    public BuscaRestaurantesGUI(JFrame telaPrincipal) {
        super("Buscar Restaurantes");

        // Criando componentes
        JLabel lblBusca = new JLabel("Buscar:");
        txtBusca = new JTextField(20);

        JLabel lblFiltro = new JLabel("Filtro:");
        String[] opcoesFiltro = {"Nome", "Tipo de Comida", "Localização"};
        cmbFiltro = new JComboBox<>(opcoesFiltro);

        listResultados = new JList<>();
        JScrollPane scrollPane = new JScrollPane(listResultados);

        btnBuscar = new JButton("Buscar");
        btnAvancar = new JButton("Avançar");
        btnVoltar = new JButton("Voltar");

        // Configurando layout
        JPanel panelBusca = new JPanel(new FlowLayout());
        panelBusca.add(lblBusca);
        panelBusca.add(txtBusca);
        panelBusca.add(lblFiltro);
        panelBusca.add(cmbFiltro);

        JPanel panelBotoes = new JPanel(new FlowLayout());
        panelBotoes.add(btnBuscar);
        panelBotoes.add(btnAvancar);
        panelBotoes.add(btnVoltar);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adiciona borda
        panelPrincipal.add(panelBusca, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        panelPrincipal.add(panelBotoes, BorderLayout.SOUTH);

        getContentPane().add(panelPrincipal);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        // Evento de clique do botão de buscar
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String busca = txtBusca.getText().trim();
                String filtro = (String) cmbFiltro.getSelectedItem();
                buscarRestaurantes(busca, filtro);
            }
        });

        // Evento de clique do botão de avançar
        btnAvancar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String restauranteSelecionado = listResultados.getSelectedValue();
                if (restauranteSelecionado != null) {
                    new VisualizarItensRestauranteGUI(restauranteSelecionado, BuscaRestaurantesGUI.this);
                    setVisible(false); // Esconde a tela de busca de restaurantes, mas não a fecha
                } else {
                    JOptionPane.showMessageDialog(BuscaRestaurantesGUI.this, "Selecione um restaurante.");
                }
            }
        });

        // Evento de clique do botão de voltar
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaPrincipal.setVisible(true);
                dispose(); // Fechar a janela de busca de restaurantes
            }
        });
    }

    // Método para buscar restaurantes no arquivo
    private void buscarRestaurantes(String busca, String filtro) {
        List<String> resultados = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_RESTAURANTES))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length >= 3) {
                    String nomeRestaurante = dados[0];
                    String tipoComida = dados[1];
                    String localizacao = dados[2];

                    if ((filtro.equals("Nome") && nomeRestaurante.contains(busca)) ||
                        (filtro.equals("Tipo de Comida") && tipoComida.contains(busca)) ||
                        (filtro.equals("Localização") && localizacao.contains(busca))) {
                        resultados.add(linha);
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar restaurantes: " + e.getMessage());
        }

        exibirResultados(resultados);
    }

    // Método para exibir os resultados na lista
    private void exibirResultados(List<String> resultados) {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String resultado : resultados) {
            model.addElement(resultado);
        }
        listResultados.setModel(model);
    }

    public static void main(String[] args) {
        JFrame telaPrincipal = new JFrame(); // Substitua por sua tela principal
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BuscaRestaurantesGUI(telaPrincipal);
            }
        });
    }
}
