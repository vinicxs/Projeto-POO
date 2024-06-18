package src.Cliente;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CadastroCliente {

    private static final String PASTA_CLIENTE = "Cliente/";
    private static final String ARQUIVO_CLIENTE = PASTA_CLIENTE + "bd/clientes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Cadastro de Cliente");

        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();

        System.out.print("Senha: ");
        String senha = scanner.nextLine().trim();

        System.out.print("Confirma Senha: ");
        String confirmaSenha = scanner.nextLine().trim();

        if (!senha.equals(confirmaSenha)) {
            System.out.println("As senhas não conferem. Tente novamente.");
            return;
        }

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine().trim();

        if (!telefone.matches("\\d+")) {
            System.out.println("Telefone deve conter apenas números.");
            return;
        }

        System.out.print("Idade: ");
        String idade = scanner.nextLine().trim();

        if (!idade.matches("\\d+")) {
            System.out.println("Idade deve conter apenas números.");
            return;
        }

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine().trim();

        System.out.println("Confirmação de Cadastro:");
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Telefone: " + telefone);
        System.out.println("Idade: " + idade);
        System.out.println("Endereço: " + endereco);
        System.out.print("Confirmar cadastro? (s/n): ");
        String resposta = scanner.nextLine().trim();

        if (resposta.equalsIgnoreCase("s")) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_CLIENTE, true))) {
                writer.write(nome + "," + senha + "," + email + "," + telefone + "," + idade);
                writer.newLine();
                System.out.println("Cadastro realizado com sucesso!");
            } catch (IOException ex) {
                System.out.println("Erro ao salvar os dados: " + ex.getMessage());
            }
        } else {
            System.out.println("Cadastro cancelado.");
        }

        scanner.close();
    }
}
