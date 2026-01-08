package gr.aueb.dmst.detFiscal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class CountryComparisonWindowTest {

    private CountryComparisonWindow window;
    private FederalBudget budget;

    @BeforeEach
    void setUp() {
        budget = FederalBudget.getInstance();

        // Καθαρίζουμε και βάζουμε γνωστές τιμές στο MacroData του budget.getDetails()
        MacroData md = new MacroData();
        md.setInflation(3.0);
        md.setGdp(1.5);
        md.setDebtRatio(60.0);
        md.setVatRatePercent(24.0);
        md.setIncomeTaxRatePercent(35.0);
        md.setBaseRevenueForVat(100000.0);
        md.setBaseRevenueForIncomeTax(200000.0);

        // Σιγουρευόμαστε ότι οι static maps έχουν τιμές (τις έχεις ήδη, αλλά επαναπροσδιορίζουμε για test isolation)
        // (Αν οι maps είναι final, μην ξαναδηλώσεις — μόνο clear/put)
        // Εδώ απλά βάζουμε κάποια δεδομένα (αν δεν είναι final)
        BudgetCountriesComparator.INFLATION_VALUES.put("Germany", 2.0);
        BudgetCountriesComparator.GDP_VALUES.put("Germany", 0.2);
        BudgetCountriesComparator.DEBTRATIO_VALUES.put("Germany", 63.5);
        BudgetCountriesComparator.VATRATEPERCENT_VALUES.put("Germany", 19.0);
        BudgetCountriesComparator.INCOMETAXRATEPERCENT_VALUES.put("Germany", 45.0);
        BudgetCountriesComparator.BASEREVENUEVAT_VALUES.put("Germany", 200000000000.0);
        BudgetCountriesComparator.BASEREVENUEINCOME_VALUES.put("Germany", 450000000000.0);

        // Δημιουργία παραθύρου
        window = new CountryComparisonWindow(budget);
    }

    private JTable getPrivateTable() throws Exception {
        Field f = CountryComparisonWindow.class.getDeclaredField("table");
        f.setAccessible(true);
        return (JTable) f.get(window);
    }

    @Test
    void testTableIsFilledWithExpectedRowsAndValues() throws Exception {
        JTable table = getPrivateTable();
        TableModel model = table.getModel();

        assertEquals(7, model.getRowCount(), "Πρέπει να υπάρχουν 7 δείκτες");

        // Πληθωρισμός για Ελλάδα (col 1)
        String inflationGreece = model.getValueAt(0, 1).toString();
        // Δεκτά formats 3.00% ή 3,00%
        assertTrue(inflationGreece.matches("\\s*3[.,]00%\\s*") || inflationGreece.matches("\\s*3[.,]?0?%\\s*"),
                "Η τιμή πληθωρισμού πρέπει να είναι 3.00% ή 3,00% — βρέθηκε: " + inflationGreece);

        // Germany (col 2) από τον static map
        String germanyInfl = model.getValueAt(0, 2).toString();
        assertTrue(germanyInfl.contains("2") && germanyInfl.endsWith("%"));
    }
}


