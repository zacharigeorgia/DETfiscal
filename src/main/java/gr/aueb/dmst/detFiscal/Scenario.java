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
   // Αν είναι έσοδο: αύξηση εσόδων = θετικό impact (μειώνει χρέος)
   // Αν είναι έξοδο: αύξηση εξόδων = αρνητικό impact (αυξάνει χρέος)
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
private String getSocialImpactMessage(String name, double diff) {
    String[] messages = impactMap.get(name);
    if (messages != null) {
        return diff > 0 ? messages[1] : messages[0]; // [0] = μείωση, [1] = αύξηση
    }

    String lowerName = name.toLowerCase();
    if (lowerName.contains("υπουργείο")) {
        return diff > 0
            ? "Ενίσχυση των αρμοδιοτήτων και των υπηρεσιών του Υπουργείου."
            : "Περικοπή πόρων λειτουργίας του Υπουργείου.";
    }
    return diff > 0 ? "Αύξηση του κονδυλίου." : "Μείωση του κονδυλίου.";
}
private void initializeImpacts() {
    // [μήνυμα μείωσης, μήνυμα αύξησης]

    //  ΕΣΟΔΑ
    impactMap.put("Φόροι", new String[]{
        "Ανακούφιση φορολογουμένων.",
        "Μείωση διαθέσιμου εισοδήματος πολιτών."
    });
    impactMap.put("Κοινωνικές εισφορές", new String[]{
        "Κίνδυνος βιωσιμότητας ασφαλιστικών ταμείων.",
        "Ενίσχυση του συστήματος κοινωνικής ασφάλισης, θετικό για τους πολίτες."
    });
    impactMap.put("Μεταβιβάσεις", new String[]{
        "Μείωση μεταβιβάσεων από άλλους φορείς.",
        "Αύξηση μεταβιβάσεων και ενίσχυση των φορέων."
    });
    impactMap.put("Πωλήσεις αγαθών και υπηρεσιών", new String[]{
        "Μείωση εσόδων από πωλήσεις.",
        "Αύξηση εσόδων από πωλήσεις αγαθών και υπηρεσιών."
    });
    impactMap.put("Δάνεια (Έσοδα)", new String[]{
        "Μείωση εισπράξεων δανείων.",
        "Αύξηση εισπράξεων δανείων."
    });
    impactMap.put("Δάνεια (Υποχρεώσεις)", new String[]{
        "Μείωση λήψης δανείων, αρα μείωση υποχρέωσης.",
        "Αύξηση λήψης δανείων, που σημαίνει αύξηση υποχρέωσης."
    });
    impactMap.put("Πάγια Περιουσιακά Στοιχεία", new String[]{
        "Μείωση εσόδων από πωλήσεις παγίων.",
        "Αύξηση εσόδων από πωλήσεις παγίων περιουσιακών στοιχείων."
    });
    // ΕΞΟΔΑ
     impactMap.put("Παροχές σε εργαζομένους", new String[]{
    "Μείωση μισθών δημοσίων υπαλλήλων.",
    "Αύξηση μισθών δημοσίων υπαλλήλων."
    });
    impactMap.put("Τόκοι", new String[]{
    "Μείωση κόστους δανεισμού.",
    "Αύξηση κόστους εξυπηρέτησης χρέους."
    });
    impactMap.put("Κοινωνικές παροχές", new String[]{
    "Μείωση κοινωνικών παροχών σε πολίτες.",
    "Ενίσχυση κοινωνικών παροχών."
    });
    impactMap.put("Αγορές αγαθών και υπηρεσιών", new String[]{
    "Μείωση δαπανών για προμήθειες και υπηρεσίες.",
    "Αύξηση δαπανών για προμήθειες και υπηρεσίες."
    });
    impactMap.put("Επιδοτήσεις", new String[]{
    "Μείωση επιδοτήσεων σε φορείς.",
    "Αύξηση επιδοτήσεων σε φορείς."
    });
    impactMap.put("Δάνεια (Εξοδα)", new String[]{
    "Μείωση χορήγησης δανείων.",
    "Αύξηση χορήγησης δανείων."
    });
    impactMap.put("Δάνεια (Αποπληρωμή)", new String[]{
    "Μείωση αποπληρωμής δανείων.",
    "Αύξηση αποπληρωμής δανείων."
    });
    impactMap.put("Πάγια περιουσιακά στοιχεία", new String[]{
    "Μείωση επενδύσεων σε πάγια περιουσιακά στοιχεία.",
    "Αύξηση επενδύσεων σε πάγια περιουσιακά στοιχεία."
    });

    //επιλεγμένα υπουργεία
    impactMap.put("Υπουργείο Υγείας", new String[]{
    "Ελλείψεις σε φάρμακα και νοσηλευτικό προσωπικό, υποβάθμιση περίθαλψης.",
    "Προσλήψεις γιατρών, αναβάθμιση νοσοκομείων και βελτίωση υπηρεσιών υγείας."
    });


    impactMap.put("Υπουργείο Παιδείας", new String[]{
    "Κενά σε εκπαιδευτικούς, συγχωνεύσεις τμημάτων και παλαιωμένος εξοπλισμός.",
    "Εκσυγχρονισμός σχολείων, διορισμοί εκπαιδευτικών και ενίσχυση έρευνας."
     });

    impactMap.put("Υπουργείο Αγροτικής Ανάπτυξης", new String[]{
    "Μείωση επιδοτήσεων και κίνδυνος βιωσιμότητας για τους αγρότες.",
    "Στήριξη πρωτογενούς τομέα, νέων αγροτών και εκσυγχρονισμός καλλιεργειών."
    });

    impactMap.put("Υπουργείο Περιβάλλοντος", new String[]{
    "Ελλιπής προστασία δασών και καθυστερήσεις στην πράσινη μετάβαση.",
    "Επενδύσεις σε ΑΠΕ, αντιμετώπιση κλιματικής αλλαγής και προστασία βιοποικιλότητας."
    });

    impactMap.put("Υπουργείο Προστασίας του Πολίτη", new String[]{
    "Κενά ασφαλείας, ελλείψεις σε περιπολικά και εξοπλισμό σωμάτων ασφαλείας.",
    "Ενίσχυση αισθήματος ασφάλειας, αναβάθμιση εξοπλισμού αστυνομίας και πυροσβεστικής."
    });

    impactMap.put("Υπουργείο Εθνικής Άμυνας", new String[]{
    "Αποδυνάμωση αποτρεπτικής ισχύος και πάγωμα εξοπλιστικών προγραμμάτων.",
    "Θωράκιση της χώρας και εκσυγχρονισμός των ενόπλων δυνάμεων."
    });
    }
}
