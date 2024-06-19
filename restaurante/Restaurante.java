import java.io.*;

public class Restaurante {
    private String nome;
    private String senha;
    private String endereco;
    private String telefone;

    public Restaurante(String nome, String senha, String endereco, String telefone) {
        this.nome = nome;
        this.senha = senha;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public static boolean autenticar(String nome, String senha) {
        try (BufferedReader reader = new BufferedReader(new FileReader("restaurante\\DadosRest.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Nome: ")) {
                    String storedNome = line.substring(6);
                    String storedSenha = reader.readLine().substring(7); // Skip "Senha: " prefix
                    if (storedNome.equals(nome) && storedSenha.equals(senha)) {
                        return true;
                    }
                }
                // Skip the next three lines (endereco, telefone, and separator)
                reader.readLine();
                reader.readLine();
                reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void salvarDados(String nome, String senha, String endereco, String telefone) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DadosRest.txt", true))) {
            writer.write("Nome: " + nome + "\n");
            writer.write("Senha: " + senha + "\n");
            writer.write("Endereco: " + endereco + "\n");
            writer.write("Telefone: " + telefone + "\n");
            writer.write("------------------------------\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
