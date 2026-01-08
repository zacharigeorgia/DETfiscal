package gr.aueb.dmst.detFiscal;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.Locale;

public class BudgetEditWindowTest {

    private FederalBudget budget;
    private ChangeLog changeLog;
    private BudgetEditWindow editWindow;

    @BeforeEach
    void setUp() {
        // Επιβολή Locale για σωστά νούμερα
        Locale.setDefault(Locale.US);
        
        // Λήψη του Singleton instance
        budget = FederalBudget.getInstance();
        
        // Αρχικοποίηση δεδομένων αν δεν έχουν ήδη φορτωθεί (mock ή sample data)
        // Σημείωση: Αν η initializeData απαιτεί αρχεία που δεν υπάρχουν στο test environment,
        // βεβαιώσου ότι το budget object έχει έστω κενές λίστες.
        if (budget.getSummary() == null) {
            budget.initializeData("src/main/resources/data/sample_budget_2025.json", null);
        }
        
        changeLog = new ChangeLog();
        
        // Δημιουργία του window (χωρίς απαραίτητα να γίνει setVisible(true))
        editWindow = new BudgetEditWindow(budget, changeLog);
    }

    @Test
    @DisplayName("Έλεγχος σύνδεσης Window με ChangeLog")
    void testChangeLogIntegration() {
        // Καταγράφουμε μια αλλαγή μέσω του ChangeLog που δώσαμε στο παράθυρο
        changeLog.addChange("Τεστ Λογαριασμός", "Έσοδα", 1000.0, 1200.0);
        
        String logContent = changeLog.getFormattedLog();
        assertTrue(logContent.contains("Τεστ Λογαριασμός"), "Το ChangeLog πρέπει να καταγράφει τις αλλαγές.");
        assertTrue(logContent.contains("1200.00"), "Η νέα τιμή πρέπει να φαίνεται στο log.");
    }

    @Test
    @DisplayName("Έλεγχος σωστής φόρτωσης τύπων δεδομένων")
    void testInitializationOfComponents() {
        assertNotNull(editWindow, "Το BudgetEditWindow πρέπει να δημιουργείται σωστά.");
        assertEquals("Επεξεργασία Προϋπολογισμού - DETfiscal", editWindow.getTitle());
    }

    @Test
    @DisplayName("Έλεγχος αλληλεπίδρασης με Scenario Analyzer")
    void testScenarioAnalysisTrigger() {
        // Δοκιμάζουμε απευθείας τη λογική του Scenario που χρησιμοποιεί το Window
        Scenario scenario = new Scenario();
        BudgetDetails details = budget.getDetails();
        
        // Προσομοίωση επεξεργασίας ενός υπουργείου
        String analysis = scenario.analyzeScenario("Υπουργείο Υγείας", 1000.0, 1500.0, false, details);
        
        assertNotNull(analysis);
        assertTrue(analysis.contains("ΑΝΑΛΥΣΗ ΕΠΙΠΤΩΣΕΩΝ"));
        assertTrue(analysis.contains("γιατρών"), "Πρέπει να εμφανίζει το κοινωνικό αντίκτυπο για την Υγεία.");
    }
}
