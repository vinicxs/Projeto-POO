package main;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private User user;
    private List<MenuItem> itens;
    private double total;

    public Order(User user) {
        this.user = user;
        this.itens = new ArrayList<>();
        this.total = 0.0;
    }

    public void addItem(MenuItem item) {
        itens.add(item);
        total += item.getPreco();
    }

    public void displayOrder() {
        System.out.println("Pedido de " + user.getNome());
        for (MenuItem item : itens) {
            System.out.println(item.toString());
        }
        System.out.println("Total: R$ " + total);
    }

    public List<MenuItem> getItens() {
        return itens;
    }

    public double getTotal() {
        return total;
    }

    public User getUser() {
        return user;
    }
}
