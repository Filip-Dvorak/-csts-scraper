import com.google.gson.Gson;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Profile {
    public String idtClena;
    public String jmeno;
    public String prijmeni;
    public String vekKtg;
    public Klub klub;
    public Divize divize;
    public Profile(String jmeno,String prijmeni){
        String idt = Csts.getProfileIDT(jmeno,prijmeni);
        try {
            InputStream input;
            input =new URL("https://www.csts.cz/api/evidence/clenove/detail-clena/osobni-udaje/" + idt ).openStream();
            String json = new Scanner(input).useDelimiter("\\A").next();
            Gson gson = new Gson();
            Profile profile = gson.fromJson(json, Profile.class);
            this.idtClena = profile.idtClena;
            this.jmeno = profile.jmeno;
            this.prijmeni = profile.prijmeni;
            this.vekKtg = profile.vekKtg;
            this.klub = profile.klub;
            this.divize = profile.divize;
        }
        catch (Exception e){
            //handle exception
        }
    }
    public double averageResult(String kat){
        double sum = 0;
        double count = 0;
        for(Soutez soutez : Csts.getSouteze(this.idtClena)) {
            if (soutez.Kategorie.equals(kat)) {
                sum = sum + soutez.PoradiOd;
                count ++;
            }
        }
        return sum/count;
    }
    public double averageResult(){
        double sum = 0;
        double count = 0;
        for(Soutez soutez : Csts.getSouteze(this.idtClena)) {
                sum = sum + soutez.PoradiOd;
                count ++;
        }
        return sum/count;
    }
}
