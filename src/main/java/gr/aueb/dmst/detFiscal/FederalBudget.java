package gr.aueb.dmst.detFiscal;

import java.util.List;

import gr.aueb.dmst.detFiscal.Expenditure;
import gr.aueb.dmst.detFiscal.Ministry;
import gr.aueb.dmst.detFiscal.Revenue;
import main.java.gr.aueb.dmst.detFiscal.BudgetDetails;
import main.java.gr.aueb.dmst.detFiscal.BudgetSummary;
import main.java.gr.aueb.dmst.detFiscal.DataLoader;

public class FederalBudget {

    // Fields
    private static FederalBudget instance; // The single instance of the class (Singleton pattern)
    private String countryName; // Name of the country
    private int year; // Budget year
    private BudgetSummary summary; // Object holding revenues and expenditures
    private BudgetDetails details; // Object holding macroeconomic data
    private IDataLoader dataLoader; // Object responsible for loading data

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
     * Returns the single instance of the FederalBudget class, creating it if
     * necessary.
     * 
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
     * 
     * @return The budget balance as a double.
     */
    public double calculateTotalBudget() {
        double revenues = summary.calculateTotalRevenues();
        double expenses = summary.calculateTotalExpenditures();
        return revenues - expenses;
    }

    /**
     * Calls the DataLoader to load all data (Revenues, Expenditures, MacroData)
     * from a JSON file.
     * The loaded data is then stored in the BudgetSummary and BudgetDetails
     * objects.
     * 
     * @param jsonPath The file path (String) to the JSON data source.
     */
    public void initializeData(String pathMain, String path2024) {
        try {
            // 1. Load Revenues and populate the BudgetSummary
            // REPLACE THE ENTIRE BODY OF initializeData (inside the try block) with this:

            // 1. Load MAIN Budget Data (2025)
            List<Revenue> revenuesMain = dataLoader.loadRevenues(pathMain);
            for (Revenue r : revenuesMain) {
                summary.addRevenue(r); // Adds to the main 'revenues' list
            }

            List<Expenditure> expendituresMain = dataLoader.loadExpenditures(pathMain);
            for (Expenditure e : expendituresMain) {
                summary.addExpenditure(e); // Adds to the main 'expenditures' list
            }

            // Load Ministries (assumed to be based on the main path/budget)
            List<Ministry> ministries = dataLoader.loadMinistries(pathMain);
            for (Ministry m : ministries) {
                summary.addMinistry(m);
            }
            List<Ministry> ministries = dataLoader.loadMinistries(jsonPath);
            for (Ministry m : ministries) {
                summary.addMinistry(m);
            }

            // ----------------------------------------------------------------------

            // 2. Load COMPARISON Budget Data (2024)
            List<Revenue> revenues24 = dataLoader.loadRevenues(path2024);
            for (Revenue r : revenues24) {
                summary.getRevenues2024().add(r);
            }

            List<Expenditure> expenditures24 = dataLoader.loadExpenditures(path2024);
            for (Expenditure e : expenditures24) {
                summary.getExpenditures2024().add(e);
            }

            // Simple validation check (using main budget data)
            assert summary.calculateTotalRevenues() >= 0 : "Data loading failed: Total revenues are zero or negative.";
            System.out.println("Data loading successful (OK).");
            // ADD THIS BLOCK immediately after the closing '}' of the try block:
        } catch (Exception e) {
            System.err.println("ERROR! Could not load file: " + e.getMessage());
            e.printStackTrace();
        }
    } // End of initializeData method

    /**
     * Displays a comprehensive overview of the budget, including totals and
     * balance.
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
     * Compares the current budget balance with the balance of another FederalBudget
     * object.
     * 
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
     * 
     * @return BudgetSummary
     */
    public BudgetSummary getSummary() {
        return this.summary;
    }

    /**
     * Returns the BudgetDetails object containing macroeconomic data.
     * 
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