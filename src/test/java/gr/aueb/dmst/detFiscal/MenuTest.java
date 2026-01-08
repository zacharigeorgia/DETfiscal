package gr.aueb.dmst.detFiscal;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class MenuTest {

    @Test
    @DisplayName("Έλεγχος Αρχικοποίησης Δεδομένων που ορίζονται στη Menu")
    void testMenuLogicAndFiles() {
        // Χρησιμοποιούμε τα ακριβή paths που υπάρχουν στη Menu.java
        String pathMain = "src/main/resources/data/sample_budget_2025.json";
        String path2024 = "src/main/resources/data/sample_budget_2024.json";

        FederalBudget fedBudget = FederalBudget.getInstance();
        
        // Αυτή η κλήση καλύπτει τη βασική λειτουργία που εκτελεί η Menu
        assertDoesNotThrow(() -> {
            fedBudget.initializeData(pathMain, path2024);
        });

        // Επαλήθευση ότι οι δείκτες λειτουργούν (καλύπτει κώδικα στη Menu και FederalBudget)
        assertNotNull(fedBudget.getDetails());
        assertTrue(fedBudget.getDetails().getInflation() >= 0);
    }
}
