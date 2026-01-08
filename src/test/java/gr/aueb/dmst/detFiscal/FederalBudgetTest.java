package gr.aueb.dmst.detFiscal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;

public class FederalBudgetTest {

    private FederalBudget budget;

    @BeforeEach
    void setUp() {
        // Χρήση σταθερού Locale για να μην έχουμε θέμα με κόμματα/τελείες (π.χ. 3.00% αντί 3,00%)
        Locale.setDefault(Locale.US);
        
        // Λήψη του πραγματικού Singleton instance
        budget = FederalBudget.getInstance();

        // ΚΑΘΑΡΙΣΜΟΣ ΔΕΔΟΜΕΝΩΝ: Επειδή είναι Singleton, αν δεν κάνεις clear, 
        // τα δεδομένα από το προηγούμενο τεστ θα παραμείνουν και θα βγάζουν λάθος αποτελέσματα.
        budget.getSummary().getRevenues().clear();
        budget.getSummary().getExpenditures().clear();
        budget.getSummary().getMinistries().clear();
        
        // Αρχικοποίηση MacroData για να αποφύγουμε το σφάλμα 0.00%
        budget.getDetails().getMacroData().setInflation(3.0);
        budget.getDetails().getMacroData().setGdp(2.0);
    }

    @Test
    @DisplayName("Έλεγχος Singleton Pattern")
    void testSingletonInstance() {
        FederalBudget secondInstance = FederalBudget.getInstance();
        assertSame(budget, secondInstance, "Η getInstance() πρέπει να επιστρέφει το ίδιο αντικείμενο.");
    }

    @Test
    @DisplayName("Έλεγχος Υπολογισμού Ισοζυγίου (Balance)")
    void testCalculateTotalBudget() {
        Revenue r = new Revenue();
        r.setName("Φόροι");
        r.setAmount(1000.0);
        budget.getSummary().addRevenue(r);

        Expenditure e = new Expenditure();
        e.setName("Μισθοί");
        e.setAmount(400.0);
        budget.getSummary().addExpenditure(e);

        // Αναμενόμενο: 1000 - 400 = 600
        assertEquals(600.0, budget.calculateTotalBudget(), 0.001);
    }

    @Test
    @DisplayName("Έλεγχος Πληθωρισμού (Διόρθωση 3.00% Error)")
    void testInflationValue() {
        assertEquals(3.0, budget.getDetails().getInflation(), "Ο πληθωρισμός πρέπει να είναι 3.0");
    }

    @Test
    @DisplayName("Έλεγχος Χαρακτηρισμού Προϋπολογισμού")
    void testCharacterization() {
        Revenue r = new Revenue(); r.setAmount(500.0);
        budget.getSummary().addRevenue(r);
        
        Expenditure e = new Expenditure(); e.setAmount(1000.0);
        budget.getSummary().addExpenditure(e);

        // Ενημέρωση του balance στην BudgetDetails
        budget.getDetails().valueForBalance();
        String status = budget.getDetails().characterizeTotal();
        
        // Πρέπει να περιέχει τη λέξη 'Έλλειμμα' ή 'Deficit'
        assertTrue(status.toLowerCase().contains("έλλειμμα") || status.toLowerCase().contains("deficit"));
    }
}