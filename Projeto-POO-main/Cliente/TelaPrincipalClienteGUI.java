package Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipalClienteGUI extends JFrame {

    public TelaPrincipalClienteGUI() {
        super("Tela Principal do Cliente");

        // Criando botões
        JButton btnPesquisarRestaurantes = new JButton("Pesquisar Restaurantes");
        JButton btnPerfil = new JButton("Perfil");
        JButton btnPedidosRecentes = new JButton("Pedidos Recentes");

        // Configurando layout
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.add(btnPesquisarRestaurantes);
        panel.add(btnPerfil);
        panel.add(btnPedidosRecentes);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        // Adicionando ações aos botões (os métodos abrirPesquisarRestaurantes, abrirPerfil e abrirPedidosRecentes serão implementados posteriormente)
        btnPesquisarRestaurantes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirPesquisarRestaurantes();
            }
        });

        btnPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirPerfil();
            }
        });

        btnPedidosRecentes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirPedidosRecentes();
            }
        });
    }

    private void abrirPesquisarRestaurantes() {
        // Implementar a lógica para abrir a tela de pesquisa de restaurantes
    }

    private void abrirPerfil() {
        // Implementar a lógica para abrir a tela de perfil do cliente
    }

    private void abrirPedidosRecentes() {
        // Implementar a lógica para abrir a tela de pedidos recentes do cliente
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipalClienteGUI().setVisible(true);
            }
        });
    }
}
