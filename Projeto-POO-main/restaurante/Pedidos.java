import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pedidos {

    private static JFrame frame;

    public static void criarPedidosGUI() {
        frame = new JFrame("Pedidos");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel tituloLabel = new JLabel("Detalhes do Pedido:");
        panel.add(tituloLabel);

        JTextArea descricaoArea = new JTextArea(10, 30);
        descricaoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(descricaoArea);
        panel.add(scrollPane);

        // Carregar os pratos do arquivo e exibi-los na área de texto
        List<String> pratos = carregarPratos();
        for (String prato : pratos) {
            descricaoArea.append(prato + "\n");
        }

        JButton voltarButton = new JButton("Voltar");
        JButton entregaButton = new JButton("Configurar Entrega");

        panel.add(Box.createVerticalStrut(20));
        panel.add(voltarButton);
        panel.add(entregaButton);

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                InterfaceFuncRest.criarInterfaceFuncRestGUI();
            }
        });

        entregaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Entrega.criarEntregaGUI();
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    private static List<String> carregarPratos() {
        List<String> pratos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("PratosRest.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                pratos.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pratos;
    }
}
