package Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ConfirmacaoPedidoGUI extends JFrame {
    private JFrame telaItensRestaurante;
    private String restauranteSelecionado;
    private boolean retirarNoLocal;
    private JLabel lblEndereco;
    private JTextField txtEndereco;
    private JButton btnFinalizar;
    private JButton btnVoltar;

    public ConfirmacaoPedidoGUI(String restauranteSelecionado, JFrame telaItensRestaurante, boolean retirarNoLocal) {
        super("Confirmar Pedido");

        this.restauranteSelecionado = restauranteSelecionado;
        this.telaItensRestaurante = telaItensRestaurante;
        this.retirarNoLocal = retirarNoLocal;

        // Criando componentes
        lblEndereco = new JLabel("Endereço:");
        txtEndereco = new JTextField(20);
        btnFinalizar = new JButton("Finalizar Compras");
        btnVoltar = new JButton("Voltar");

        // Configurando layout
        JPanel panelPrincipal = new JPanel(new GridLayout(3, 2, 5, 5));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelPrincipal.add(lblEndereco);
        panelPrincipal.add(txtEndereco);
        panelPrincipal.add(btnVoltar);
        panelPrincipal.add(btnFinalizar);

        getContentPane().add(panelPrincipal);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        if (retirarNoLocal) {
            lblEndereco.setVisible(false);
            txtEndereco.setVisible(false);
        }

        // Evento de clique do botão de finalizar
        btnFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarPedido();
            }
        });

        // Evento de clique do botão de voltar
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                telaItensRestaurante.setVisible(true); // Voltar para a tela de itens do restaurante
            }
        });
    }

    public ConfirmacaoPedidoGUI(String restauranteSelecionado2, TelaItensRestauranteGUI telaItensRestauranteGUI) {
        //TODO Auto-generated constructor stub
    }

    // Método para confirmar o pedido
    private void confirmarPedido() {
        String endereco = txtEndereco.getText();

        // Salvar o pedido em um arquivo
        salvarPedido(endereco);

        JOptionPane.showMessageDialog(this, "Pedido confirmado.");

        // Fechar a tela de itens do restaurante e a tela de confirmação de pedido
        telaItensRestaurante.dispose();
        dispose();
    }

    // Método para salvar o pedido em um arquivo
    private void salvarPedido(String endereco) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cliente/pedidos.txt", true))) {
            writer.write("Restaurante: " + restauranteSelecionado + ", Endereço: " + (retirarNoLocal ? "Retirar no Local" : endereco) + "\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar pedido: " + e.getMessage());
        }

        // Atualizar a quantidade no estoque do restaurante
        atualizarEstoque();
    }

    // Método para atualizar o estoque do restaurante
    private void atualizarEstoque() {
        // Aqui você deve implementar a lógica para atualizar a quantidade no estoque do restaurante
        // Leitura do arquivo, alteração das quantidades e gravação de volta no arquivo.
        // Similar ao que foi feito no método carregarItensRestaurante, mas com escrita no arquivo.
    }
}
