package gr.aueb.dmst.detfiscal;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.util.Map;
import gr.aueb.dmst.detFiscal.BudgetSummary;
import gr.aueb.dmst.detFiscal.FederalBudget;
import gr.aueb.dmst.detFiscal.BudgetDetails;
import gr.aueb.dmst.detFiscal.BudgetCountriesComparator;
import gr.aueb.dmst.detFiscal.MacroData;


public class Charts {
    // Μέθοδος που βοηθάει στην εμφάνιση παραθύρου
    private static void displayChart(String title, DefaultCategoryDataset dataset, String xAxisLabel, String yAxixLabel) {
        JFreeChart barChart = ChartFactory.createBarChart(
            title,  // τίτλος του γραφήματος
            xAxisLabel, // Τίτλος για άξονα Χ
            yAxixLabel, // Τίτλος για άξονα Ψ
            dataset,    // τα δεδομενα
            PlotOrientation.VERTICAL,
            true,   // Έχει να κάνει με την εμφάνιση legend (υπόμνημα) με εξηγηση κάθε χρώματος
            true,   // Tooltip(το κουτάκι με την τιμή οταν το βελάκι είναι πάνω στην μπάρα)
            false   // URL δεν ανοίγει κάποιο λινκ
        );

            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setPreferredSize(new Dimension(800, 500));
            
            JFrame frame = new JFrame(title); // Δημιουργία παραθύρου με όνομα title
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //κλείνει το παράθυρο χωρίς να τερματίζει η εφαρμογή μόνο καθαρίζει την μνήμη
            frame.setContentPane(chartPanel); //γεμίζει το παράθυρο και περιέχει το Bar Chart
            frame.pack(); // χωράει ακριβώς στο παράθυρο το γράφημα
            frame.setLocationRelativeTo(null); //το τοποθετεί στο κέντρο της οθόνης
            frame.setVisible(true); // εμφανίζει το παράθυρο

        }
    /*
 * ΟΔΗΓΙΕΣ ΕΡΓΑΣΙΑΣ ΓΙΑ ΤΗΝ ΥΛΟΠΟΙΗΣΗ ΤΟΥ MULTI-YEAR BAR CHART
 * --------------------------------------------------------------------------------
 * Στόχος: Υλοποίηση της μεθόδου plotMultiYearComparison() που συγκρίνει 
 * τα ισοζύγια των ετών 2025 και 2024 (Multi-Year Comparison).
 * * Για να λειτουργήσει σωστά η σύγκριση, απαιτείται η υλοποίηση δύο νέων μεθόδων
 * στην κλάση BudgetSummary.java
 * * 1. Στην κλάση BudgetSummary.java, έχουμε:
 * - public double calculateTotalRevenues2024()
 * - public double calculateTotalExpenditures2024()
 * * Αυτές οι μέθοδοι θα χρησιμοποιούν τις λίστες 'revenues2024' και 'expenditures2024'
 * (οι οποίες προστέθηκαν στο BudgetSummary) και θα επιστρέφουν το άθροισμα τους.
 */
        /*
         * 1. Λογική Υπολογισμού Δεδομένων για συγκριση των 2 χρόνων:
         * - Καλείται το FederalBudget.getInstance().getSummary(). tik
         * - Υπολογίζεται το balance 2025 (summary.calculateBalance()).
         * - Υπολογίζεται το balance 2024 (Καλείται: summary.calculateTotalRevenues2024() - summary.calculateTotalExpenditures2024()).
         * * 2. Δημιουργία Dataset:
         * - Δημιουργείται DefaultCategoryDataset (JFreeChart).
         * - Προστίθενται τα δύο ζεύγη (Balance 2025 και Balance 2024) ως Bars.
         * * 3. JFreeChart & Εμφάνιση:
         * - Δημιουργείται JFreeChart (Bar Chart) με βάση το Dataset.
         * - Εμφανίζεται το γράφημα σε ένα νέο JFrame.
         */
    public static void plotMultiYearComparison() {
        FederalBudget budget = FederalBudget.getInstance();
        BudgetSummary summary = budget.getSummary();

        //υπολογισμος ισοζηυγίου 2025, χρήση revenues/expendures
        double balance2025 = summary.calculateBalance();

        //υπολογισμός για το 2024 που το συγκρίνουμε
        double balance2024 = summary.calculateBalance2024();
     
        //Δημιουργία Dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(balance2025, "Ισοζύγιο", "2025");
        dataset.addValue(balance2024, "Ισοζύγιο", "2024");

        // Εμφάνιση και JFreeChart
        String title = String.format("Σύγκριση Ισοζυγίου: %s (2025 vs 2024)", budget.getCountryName());
        displayChart(title, dataset, "Έτος Σύκρισης", "Ποσό (€)");
       
    }

    /**
     * Υλοποιεί το Chart για τη σύγκριση των Μακροοικονομικών Δεδομένων 
     * (π.χ., GDP, Inflation, Debt) μεταξύ διαφορετικών χωρών.
     * Τα δεδομένα λαμβάνονται από την κλάση BudgetCountriesComparator.
     */
    // Προσθήκη παραμέτρου, με αυτή ξέρουμε ποια χώρα συγκρίνουμε
    public static void plotMultiCountryComparison(String selectedCountry) {
        FederalBudget budget = FederalBudget.getInstance();
        BudgetDetails details = budget.getDetails();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //Παίρνουμε δεδομένα Ελλάδας από MacroData
        String greece = "Greece (2025)";
        dataset.addValue(details.getGdp(), "GDP Growth (%)", greece);
        dataset.addValue(details.getInflation(), "Inflation (%)", greece);
        dataset.addValue(details.getDeptRatio(), "Dept Ratio (% of GDP)", greece);

        // Παίρνουμε δεδομένα για την επιλεγμένη χώρα από την BudgetCountriesComparator
        // Πρώτα Έλεγχος αν η χώρα υπάρχει στους χάρτες τιμών
        if (BudgetCountriesComparator.INFLATION_VALUES.containsKey(selectedCountry)) {
            dataset.addValue(BudgetCountriesComparator.GDP_VALUES.get(selectedCountry), "GDP Growth (%)", selectedCountry);
            dataset.addValue(BudgetCountriesComparator.INFLATION_VALUES.get(selectedCountry), "Inflation(%)", selectedCountry);
            dataset.addValue(BudgetCountriesComparator.DEBTRATIO_VALUES.get(selectedCountry), "Dept Ratio (% of GDP)", selectedCountry);
        }

        // Εμφάνιση
        String title = "Macroeconomic Comparison: Greece vs " + selectedCountry;
        displayChart(title, dataset, "Δείκτες", "Ποσοστό (%)");
    }
}

