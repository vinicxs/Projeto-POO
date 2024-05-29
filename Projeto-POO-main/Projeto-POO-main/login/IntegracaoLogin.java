package login;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class IntegracaoLogin {

    // Método para verificar o login do usuário
    public static boolean verificarLogin(String email, String senha) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:C:/Users/Vinicius.chella/Documents/Projeto-POO-main/cadastro/Cadastros.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dadosUsuario = linha.split(",");
                if (dadosUsuario.length == 3 && dadosUsuario[1].equals(email) && dadosUsuario[2].equals(senha)) {
                    return true; // Usuário encontrado e senha correspondente
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao verificar login: " + e.getMessage());
        }
        return false; // Usuário não encontrado ou senha incorreta
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Login");
        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        if (verificarLogin(email, senha)) {
            System.out.println("Login bem-sucedido!");
        } else {
            System.out.println("Email ou senha incorretos. Tente novamente.");
        }

        scanner.close();
    }
}