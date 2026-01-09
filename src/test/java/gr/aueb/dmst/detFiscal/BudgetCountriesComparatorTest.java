package gr.aueb.dmst.detFiscal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BudgetCountriesComparatorTest {

    @DisplayName("Full Coverage Test: Έλεγχος getComparisonReport με διάφορα σενάρια")
    @ParameterizedTest(name = "Run {index}: Inf={0}, GDP={1}, Debt={2}")
    @CsvSource({
        // Σενάριο 1: Τιμές μεγαλύτερες από Γερμανία/Βουλγαρία/Ιταλία/Σερβία (μπαίνει στα if)
        "10.0, 5.0, 200.0, 25.0, 50.0",
        
        // Σενάριο 2: Τιμές μικρότερες από όλες τις χώρες (μπαίνει στα else if)
        "0.1, -2.0, 5.0, 5.0, 5.0",
        
        // Σενάριο 3: Τιμές ακριβώς ίσες με τη Γερμανία (μπαίνει στα else - ισοπαλία)
        "2.3, 0.2, 63.5, 19.0, 45.0"
    })
    void testGetComparisonReportCoverage(double inf, double gdp, double debt, double vat, double tax) {
        // 1. Προετοιμασία δεδομένων
        MacroData data = new MacroData();
        data.setInflation(inf);
        data.setGdp(gdp);
        data.setDebtRatio(debt);
        data.setVatRatePercent(vat);
        data.setIncomeTaxRatePercent(tax);

        // 2. Δημιουργία του αντικειμένου
        BudgetCountriesComparator comparator = new BudgetCountriesComparator(data);
        
        // 3. Εκτέλεση της σωστής μεθόδου
        assertDoesNotThrow(() -> {
            String report = comparator.getComparisonReport();
            assertNotNull(report); // Βεβαιωνόμαστε ότι παράγεται κείμενο
        });
    }
}