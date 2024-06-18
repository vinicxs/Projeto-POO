package perfil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuscaRestaurantesGUI extends JFrame {
    private JTextField txtBusca;
    private JTextArea txtResultado;
    private JButton btnBuscar;

    public BuscaRestaurantesGUI() {
        setTitle("Busca de Restaurantes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        txtBusca = new JTextField();
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        btnBuscar = new JButton("Buscar");

        add(txtBusca, BorderLayout.NORTH);
        add(new JScrollPane(txtResultado), BorderLayout.CENTER);
        add(btnBuscar, BorderLayout.SOUTH);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarRestaurantes();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void buscarRestaurantes() {
        String busca = txtBusca.getText().trim();
        if (busca.isEmpty()) {
            txtResultado.setText("Por favor, insira um termo de busca.");
            return;
        }

        // Lógica de busca de restaurantes
        RestaurantHandler restaurantHandler = new RestaurantHandler();
        User restaurante = restaurantHandler.findUser(busca);
        if (restaurante != null && restaurante instanceof Restaurant) {
            txtResultado.setText("Restaurante encontrado:\n" + restaurante.toString());
        } else {
            txtResultado.setText("Nenhum restaurante encontrado.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BuscaRestaurantesGUI();
            }
        });
    }
}
