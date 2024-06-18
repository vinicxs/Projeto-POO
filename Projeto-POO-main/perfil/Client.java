package perfil;

public class Client extends User {
    private String telefone;
    private int idade;
    private String endereco;

    public Client(String nome, String email, String senha, String telefone, int idade, String endereco) {
        super();
        this.telefone = telefone;
        this.idade = idade;
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String getTipo() {
        return "Cliente";
    }
}
