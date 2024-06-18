import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Entrega {

    private static JFrame frame;

    public static void criarEntregaGUI() {
        frame = new JFrame("Configurar Entrega");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel tituloLabel = new JLabel("Selecione o tipo de retirada:");
        panel.add(tituloLabel);

        JRadioButton retiradaLocalButton = new JRadioButton("Retirada no local");
        JRadioButton entregaCasaButton = new JRadioButton("Entrega em casa");

        ButtonGroup group = new ButtonGroup();
        group.add(retiradaLocalButton);
        group.add(entregaCasaButton);

        panel.add(retiradaLocalButton);
        panel.add(entregaCasaButton);

        JPanel enderecoPanel = new JPanel();
        enderecoPanel.setLayout(new BoxLayout(enderecoPanel, BoxLayout.Y_AXIS));
        enderecoPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JTextField enderecoField = new JTextField(20);
        enderecoField.setMaximumSize(new Dimension(300, 30));

        JLabel enderecoLabel = new JLabel("Endereço:");
        enderecoPanel.add(enderecoLabel);
        enderecoPanel.add(enderecoField);

        panel.add(enderecoPanel);

        JButton confirmarButton = new JButton("Confirmar");
        JButton voltarButton = new JButton("Voltar");

        panel.add(Box.createVerticalStrut(10));
        panel.add(confirmarButton);
        panel.add(voltarButton);

        retiradaLocalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exibir endereço do restaurante cadastrado
                enderecoField.setText("Endereço do restaurante");
                enderecoField.setEnabled(false);
            }
        });

        entregaCasaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpar campo de endereço
                enderecoField.setText("");
                enderecoField.setEnabled(true);
            }
        });

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoRetirada = retiradaLocalButton.isSelected() ? "Retirada no local" : "Entrega em casa";
                String endereco = enderecoField.getText();

                // Processar as informações conforme necessário (não implementado nesta versão)
                JOptionPane.showMessageDialog(frame, "Tipo de retirada selecionado: " + tipoRetirada + "\nEndereço: " + endereco);
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Pedidos.criarPedidosGUI();
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
