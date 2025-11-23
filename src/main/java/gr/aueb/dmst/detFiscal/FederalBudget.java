public class FederalBudget {

    // Fields
    private static FederalBudget instance; // μοναδικό αντικείμενο της κλάσης (Singleton)
    private String countryName;            // όνομα χώρας
    private int year;                      // έτος προϋπολογισμού
    private BudgetSummary summary;         // αντικείμενο που κρατάει έσοδα/έξοδα
    private BudgetDetails details;         // αντικείμενο που κρατάει μακροοικονομικά δεδομένα
    private DataLoader dataLoader;         // αντικείμενο υπεύθυνο για τη φόρτωση δεδομένων

    // Private constructor for Singleton
    private FederalBudget() {
        // αρχικοποίηση αντικειμένων αν χρειάζεται
        dataLoader = new DataLoader();
        summary = new BudgetSummary();
        details = new BudgetDetails();
    }
    public static FederalBudget getInstance() {
        if (instance == null) {
            instance = new FederalBudget();
        }
        return instance;
    }
    public void loadData(String filePath) {
    dataLoader.loadData(filePath, summary, details);
}
public double calculateTotalBudget() {
    double revenues = summary.getTotalRevenues();
    double expenses = summary.getTotalExpenses();
    return revenues - expenses;
}
Java

// FederalBudget Class Continuation...

    /**
     * Καλεί τον DataLoader για να φορτώσει τα δεδομένα από JSON.
     * @param jsonPath διαδρομή αρχείου (String)
     */
    public void initializeData(String jsonPath) {
        // Δοκιμάζουμε να φορτώσουμε τα δεδομένα και να χειριστούμε τυχόν λάθη.
        try {
            dataLoader.loadData(jsonPath, summary, details); // Καλεί τη φόρτωση δεδομένων
            // Απλό assertion: Ελέγχουμε αν η φόρτωση έδωσε τουλάχιστον ένα έσοδο.
            assert summary.calculateTotalRevenues() >= 0 : "Δεν φορτώθηκαν έσοδα: Το σύνολο είναι αρνητικό."; 
            System.out.println("Φόρτωση δεδομένων ΟΚ.");
        } catch (Exception e) {
            // Αν αποτύχει η φόρτωση (π.χ. το αρχείο δεν υπάρχει)
            System.err.println("ΣΦΑΛΜΑ! Αδυναμία φόρτωσης αρχείου.");
            // Μπορείς να βγάλεις το e.getMessage() αν θες πιο απλό μήνυμα.
        }
    }

    /**
     * Εμφανίζει συνολική ανάλυση του προϋπολογισμού.
     */
    public void showBudgetOverview() {
        // Υπολογίζει το ισοζύγιο (έσοδα - έξοδα).
        double balance = this.calculateTotalBudget();
        
        // Βρίσκει αν το αποτέλεσμα είναι Πλεόνασμα ή Έλλειμμα.
        String characterization = details.characterizeTotal(balance); 

        System.out.println("\n--- Συνολική Ανάλυση Προϋπολογισμού ---");
        System.out.println("Έσοδα: " + summary.calculateTotalRevenues());       // Σύνολο Εσόδων
        System.out.println("Δαπάνες: " + summary.calculateTotalExpenditures());   // Σύνολο Δαπανών
        System.out.println("Ισοζύγιο: " + balance);                              // Η διαφορά
        System.out.println("Αποτέλεσμα: " + characterization);
        System.out.println("Πληθωρισμός: " + details.getInflation() + "%");    // Μακροοικονομικό στοιχείο

        details.plotGraph(); // Καλεί την εμφάνιση γραφημάτων
    }

    /**
     * Συγκρίνει τον τρέχοντα προϋπολογισμό με έναν άλλον.
     * @param other ο άλλος προϋπολογισμός (FederalBudget)
     */
    public void compareWith(FederalBudget other) {
        // Υπολογίζει το ισοζύγιο του τρέχοντος.
        double currentBalance = this.calculateTotalBudget();
        
        // Υπολογίζει το ισοζύγιο του άλλου αντικειμένου.
        double otherBalance = other.calculateTotalBudget();

        System.out.println("\n--- Σύγκριση Προϋπολογισμών ---");
        System.out.println("Τρέχον Ισοζύγιο: " + currentBalance);
        System.out.println("Συγκρινόμενο Ισοζύγιο: " + otherBalance);

        // Συγκρίνει τα δύο ισοζύγια.
        if (currentBalance > otherBalance) {
            System.out.println("Ο τρέχων προϋπολογισμός είναι καλύτερος.");
        } else if (currentBalance < otherBalance) {
            System.out.println("Ο άλλος προϋπολογισμός είναι καλύτερος.");
        } else {
            System.out.println("Τα ισοζύγια είναι ίδια.");
        }
    }
}
