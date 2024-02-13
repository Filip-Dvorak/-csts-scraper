import java.util.List;

public class Main {
    public static void main(String[] args) {

        String idt=Csts.getProfileIDT("Filip", "Dvořák");

        Profile profile = new Profile("Filip", "Dvořák");
        System.out.println(profile.divize.nazev);

        List<Soutez> souteze= Csts.getSouteze(idt);
        for (Soutez soutez : souteze) {
            System.out.println(soutez.Nazev + " " + soutez.Kategorie);
        }

        int sum=0;
        for(Soutez soutez : souteze){
            sum = sum + soutez.PoradiOd;
        }
        double averagePlace=sum/souteze.size();
        System.out.println("Avg. place: "+averagePlace);


    }

}