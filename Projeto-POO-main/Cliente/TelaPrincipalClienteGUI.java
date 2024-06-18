package Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipalClienteGUI extends JFrame {

    public TelaPrincipalClienteGUI(JFrame telaLogin) {
        super("Tela Principal do Cliente");

        JButton btnPesquisarRestaurantes = new JButton("Pesquisar Restaurantes");
        JButton btnPerfil = new JButton("Perfil");
        JButton btnPedidosRecentes = new JButton("Pedidos Recentes");
        JButton btnVoltar = new JButton("Voltar");

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adiciona borda
        panel.add(btnPesquisarRestaurantes);
        panel.add(btnPerfil);
        panel.add(btnPedidosRecentes);
        panel.add(btnVoltar);

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        // Evento de clique do botão de pesquisar restaurantes
        btnPesquisarRestaurantes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuscaRestaurantesGUI(TelaPrincipalClienteGUI.this);
                setVisible(false); // Esconde a tela principal do cliente, mas não a fecha
            }
        });

        // Evento de clique do botão de voltar
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaLogin.setVisible(true);
                dispose(); // Fechar a tela principal do cliente
            }
        });

        // Implementar eventos para btnPerfil e btnPedidosRecentes conforme necessário
    }

    public static void main(String[] args) {
        JFrame telaLogin = new JFrame(); // Substitua por sua tela de login
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipalClienteGUI(telaLogin);
            }
        });
    }
}
