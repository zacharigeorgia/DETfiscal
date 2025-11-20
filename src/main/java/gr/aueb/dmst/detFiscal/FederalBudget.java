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
}
