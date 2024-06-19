import javax.swing.*;

public class Pagamento {

    public void exibirInterface(JFrame mainFrame) {
        JFrame pagamentoFrame = new JFrame("Pagamento");
        pagamentoFrame.setSize(400, 300);
        pagamentoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pagamentoFrame.setLocationRelativeTo(mainFrame);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel("Selecione o método de pagamento:");
        panel.add(label);

        String[] metodos = {"Pix", "Cartão de Crédito", "Cartão de Débito"};
        JComboBox<String> metodosComboBox = new JComboBox<>(metodos);
        panel.add(metodosComboBox);

        JButton confirmarButton = new JButton("Confirmar");
        JButton cancelarButton = new JButton("Cancelar");

        panel.add(confirmarButton);
        panel.add(cancelarButton);

        confirmarButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(pagamentoFrame, "Operação concluída com sucesso.");
            pagamentoFrame.dispose(); // Fechar a tela de Pagamento
            mainFrame.setVisible(true); // Mostrar a tela de Funcionalidades
        });

        cancelarButton.addActionListener(e -> {
            pagamentoFrame.dispose(); // Fechar a tela de Pagamento
            mainFrame.setVisible(true); // Mostrar a tela de Funcionalidades
        });

        pagamentoFrame.add(panel);
        pagamentoFrame.setVisible(true);
    }
}
