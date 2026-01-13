package gr.aueb.dmst.detFiscal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class RevenueExpenditureTest {

    @Test
    @DisplayName("Έλεγχος Revenue: Setters, Getters και Υπολογισμοί")
    void testRevenueLogic() {
        Revenue r = new Revenue();
        r.setName("Φόρος Επιχειρήσεων");
        r.setAmount(1000.0);

        assertEquals("Φόρος Επιχειρήσεων", r.getName());
        assertEquals(1000.0, r.getAmount());

        r.increase(500.0);
        assertEquals(1500.0, r.getAmount());

        r.decrease(200.0);
        assertEquals(1300.0, r.getAmount());

        // Έλεγχος toString (για κάλυψη της String.format)
        assertNotNull(r.toString());
        assertTrue(r.toString().contains("Φόρος Επιχειρήσεων"));
    }

    @Test
    @DisplayName("Έλεγχος Expenditure: Setters, Getters και Υπολογισμοί")
    void testExpenditureLogic() {
        Expenditure e = new Expenditure();
        e.setName("Παιδεία");
        e.setAmount(2000.0);

        assertEquals("Παιδεία", e.getName());
        
        e.increase(1000.0);
        assertEquals(3000.0, e.getAmount());

        e.decrease(500.0);
        assertEquals(2500.0, e.getAmount());

        // Έλεγχος toString
        assertNotNull(e.toString());
        assertTrue(e.toString().contains("Παιδεία"));
    }
}