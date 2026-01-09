package gr.aueb.dmst.detFiscal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BudgetCountriesComparatorTest {

    @DisplayName("Coverage Test: Έλεγχος όλων των συνθηκών (if/else/equal)")
    @ParameterizedTest(name = "Run {index}: Inflation={0}, GDP={1}, Debt={2}")
    @CsvSource({
        // Σενάριο 1: Τιμές μεγαλύτερες από όλες τις χώρες (μπαίνει στα πρώτα 'if')
        "10.0, 10.0, 200.0, 30.0, 50.0, 500000000000.0, 600000000000.0",
        
        // Σενάριο 2: Τιμές μικρότερες από όλες τις χώρες (μπαίνει στα 'else if')
        "0.5, -1.0, 10.0, 5.0, 5.0, 1000000000.0, 1000000000.0",
        
        // Σενάριο 3: Τιμές ακριβώς ίσες με κάποιες χώρες (π.χ. Γερμανία) για να μπει στα 'else'
        "2.3, 0.2, 63.5, 19.0, 45.0, 200000000000.0, 450000000000.0"
    })
    void testFullCoverage(double inf, double gdp, double debt, double vat, double tax, double baseVat, double baseInc) {
        // Δημιουργία δεδομένων με τις τιμές της παραμέτρου
        MacroData data = new MacroData();
        data.setInflation(inf);
        data.setGdp(gdp);
        data.setDebtRatio(debt);
        data.setVatRatePercent(vat);
        data.setIncomeTaxRatePercent(tax);
        data.setBaseRevenueForVat(baseVat);
        data.setBaseRevenueForIncomeTax(baseInc);

        BudgetCountriesComparator comparator = new BudgetCountriesComparator(data);
        
        // Η κλήση αυτή θα "περάσει" από όλες τις γραμμές του κώδικα λόγω των 3 διαφορετικών σεναρίων
        assertDoesNotThrow(() -> comparator.compareCountriesMacro());
    }
}
