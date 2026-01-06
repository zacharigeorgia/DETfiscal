package gr.aueb.dmst.detFiscal;

public class BudgetCountriesComparatorTest {

    public static void main(String[] args) {

        // Δημιουργούμε ένα MacroData αντικείμενο για την Ελλάδα
        MacroData greeceData = new MacroData();
        greeceData.setInflation(3.0);           // παράδειγμα πληθωρισμού
        greeceData.setGdp(1.5);                 // παράδειγμα ΑΕΠ
        greeceData.setDebtRatio(120.0);         // λόγος χρέους
        greeceData.setVatRatePercent(24.0);     // ΦΠΑ
        greeceData.setIncomeTaxRatePercent(40.0); // φόρος εισοδήματος
        greeceData.setBaseRevenueForVat(250_000_000_000.0); // βάση ΦΠΑ
        greeceData.setBaseRevenueForIncomeTax(400_000_000_000.0); // βάση εισοδήματος

        // Δημιουργούμε τον comparator
        BudgetCountriesComparator comparator = new BudgetCountriesComparator(greeceData);

        // Τρέχουμε τη σύγκριση
        comparator.compareCountriesMacro();
    }
}
