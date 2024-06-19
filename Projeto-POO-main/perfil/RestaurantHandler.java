package perfil;

public class RestaurantHandler extends AbstractUserHandler {
    public void showUserDetails(User user) {
        if (user instanceof Restaurant) {
            Restaurant restaurant = (Restaurant) user;
            System.out.println("Nome: " + restaurant.getNome());
            System.out.println("Email: " + restaurant.getEmail());
            System.out.println("Localização: " + restaurant.getLocalizacao());
            System.out.println("Tipo de Comida: " + restaurant.getTipoComida());
        }
    }
}
