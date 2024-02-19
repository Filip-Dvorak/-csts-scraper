import java.util.Map;
import java.util.Set;

public class Soutez {
    public String nazev;
    public Map<String, Set<Par>> kategorie;

    public Soutez(String nazev ,Map<String, Set<Par>> kategorie) {
        this.kategorie = kategorie;
        this.nazev = nazev;
    }
}
