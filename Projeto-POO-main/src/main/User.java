package main;

public class User extends Person {
    private String senha;
    private String telefone;
    private String idade;
    private String endereco;

    public User(String nome, String email, String senha, String telefone, String idade, String endereco) {
        super(nome, email);
        this.senha = senha;
        this.telefone = telefone;
        this.idade = idade;
        this.endereco = endereco;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public void displayInfo() {
        System.out.println("Nome: " + getNome());
        System.out.println("Email: " + getEmail());
        System.out.println("Telefone: " + telefone);
        System.out.println("Idade: " + idade);
        System.out.println("Endereço: " + endereco);
    }
}
