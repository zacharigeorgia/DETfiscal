package gr.aueb.dmst.detfiscal;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Charts {
<<<<<<< HEAD
    private DefaultCategoryDataset createComparisonDataset(
        FederalBudget budget1, FederalBudget budget2, String categoryName1, String categoryName2)
        /*Δέχεται τον 1ο προϋπολογισμό, μετά τον 2ο και μετά ονόματα κατηγορίας έτος ή χώρα */
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        //Data για πρώτο αντικείμενο
        dataset.addValue(budget1.getSummary().calculateTotalRevenues(),
                         "Σύνολο Εσόδων", categoryName1);
        dataset.addValue(budget1.getSummary().calculateTotalExpenditures(),
                         "Σύνολο Εξόδων", categoryName1);
        dataset.addValue(budget1.calculateTotalBudget(),
                         "Ισοζύγιο", categoryName1);

        //Data για δεύτερο αντικείμενο
        dataset.addValue(budget2.getSummary().calculateTotalRevenues(),
                         "Σύνολο Εσόδων", categoryName2);
        dataset.addValue(budget2.getSummary().calculateTotalExpenditures(),
                         "Σύνολο Εξόδων", categoryName2)
        dataset.addValue(budget2.getSummary().calculateTotalBudget(),
                         "Ισοζύγιο", categoryName2);
        
        return dataset;
=======
    /*
 * ΟΔΗΓΙΕΣ ΕΡΓΑΣΙΑΣ ΓΙΑ ΤΗΝ ΥΛΟΠΟΙΗΣΗ ΤΟΥ MULTI-YEAR BAR CHART
 * --------------------------------------------------------------------------------
 * Στόχος: Υλοποίηση της μεθόδου plotMultiYearComparison() που συγκρίνει 
 * τα ισοζύγια των ετών 2025 και 2024 (Multi-Year Comparison).
 * * ********** ΑΝΑΛΥΣΗ ΚΑΙ ΠΡΟΕΤΟΙΜΑΣΙΑ ΔΕΔΟΜΕΝΩΝ **********
 * * Για να λειτουργήσει σωστά η σύγκριση, απαιτείται η υλοποίηση δύο νέων μεθόδων
 * στην κλάση BudgetSummary.java, οι οποίες ΔΕΝ υπάρχουν ακόμα.
 * * 1. Στην κλάση BudgetSummary.java, ΠΡΕΠΕΙ να δημιουργηθούν οι μέθοδοι:
 * - public double calculateTotalRevenues2024()
 * - public double calculateTotalExpenditures2024()
 * * Αυτές οι μέθοδοι θα χρησιμοποιούν τις λίστες 'revenues2024' και 'expenditures2024'
 * (οι οποίες προστέθηκαν στο BudgetSummary) και θα επιστρέφουν το άθροισμα τους.
 * * --------------------------------------------------------------------------------
 * ΥΛΟΠΟΙΗΣΗ ΣΤΗΝ ΚΛΑΣΗ Charts.java
 * --------------------------------------------------------------------------------
 */

// *Προσοχή: Προσθέστε τα imports για Map και τις κλάσεις του project
// import java.util.Map;
// import gr.aueb.dmst.detFiscal.BudgetSummary;
// import gr.aueb.dmst.detFiscal.FederalBudget;

public class Charts {

    /**
     * Υλοποιεί το Bar Chart για τη σύγκριση των ισοζυγίων (Balance) 
     * μεταξύ του κύριου έτους (π.χ., 2025) και του συγκριτικού έτους (2024).
     * Καλούμε αυτή τη μέθοδο από το Menu (GUI).
     */
    public static void plotMultiYearComparison() {
        
        /*
         * 1. Λογική Υπολογισμού Δεδομένων:
         * - Καλείται το FederalBudget.getInstance().getSummary().
         * - Υπολογίζεται το balance 2025 (summary.calculateBalance()).
         * - Υπολογίζεται το balance 2024 (Καλείται: summary.calculateTotalRevenues2024() - summary.calculateTotalExpenditures2024()).
         * * 2. Δημιουργία Dataset:
         * - Δημιουργείται DefaultCategoryDataset (JFreeChart).
         * - Προστίθενται τα δύο ζεύγη (Balance 2025 και Balance 2024) ως Bars.
         * * 3. JFreeChart & Εμφάνιση:
         * - Δημιουργείται JFreeChart (Bar Chart) με βάση το Dataset.
         * - Εμφανίζεται το γράφημα σε ένα νέο JFrame.
         */
    }

    /**
     * Υλοποιεί το Chart για τη σύγκριση των Μακροοικονομικών Δεδομένων 
     * (π.χ., GDP, Inflation, Debt) μεταξύ διαφορετικών χωρών.
     * Τα δεδομένα λαμβάνονται από την κλάση BudgetComparator.
     */
    public static void plotMultiCountryComparison() {

        /*
         * 1. Λογική Υπολογισμού Δεδομένων:
         * - Καλείται η BudgetComparator (π.χ., comparator.getCountryMacroData()).
         * - Η μέθοδος της BudgetComparator πρέπει να επιστρέφει Map με τα δεδομένα των χωρών (π.χ., GDP, Debt).
         * * 2. Δημιουργία Dataset:
         * - Δημιουργείται DefaultCategoryDataset.
         * - Προστίθενται τα δεδομένα. Η χώρα είναι η "Κατηγορία" και ο δείκτης (GDP) είναι η "Σειρά".
         * * 3. JFreeChart & Εμφάνιση:
         * - Δημιουργείται JFreeChart (π.χ., Grouped Bar Chart) με βάση το Dataset.
         * - Εμφανίζεται το γράφημα σε ένα νέο JFrame.
         */
    }
}
>>>>>>> a15ee47484d3d2d11d950677a0e241f0a336dd78
}
