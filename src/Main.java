import java.util.List;

public class Main {
    public static void main(String[] args) {
        Profile fila = new Profile("Filip", "Dvořák");
        Profile viktor = new Profile("Viktor", "Kavka");
        Profile adam = new Profile("Adam", "Müller");
        Profile jaroslav = new Profile("Jaroslav", "Požár");
        Profile ondrej =  new Profile("Ondřej", "Suchánek");
        Profile lubomír = new Profile("Lubomír", "Štecha");

        System.out.println("Fila average result: " + fila.averageResult("Dospělí-B-LAT"));
        System.out.println("Viktor average result: " + viktor.averageResult("Dospělí-B-LAT"));
        System.out.println("Adam average result: " + adam.averageResult("Dospělí-B-LAT"));
        System.out.println("Jaroslav average result: " + jaroslav.averageResult("Dospělí-B-LAT"));
        System.out.println("Ondrej average result: " + ondrej.averageResult("Dospělí-B-LAT"));
        System.out.println("Lubomír average result: " + lubomír.averageResult("Dospělí-B-LAT"));
    }

}