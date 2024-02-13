import java.util.List;

public class Main {
    public static void main(String[] args) {
        Profile fila = new Profile("Filip", "Dvořák");
        Profile viktor = new Profile("Viktor", "Kavka");
        Profile adam = new Profile("Adam", "Müller");
        Profile jaroslav = new Profile("Jaroslav", "Požár");
        Profile ondrej =  new Profile("Ondřej", "Suchánek");
        Profile lubomír = new Profile("Lubomír", "Štecha");

        Csts.getNadchazejiciSouteze();
    }

}