package gr.aueb.dmst.detFiscal;
import java.util.ArrayList;
import java.util.List;

// NOTE: This class assumes that Revenue and Expenditure classes exist and extend an abstract
// Account class (as per your design), and that they have a public method getAmount().
public class BudgetSummary {

    // Fields as per your design
    private List<Revenue> revenues;
    private List<Expenditure> expenditures;
    private double surplus;
    private double deficit;

    public BudgetSummary() {
        this.revenues = new ArrayList<>();
        this.expenditures = new ArrayList<>();
        this.surplus = 0.0;
        this.deficit = 0.0;
    }

    /**
     * Adds a Revenue object to the list of revenues.
     * @param r The Revenue object to add.
     */
    public void addRevenue(Revenue r) {
        this.revenues.add(r);
    }

    /**
     * Adds an Expenditure object to the list of expenditures.
     * @param e The Expenditure object to add.
     */
    public void addExpenditure(Expenditure e) {
        this.expenditures.add(e);
    }

    /**
     * Calculates and returns the total amount of all revenues in the list.
     * @return The sum of all revenue amounts.
     */
    public double calculateTotalRevenues() {
        double total = 0;
        for (Revenue r : revenues) {
            total += r.getAmount();
        }
        return total;
    }

    /**
     * Calculates and returns the total amount of all expenditures in the list.
     * @return The sum of all expenditure amounts.
     */
    public double calculateTotalExpenditures() {
        double total = 0;
        for (Expenditure e : expenditures) {
            total += e.getAmount();
        }
        return total;
    }

    /**
     * Calculates the difference between total revenues and total expenditures (the balance/ισοζύγιο).
     * @return The budget balance (Revenues - Expenditures).
     */
    public double calculateBalance() {
        return calculateTotalRevenues() - calculateTotalExpenditures();
    }

    /**
     * Searches for an Account (Revenue or Expenditure) by name.
     * @param name The name of the account to search for.
     * @return The Account object (Revenue or Expenditure) or null if not found.
     */
    public Account searchAccount(String name) {
        // Search in Revenues
        for (Revenue r : revenues) {
            if (r.getName().equalsIgnoreCase(name)) {
                return r;
            }
        }
        // Search in Expenditures
        for (Expenditure e : expenditures) {
            if (e.getName().equalsIgnoreCase(name)) {
                return e;
            }
        }
        return null; // Not found
    }
}