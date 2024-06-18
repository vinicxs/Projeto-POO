package Cliente;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CadastroCliente {
    private static final String ARQUIVO_CLIENTE = "Cliente/bd/clientes.txt";

    public static boolean registrarCliente(String nome, String senha, String email, String telefone, String idade, String endereco) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_CLIENTE, true))) {
            writer.write(nome + "," + senha + "," + email + "," + telefone + "," + idade + "," + endereco);
            writer.newLine();
            return true;
        } catch (IOException ex) {
            System.out.println("Erro ao salvar os dados: " + ex.getMessage());
            return false;
        }
    }
}
