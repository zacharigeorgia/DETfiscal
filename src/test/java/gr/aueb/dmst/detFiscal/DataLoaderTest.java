package gr.aueb.dmst.detFiscal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class DataLoaderTest {
    // Ορίζουμε τη διαδρομή του mock αρχείου
    private final String MOCK_FILE_PATH = "src/test/resources/testdata/mock_budget.json";
    // Φτιάχνουμε ένα αντικείμενο DataLoader για να το τεστάρουμε
    private DataLoader dataLoader = new DataLoader();
@Test
    public void testLoadRevenues() {
        // 1. Καλούμε τη μέθοδο
        List<Revenue> revenues = dataLoader.loadRevenues(MOCK_FILE_PATH);

        // 2. Ελέγχουμε τα αποτελέσματα (Assertions)
        assertNotNull(revenues, "Η λίστα εσόδων δεν πρέπει να είναι null");
        assertEquals(1, revenues.size(), "Πρέπει να υπάρχει ακριβώς 1 έσοδο (όπως στο mock)");

        Revenue r = revenues.get(0);
        assertEquals("Test Revenue", r.getName());
        assertEquals(2000.0, r.getAmount());
    }
@Test
    public void testLoadExpenditures() {
        //  Καλούμε τη μέθοδο για να φορτώσουμε το mock αρχείο
        List<Expenditure> expenditures = dataLoader.loadExpenditures(MOCK_FILE_PATH);

        //  Ελέγχουμε αν η λίστα δημιουργήθηκε σωστά
        assertNotNull(expenditures);  //Η λίστα εξόδων δεν πρέπει να είναι null
        assertEquals(1, expenditures.size()); //Πρέπει να υπάρχει ακριβώς 1 έξοδο, όπως ορίσαμε στο mock_budget.json

        // Παίρνουμε το μοναδικό αντικείμενο για να το ελέγξουμε αναλυτικά
        Expenditure e = expenditures.get(0);

        // Ελέγχουμε αν τα βασικά δεδομένα (Όνομα, Ποσό) είναι σωστά
        assertEquals("Test Expenditure", e.getName());  //Το όνομα έπρεπε να είναι 'Test Expenditure
        assertEquals(1000.0, e.getAmount());  //Το ποσό έπρεπε να είναι 1000.0

        // Ελέγχουμε αν φορτώθηκαν οι κανόνες περιορισμών

        assertFalse(e.isCanDecrease());   //Το canDecrease έπρεπε να διαβαστεί ως false από το αρχείο
        assertEquals(5.0, e.getMaxIncreasePercent()); //Το μέγιστο ποσοστό αύξησης έπρεπε να είναι 5.0
    }
    @Test
    public void testLoadMacroData() {
        // Καλούμε τη μέθοδο
        MacroData macro = dataLoader.loadMacroData(MOCK_FILE_PATH);

        // Το αντικείμενο δεν πρέπει να είναι κενό
        assertNotNull(macro);

        //  Check Summary Data (Τα γενικά σύνολα)
        assertEquals(2000.0, macro.getTotalRevenues());

        assertEquals(1000.0, macro.getTotalExpenditures());


        assertEquals(1000.0, macro.getBudgetResult()); //Το αποτέλεσμα προϋπολογισμού έπρεπε να είναι 1000.0

        //Οι παράμετροι για τα σενάρια)
        assertEquals(24.0, macro.getVatRatePercent());
        assertEquals(5000.0, macro.getBaseRevenueForVat());
        assertEquals(22.0, macro.getIncomeTaxRatePercent());
    }
}