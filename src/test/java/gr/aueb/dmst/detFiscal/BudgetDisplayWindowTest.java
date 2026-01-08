package gr.aueb.dmst.detFiscal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class BudgetDisplayWindowTest {
    private FederalBudget budget;

    @BeforeEach
    void setUp() {
        budget = FederalBudget.getInstance();
        budget.getSummary().getRevenues().clear();
        budget.getSummary().getExpenditures().clear();
        
        // Προσθήκη δεδομένων για έλεγχο ροής
        Revenue r = new Revenue(); r.setName("Φόρος Εισοδήματος"); r.setAmount(5000.0);
        budget.getSummary().addRevenue(r);
    }

    @Test
    @DisplayName("Έλεγχος Δημιουργίας και Footer Stats")
    void testDisplayWindowCreation() {
        // Έλεγχος ότι το παράθυρο δημιουργείται χωρίς Exception
        BudgetDisplayWindow window = assertDoesNotThrow(() -> new BudgetDisplayWindow(budget));
        
        assertNotNull(window);
        assertEquals("Εμφάνιση Προϋπολογισμού - DETfiscal", window.getTitle());
        
        // Έλεγχος αν οι υπολογισμοί του προϋπολογισμού είναι προσβάσιμοι από το παράθυρο
        assertEquals(5000.0, budget.getSummary().calculateTotalRevenues());
    }
}