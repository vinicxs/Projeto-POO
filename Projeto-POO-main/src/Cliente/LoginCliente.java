package src.Cliente;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LoginCliente {

    private static final String PASTA_CLIENTE = "cliente/";
    private static final String ARQUIVO_CLIENTE = PASTA_CLIENTE + "clientes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Login do Cliente");

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Senha: ");
        String senha = scanner.nextLine().trim();

        if (verificarCredenciais(email, senha)) {
            System.out.println("Login efetuado com sucesso!");
            abrirTelaPrincipalCliente();
        } else {
            System.out.println("Credenciais inválidas. Tente novamente.");
        }

        scanner.close();
    }

    // Método para verificar as credenciais do cliente
    private static boolean verificarCredenciais(String email, String senha) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_CLIENTE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length >= 5) {
                    String emailCadastrado = dados[2];
                    String senhaCadastrada = dados[1];
                    if (emailCadastrado.equals(email) && senhaCadastrada.equals(senha)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao verificar as credenciais: " + e.getMessage());
        }
        return false;
    }

    // Método para simular a abertura da tela principal do cliente
    private static void abrirTelaPrincipalCliente() {
        System.out.println("Bem-vindo à tela principal do cliente!");

    }
}
