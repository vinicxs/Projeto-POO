package main;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuscaRestauranteGUI extends JFrame {
    private JTextField txtBusca;
    private JComboBox<String> cmbFiltro;
    private JList<String> listResultados;
    private JButton btnBuscar;

    public BuscaRestauranteGUI() {
        setTitle("Buscar Restaurantes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelTop = new JPanel(new GridLayout(2, 2));
        txtBusca = new JTextField();
        cmbFiltro = new JComboBox<>(new String[]{"Todos", "Chinesa", "Italiana", "Japonesa"});
        panelTop.add(new JLabel("Buscar:"));
        panelTop.add(txtBusca);
        panelTop.add(new JLabel("Filtrar por:"));
        panelTop.add(cmbFiltro);

        listResultados = new JList<>();
        JScrollPane scrollPane = new JScrollPane(listResultados);

        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarRestaurantes());

        add(panelTop, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(btnBuscar, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void buscarRestaurantes() {
        String busca = txtBusca.getText().trim().toLowerCase();
        String filtro = cmbFiltro.getSelectedItem().toString();
        List<String> resultados = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("restaurantes.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                String nome = dados[0];
                String tipo = dados[1].toLowerCase();
                String localizacao = dados[2].toLowerCase();

                if ((filtro.equals("Todos") || tipo.contains(filtro.toLowerCase())) &&
                        (nome.toLowerCase().contains(busca) || localizacao.contains(busca))) {
                    resultados.add(nome + " - " + tipo + " - " + localizacao);
                }
            }
            listResultados.setListData(resultados.toArray(new String[0]));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao ler os dados: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new BuscaRestauranteGUI();
    }
}
