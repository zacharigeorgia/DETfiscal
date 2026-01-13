package gr.aueb.dmst.detFiscal;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BudgetSummaryTest {

    private BudgetSummary summary;

    @BeforeEach
void setUp() {
    summary = new BudgetSummary();
    
    // Χρήση κενού constructor και μετά setters
    Revenue r = new Revenue();
    r.setName("ΦΠΑ");
    r.setAmount(500.0); // Εδώ δίνουμε την τιμή που περιμένει το assertEquals
    summary.addRevenue(r);

    Expenditure e = new Expenditure();
    e.setName("Μισθοί");
    e.setAmount(300.0);
    summary.addExpenditure(e);

    Ministry m = new Ministry();
    m.setName("Υπουργείο Υγείας");
    summary.addMinistry(m);
}
    @Test
    void testSearchAccount() {
        // 1. Έλεγχος εύρεσης στα Έσοδα (Revenues)
        Account rev = summary.searchAccount("ΦΠΑ");
        assertNotNull(rev, "Θα έπρεπε να βρει το έσοδο 'ΦΠΑ'");
        assertEquals(500.0, rev.getAmount());

        // 2. Έλεγχος εύρεσης στα Έξοδα (Expenditures)
        Account exp = summary.searchAccount("Μισθοί");
        assertNotNull(exp, "Θα έπρεπε να βρει το έξοδο 'Μισθοί'");

        // 3. Έλεγχος περίπτωσης που δεν υπάρχει (Return null)
        assertNull(summary.searchAccount("Ανύπαρκτο"), "Θα έπρεπε να επιστρέψει null");
    }

    @Test
    void testSearchMinistry() {
        // 1. Έλεγχος εύρεσης με ακριβές όνομα (equalsIgnoreCase)
        Ministry m1 = summary.searchMinistry("Υπουργείο Υγείας");
        assertNotNull(m1);

        // 2. Έλεγχος εύρεσης με μέρος του ονόματος (contains)
        Ministry m2 = summary.searchMinistry("Υγείας");
        assertNotNull(m2, "Θα έπρεπε να το βρει μέσω της contains()");

        // 3. Έλεγχος περίπτωσης που δεν υπάρχει
        assertNull(summary.searchMinistry("Υπουργείο Άμυνας"));
    }
}