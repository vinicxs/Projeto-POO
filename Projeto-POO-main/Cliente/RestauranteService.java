package Cliente;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RestauranteService {
    private static final String ARQUIVO_RESTAURANTES = "restaurante/DadosRestaurante.txt";

    public List<String> buscarRestaurantes(String busca, String filtro) {
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
            JOptionPane.showMessageDialog(null, "Erro ao buscar restaurantes: " + e.getMessage());
        }

        return resultados;
    }

    public void exibirResultados(List<String> resultados, JList<String> listResultados) {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String resultado : resultados) {
            model.addElement(resultado);
        }
        listResultados.setModel(model);
    }
}
