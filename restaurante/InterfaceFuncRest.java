import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InterfaceFuncRest {

    private static JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> criarInterfaceFuncRestGUI());
    }

    public static void criarInterfaceFuncRestGUI() {
        frame = new JFrame("Funcionalidades do Restaurante");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 3, 10, 10));

        JButton estoqueButton = new JButton("Estoque");
        JButton avaliacaoButton = new JButton("Avaliação");
        JButton entregaButton = new JButton("Entrega");
        JButton pedidosButton = new JButton("Pedidos");
        JButton vendasButton = new JButton("Vendas");
        JButton fecharButton = new JButton("Fechar");

        panel.add(estoqueButton);
        panel.add(avaliacaoButton);
        panel.add(entregaButton);
        panel.add(pedidosButton);
        panel.add(vendasButton);
        panel.add(fecharButton);

        estoqueButton.addActionListener(e -> {
            Estoque estoque = new Estoque(frame);
            estoque.exibirInterface();
            frame.dispose();
        });


        avaliacaoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ação do botão de Avaliação
                JOptionPane.showMessageDialog(frame, "Ação do botão de Avaliação");
            }
        });

        entregaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir a interface de entrega
                frame.dispose();
                Entrega.criarEntregaGUI();
            }
        });

        pedidosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir a interface de pedidos
                frame.dispose();
                Pedidos.criarPedidosGUI();
            }
        });

        vendasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ação do botão de Vendas
                JOptionPane.showMessageDialog(frame, "Ação do botão de Vendas");
            }
        });

        fecharButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fechar a aplicação
                frame.dispose();
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void abrirInterfaceEstoque() {
        JFrame estoqueFrame = new JFrame("Controle de Estoque");
        estoqueFrame.setSize(600, 400);
        estoqueFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        // Aqui você pode adicionar os componentes para o controle de estoque

        estoqueFrame.add(panel);
        estoqueFrame.setVisible(true);
    }
}
