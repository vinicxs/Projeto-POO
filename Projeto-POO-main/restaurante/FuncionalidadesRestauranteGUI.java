package restaurante;

import Cliente.VisualizarItensRestauranteGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FuncionalidadesRestauranteGUI extends JFrame {
    private String nomeRestaurante;

    public FuncionalidadesRestauranteGUI(String nomeRestaurante) {
        super("Funcionalidades do Restaurante " + nomeRestaurante);
        this.nomeRestaurante = nomeRestaurante;

        // Criando componentes
        JButton btnVisualizarItens = new JButton("Visualizar Itens");
        JButton btnCadastrarItens = new JButton("Cadastrar Itens");
        JButton btnAtualizarEstoque = new JButton("Atualizar Estoque");
        JButton btnLogout = new JButton("Logout");

        // Configurando layout
        JPanel panelPrincipal = new JPanel(new GridLayout(4, 1, 10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelPrincipal.add(btnVisualizarItens);
        panelPrincipal.add(btnCadastrarItens);
        panelPrincipal.add(btnAtualizarEstoque);
        panelPrincipal.add(btnLogout);

        getContentPane().add(panelPrincipal);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Evento de clique do botão de visualizar itens
        btnVisualizarItens.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navegar para a tela de visualizar itens
                new VisualizarItensRestauranteGUI(nomeRestaurante, FuncionalidadesRestauranteGUI.this);
                setVisible(false); // Esconde a tela de funcionalidades, mas não a fecha
            }
        });

        // Evento de clique do botão de cadastrar itens
        btnCadastrarItens.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar lógica de cadastro de itens
            }
        });

        // Evento de clique do botão de atualizar estoque
        btnAtualizarEstoque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar lógica de atualização de estoque
            }
        });

        // Evento de clique do botão de logout
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginRestauranteGUI(null); // Voltar para a tela de login
                dispose(); // Fechar a tela de funcionalidades
            }
        });
    }
}
