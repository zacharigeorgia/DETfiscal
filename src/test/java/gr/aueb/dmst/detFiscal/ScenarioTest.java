package gr.aueb.dmst.detFiscal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Locale;

public class ScenarioTest {

    private Scenario scenario;
    private BudgetDetails details;

    @BeforeEach
    void setUp() {
        Locale.setDefault(Locale.US);
        scenario = new Scenario();
        
        // Χρησιμοποιούμε μόνο τη setGdp που είναι σίγουρη
        MacroData macro = new MacroData();
        macro.setGdp(200000.0); 
        
        BudgetSummary summary = new BudgetSummary();
        details = new BudgetDetails(macro, summary);
    }

    @Test
    @DisplayName("Έλεγχος αύξησης σε υπουργείο")
    void testAnalyzeScenarioIncrease() {
        String result = scenario.analyzeScenario("Υπουργείο Υγείας", 1000.0, 1500.0, false, details);
        
        assertNotNull(result);
        assertTrue(result.contains("500"), "Πρέπει να περιέχει τη διαφορά");
        assertTrue(result.contains("γιατρών") || result.contains("Αύξηση"));
    }

    @Test
    @DisplayName("Έλεγχος μείωσης σε υπουργείο")
    void testAnalyzeScenarioDecrease() {
        String result = scenario.analyzeScenario("Υπουργείο Υγείας", 1000.0, 800.0, false, details);
        
        assertNotNull(result);
        assertTrue(result.contains("200"));
        assertTrue(result.contains("νοσηλευτικό") || result.contains("Μείωση"));
    }

    @Test
    @DisplayName("Έλεγχος άγνωστης κατηγορίας")
    void testAnalyzeScenarioUnknown() {
        String result = scenario.analyzeScenario("Unknown Category", 100.0, 200.0, false, details);
        
        assertNotNull(result);
        // Ελέγχουμε αν επιστρέφει το βασικό report
        assertTrue(result.contains("ΑΝΑΛΥΣΗ"), "Πρέπει να περιέχει τον τίτλο του report");
        assertTrue(result.contains("100"), "Πρέπει να περιέχει τη μεταβολή ποσού");
    }
}
