package restaurante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipalRestauranteGUI extends JFrame {
    public MenuPrincipalRestauranteGUI() {
        super("Menu Principal do Restaurante");

        // Criando componentes
        JButton btnGerenciarEstoque = new JButton("Gerenciar Estoque");
        JButton btnVisualizarVendas = new JButton("Visualizar Vendas");
        JButton btnVisualizarAvaliacoes = new JButton("Visualizar Avaliações");

        // Configurando layout
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(btnGerenciarEstoque);
        panel.add(btnVisualizarVendas);
        panel.add(btnVisualizarAvaliacoes);

        getContentPane().add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Adicionando ações aos botões
        btnGerenciarEstoque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaGerenciarEstoque();
            }
        });

        btnVisualizarVendas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaVisualizarVendas();
            }
        });

        btnVisualizarAvaliacoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaVisualizarAvaliacoes();
            }
        });
    }

    // Método para abrir a tela de gerenciamento de estoque
    private void abrirTelaGerenciarEstoque() {
        new TelaPrincipalRestauranteGUI("Nome do Restaurante");
        dispose();
    }

    // Método para abrir a tela de visualização de vendas
    private void abrirTelaVisualizarVendas() {
        // Implemente aqui a lógica para abrir a tela de visualização de vendas
    }

    // Método para abrir a tela de visualização de avaliações
    private void abrirTelaVisualizarAvaliacoes() {
        // Implemente aqui a lógica para abrir a tela de visualização de avaliações
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipalRestauranteGUI();
            }
        });
    }
}
