package perfil;

public class ClientHandler extends AbstractUserHandler {
    @Override
    public void showUserDetails(User user) {
        if (user instanceof Client) {
            Client client = (Client) user;
            System.out.println("Nome: " + client.getNome());
            System.out.println("Email: " + client.getEmail());
            System.out.println("Telefone: " + client.getTelefone());
            System.out.println("Idade: " + client.getIdade());
            System.out.println("Endereço: " + client.getEndereco());
        }
    }
}
