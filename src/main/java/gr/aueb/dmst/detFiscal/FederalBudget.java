package gr.aueb.dmst.detFiscal;
import java.util.List;

public class FederalBudget {

    // Fields
    private static FederalBudget instance;  // The single instance of the class (Singleton pattern)
    private String countryName;             // Name of the country
    private int year;                       // Budget year
    private BudgetSummary summary;          // Object holding revenues and expenditures
    private BudgetDetails details;          // Object holding macroeconomic data
    private IDataLoader dataLoader;          // Object responsible for loading data

    // Private constructor for Singleton
    private FederalBudget() {
        // Initialize essential components
        dataLoader = new DataLoader();
        summary = new BudgetSummary();
        details = new BudgetDetails();
        this.countryName = "Greece";
        this.year = 2025;
    }

    /**
     * Returns the single instance of the FederalBudget class, creating it if necessary.
     * @return The FederalBudget singleton instance.
     */
    public static FederalBudget getInstance() {
        if (instance == null) {
            instance = new FederalBudget();
        }
        return instance;
    }

    /**
     * Calculates the total budget balance (Total Revenues - Total Expenditures).
     * @return The budget balance as a double.
     */
    public double calculateTotalBudget() {
        double revenues = summary.calculateTotalRevenues();
        double expenses = summary.calculateTotalExpenditures();
        return revenues - expenses;
    }

    /**
     * Calls the DataLoader to load all data (Revenues, Expenditures, MacroData) from a JSON file.
     * The loaded data is then stored in the BudgetSummary and BudgetDetails objects.
     * @param jsonPath The file path (String) to the JSON data source.
     */
    public void initializeData(String jsonPath) {
        try {
            // 1. Load Revenues and populate the BudgetSummary
            List<Revenue> revenues = dataLoader.loadRevenues(jsonPath);
            for (Revenue r : revenues) {
                summary.addRevenue(r);
            }

            // 2. Load Expenditures and populate the BudgetSummary
            List<Expenditure> expenditures = dataLoader.loadExpenditures(jsonPath);
            for (Expenditure e : expenditures) {
                summary.addExpenditure(e);
            }
            List<Ministry> ministries = dataLoader.loadMinistries(jsonPath);
            for (Ministry m : ministries) {
                summary.addMinistry(m);
            }


            // 3. Load MacroData (assuming MacroData class exists) and update BudgetDetails
            MacroData macroData = dataLoader.loadMacroData(jsonPath);
            details.setInflation(macroData.getInflation());
            details.setGdp(macroData.getGdp());
            details.setDebtRatio(macroData.getDebtRatio());

            // Simple validation check
            assert summary.calculateTotalRevenues() >= 0 : "Data loading failed: Total revenues are zero or negative.";
            System.out.println("Data loading successful (OK).");

        } catch (Exception e) {
            System.err.println("ERROR! Could not load file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Displays a comprehensive overview of the budget, including totals and balance.
     */
    public void showBudgetOverview() {
        // Calculate the balance (Revenues - Expenditures)
        double balance = this.calculateTotalBudget();

        // Determine if the result is a Surplus or Deficit
        String characterization = details.characterizeTotal(balance);

        System.out.println("\n--- Federal Budget Overview ---");
        System.out.printf("Country: %s, Year: %d\n", countryName, year);

        System.out.println("Total Revenues: " + summary.calculateTotalRevenues());
        System.out.println("Total Expenditures: " + summary.calculateTotalExpenditures());
        System.out.println("Balance: " + balance);
        System.out.println("Result: " + characterization);
        System.out.println("Inflation: " + details.getInflation() + "%");

        details.plotGraph(); // Assumes this method exists in BudgetDetails
    }

    /**
     * Compares the current budget balance with the balance of another FederalBudget object.
     * @param other The other budget to compare against.
     */
    public void compareWith(FederalBudget other) {
        double currentBalance = this.calculateTotalBudget();
        double otherBalance = other.calculateTotalBudget();

        System.out.println("\n--- Budget Comparison ---");
        System.out.printf("Current Budget Balance (%s %d): %f\n", this.countryName, this.year, currentBalance);
        System.out.printf("Compared Budget Balance (%s %d): %f\n", other.countryName, other.year, otherBalance);

        if (currentBalance > otherBalance) {
            System.out.println("The current budget is performing better.");
        } else if (currentBalance < otherBalance) {
            System.out.println("The other budget is performing better.");
        } else {
            System.out.println("The balances are identical.");
        }
    }

    // --- Getters and Setters (From Specification) ---

    /**
     * Returns the BudgetSummary object containing revenues and expenditures.
     * @return BudgetSummary
     */
    public BudgetSummary getSummary() {
        return this.summary;
    }

    /**
     * Returns the BudgetDetails object containing macroeconomic data.
     * @return BudgetDetails
     */
    public BudgetDetails getDetails() {
        return this.details;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}