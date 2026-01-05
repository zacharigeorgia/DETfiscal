package gr.aueb.dmst.detFiscal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class CountryComparisonWindowTest {

    private FederalBudget budget;

    @BeforeEach
    public void setUp() {
        budget = FederalBudget.getInstance();

        // Καθαρίζουμε / ορίζουμε γνωστές τιμές στο MacroData για την Ελλάδα
        // (χρησιμοποιούμε τα υπάρχοντα setters που έχεις στην MacroData)
        BudgetDetails details = budget.getDetails();
        // Αν BudgetDetails κρατάει MacroData εσωτερικά, υπάρχει getMacroData()
        MacroData md = details.getMacroData();

        md.setInflation(3.00);                // 3%
        md.setGdp(1.50);                      // 1.5%
        md.setDebtRatio(60.0);                // 60%
        md.setVatRatePercent(24.0);           // 24%
        md.setIncomeTaxRatePercent(35.0);     // 35%
        md.setBaseRevenueForVat(100000.0);    // 100k €
        md.setBaseRevenueForIncomeTax(200000.0);// 200k €
    }

    // helper για πρόσβαση στο private table με reflection
    private JTable getPrivateTable(CountryComparisonWindow window) throws Exception {
        Field f = CountryComparisonWindow.class.getDeclaredField("table");
        f.setAccessible(true);
        return (JTable) f.get(window);
    }

    @Test
    public void testTableIsFilledWithExpectedRowsAndValues() throws Exception {
        // Δημιουργία παραθύρου — δεν εμφανίζει τίποτα (no setVisible(true) στο constructor).
        CountryComparisonWindow window = new CountryComparisonWindow(budget);

        JTable table = getPrivateTable(window);
        assertNotNull(table, "Το JTable πρέπει να υπάρχει");

        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Η fillTableData προσθέτει 7 γραμμές (όπως στην υλοποίηση)
        assertEquals(7, model.getRowCount(), "Ο πίνακας πρέπει να έχει 7 γραμμές (7 δείκτες)");

        // Έλεγχος γραμμής 0 = Πληθωρισμός
        assertEquals("Πληθωρισμός", model.getValueAt(0, 0));
        assertEquals("3,00%", model.getValueAt(0, 1));   // Ελλάδα (setUp)
        assertEquals(String.format("%.2f%%", BudgetCountriesComparator.INFLATION_VALUES.get("Germany")),
                     model.getValueAt(0, 2));           // Γερμανία από static map

        // Έλεγχος γραμμής 1 = ΑΕΠ (Ρυθμός Ανάπτυξης)
        assertEquals("ΑΕΠ (Ρυθμός Ανάπτυξης)", model.getValueAt(1, 0));
        assertEquals("1.50%", model.getValueAt(1, 1));
        assertEquals(String.format("%.2f%%", BudgetCountriesComparator.GDP_VALUES.get("Germany")),
                     model.getValueAt(1, 2));

        // Έλεγχος γραμμής 5 = Βάση Εσόδων ΦΠΑ (μονάδα = €)
        assertEquals("Βάση Εσόδων ΦΠΑ", model.getValueAt(5, 0));
        assertEquals("100000.00€", model.getValueAt(5, 1)); // Ελλάδα όπως το setUp
        assertEquals(String.format("%.2f€", BudgetCountriesComparator.BASEREVENUEVAT_VALUES.get("Germany")),
                     model.getValueAt(5, 2));

        // Έλεγχος γραμμής 6 = Βάση Εσόδων Φορ. Εισ.
        assertEquals("Βάση Εσόδων Φορ. Εισ.", model.getValueAt(6, 0));
        assertEquals("200000.00€", model.getValueAt(6, 1));
        assertEquals(String.format("%.2f€", BudgetCountriesComparator.BASEREVENUEINCOME_VALUES.get("Germany")),
                     model.getValueAt(6, 2));
    }
}
