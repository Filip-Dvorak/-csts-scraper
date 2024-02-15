import java.util.List;

public class Main {
    public static void main(String[] args) {
        Profile fila = new Profile("Filip", "Dvořák");
        Profile viktor = new Profile("Viktor", "Kavka");
        Profile adam = new Profile("Adam", "Müller");
        Profile jaroslav = new Profile("Jaroslav", "Požár");
        Profile ondrej =  new Profile("Ondřej", "Suchánek");
        Profile lubomír = new Profile("Lubomír", "Štecha");
        Profile azulin = new Profile("Anežka", "Augustinová");
        Profile filip = new Profile("Filip", "Süsmilich");

        System.out.println(filip.averageResult("Dospělí-B-LAT"));

        Csts.getNadchazejiciSouteze();
    }

}