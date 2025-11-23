package gr.aueb.dmst.detFiscal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

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
}