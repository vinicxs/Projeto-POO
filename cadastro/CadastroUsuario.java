package cadastro;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CadastroUsuario {
    /*Cadastra e armazenar usuario no arquivo txt */
    public static void cadastrarUsuario(String nome, String email, String senha) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/nicolas.bueno/OneDrive - Grupo Marista/programar/PjBL/banco de dados/Cadastros.txt", true))) {
            writer.write(nome + "," + email + "," + senha + "\n");
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Cadastro de Usuário");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        cadastrarUsuario(nome, email, senha);

        scanner.close();
    }
}
