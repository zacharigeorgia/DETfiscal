package gr.aueb.dmst.detFiscal;
import java.util.HashMap;
import java.util.Map;

public class Scenario {
    // Χάρτης: Κατηγορία -> [μήνυμα μείωσης, μήνυμα αύξησης]
    private Map<String, String[]> impactMap;

    public Scenario() {
        this.impactMap = new HashMap<>();
        initializeImpacts();
    }
    public String analyzeScenario(String categoryName, double oldAmount, double newAmount, boolean isRevenue, BudgetDetails details) {
   StringBuilder report = new StringBuilder();
   double difference = newAmount - oldAmount;

   // Header
   report.append("ΑΝΑΛΥΣΗ ΕΠΙΠΤΩΣΕΩΝ\n\n");

   // Μεταβολή Ποσού
   report.append("Μεταβολή Ποσού:\n");
   if (difference >= 0) {
       report.append(String.format("   +%,.0f € (Αύξηση)\n", difference));
   } else {
       report.append(String.format("   %,.0f € (Μείωση)\n", difference));
   }
   report.append(String.format("   Από: %,.0f €\n", oldAmount));
   report.append(String.format("   Σε: %,.0f €\n\n", newAmount));

   // Αντίκτυπος
   String message = getSocialImpactMessage(categoryName, difference);
   report.append("Αντίκτυπος:\n");
   report.append("   ").append(message).append("\n\n");

   // Υπολογισμός επιπτώσεων στο δημόσιο χρέος
   double impactOnBalance = isRevenue ? difference : -difference;
   double currentDebtRatio = details.getDebtRatio();
   double gdp = details.getGdp();

   // Υπολογισμός τρέχοντος δημοσίου χρέους
   double currentDebtAmount = (currentDebtRatio / 100.0) * gdp;

   // Υπολογισμός νέου δημοσίου χρέους
   // Αν αυξάνουμε έσοδα ή μειώνουμε έξοδα -> μειώνεται το χρέος
   // Αν μειώνουμε έσοδα ή αυξάνουμε έξοδα -> αυξάνεται το χρέος
   double newDebtAmount = currentDebtAmount - impactOnBalance;
   double newDebtRatio = (newDebtAmount / gdp) * 100.0;
   double ratioDiff = newDebtRatio - currentDebtRatio;

   // Επιπτώσεις στο Δημόσιο Χρέος
   report.append("Επιπτώσεις στο Δημόσιο Χρέος:\n");
   report.append(String.format("Τρέχον λόγος χρέους: %.2f%% του ΑΕΠ\n", currentDebtRatio));
   report.append(String.format("Νέος λόγος χρέους: %.2f%% του ΑΕΠ\n", newDebtRatio));

   if (ratioDiff > 0) {
       report.append(String.format("Αύξηση: +%.3f%% (Αύξηση δημοσίου χρέους)\n", ratioDiff));
   } else if (ratioDiff < 0) {
       report.append(String.format("Μείωση: %.3f%% (Μείωση δημοσίου χρέους)\n", ratioDiff));
   } else {
       report.append("   →  Χωρίς μεταβολή\n");
   }

   // Επιπλέον πληροφορίες
   report.append(String.format("\n Τρέχον δημόσιο χρέος: %,.0f €\n", currentDebtAmount));
   report.append(String.format("Νέο δημόσιο χρέος: %,.0f €\n", newDebtAmount));
   report.append(String.format("Μεταβολή: %,.0f €\n", newDebtAmount - currentDebtAmount));

   return report.toString();
    }
}