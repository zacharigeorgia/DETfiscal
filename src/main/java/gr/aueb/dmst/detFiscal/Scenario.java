package gr.aueb.dmst.detFiscal;
import java.util.HashMap;
import java.util.Map;

public class Scenario {
    // Χάρτης: Κατηγορία -> [μήνυμα μείωσης, μήνυμα αύξησης]
    private Map<String, String[]> impactMap;

    public Scenario() {
        this.impactMap = new HashMap<>();
        initializeImpacts();
    }
}