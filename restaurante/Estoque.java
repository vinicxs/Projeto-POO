import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Estoque {

    private List<String> pratos;
    private JFrame mainFrame;

    public Estoque(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.pratos = carregarPratos();
    }

    private List<String> carregarPratos() {
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

    public void adicionarPrato(String prato) {
        pratos.add(prato);
        salvarPratos();
    }

    public void excluirPrato(String prato) {
        pratos.remove(prato);
        salvarPratos();
    }

    private void salvarPratos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("PratosRest.txt"))) {
            for (String prato : pratos) {
                writer.write(prato);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getPratos() {
        return pratos;
    }

    public void exibirInterface() {
        JFrame estoqueFrame = new JFrame("Estoque");
        estoqueFrame.setSize(400, 300);
        estoqueFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        estoqueFrame.setLocationRelativeTo(mainFrame);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel("Gestão de Estoque");
        panel.add(label);

        JTextField pratoField = new JTextField();
        pratoField.setMaximumSize(new Dimension(300, 30));
        panel.add(pratoField);

        JButton adicionarButton = new JButton("Adicionar Prato");
        JButton excluirButton = new JButton("Excluir Prato");
        JButton pesquisarButton = new JButton("Pesquisar Prato");

        panel.add(adicionarButton);
        panel.add(excluirButton);
        panel.add(pesquisarButton);

        // Adicionando uma JList para mostrar os pratos
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> pratosList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(pratosList);
        scrollPane.setPreferredSize(new Dimension(300, 150));
        panel.add(scrollPane);

        // Atualizar a lista de pratos na interface
        pratos.forEach(listModel::addElement);

        adicionarButton.addActionListener(e -> {
            String prato = pratoField.getText();
            if (!prato.isEmpty()) {
                adicionarPrato(prato);
                listModel.addElement(prato);
                JOptionPane.showMessageDialog(estoqueFrame, "Prato adicionado com sucesso.");
                pratoField.setText("");
            }
        });

        excluirButton.addActionListener(e -> {
            String prato = pratoField.getText();
            if (!prato.isEmpty() && pratos.contains(prato)) {
                excluirPrato(prato);
                listModel.removeElement(prato);
                JOptionPane.showMessageDialog(estoqueFrame, "Prato excluído com sucesso.");
                pratoField.setText("");
            } else {
                JOptionPane.showMessageDialog(estoqueFrame, "Erro: Prato não encontrado.");
            }
        });

        pesquisarButton.addActionListener(e -> {
            String prato = pratoField.getText();
            if (pratos.contains(prato)) {
                JOptionPane.showMessageDialog(estoqueFrame, "Prato encontrado: " + prato);
            } else {
                JOptionPane.showMessageDialog(estoqueFrame, "Prato não encontrado.");
            }
        });

        JButton voltarButton = new JButton("Voltar");
        panel.add(voltarButton);

        voltarButton.addActionListener(e -> {
            estoqueFrame.dispose(); // Fechar a tela de Estoque
            mainFrame.setVisible(true); // Mostrar a tela principal
        });

        estoqueFrame.add(panel);
        estoqueFrame.setVisible(true);
    }
}
