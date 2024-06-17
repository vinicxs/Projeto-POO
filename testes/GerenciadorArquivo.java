package testes;
import java.io.*;

public class GerenciadorArquivo {

    // Método para ler o conteúdo do arquivo
    public static String lerArquivo(String nomeArquivo) {
        StringBuilder conteudo = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return conteudo.toString();
    }

    // Método para escrever no arquivo
    public static void escreverArquivo(String nomeArquivo, String texto) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
            bw.write(texto);
            System.out.println("Conteúdo gravado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String nomeArquivo = "C:/Users/nicolas.bueno/OneDrive - Grupo Marista/programar/PjBL/banco de dados/bd.txt";
        String conteudo = lerArquivo(nomeArquivo);
        System.out.println("Conteúdo do arquivo:");
        System.out.println(conteudo);

        // Exemplo de texto para escrever no arquivo
        String novoConteudo = "Novo conteúdo a ser adicionado ao arquivo.\n";
        escreverArquivo(nomeArquivo, novoConteudo);

        // Exibindo o conteúdo atualizado do arquivo após a escrita
        conteudo = lerArquivo(nomeArquivo);
        System.out.println("\nConteúdo do arquivo após escrita:");
        System.out.println(conteudo);
    }
}
