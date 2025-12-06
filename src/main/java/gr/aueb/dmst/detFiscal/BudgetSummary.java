package gr.aueb.dmst.detFiscal;
import java.util.ArrayList;
import java.util.List;

import gr.aueb.dmst.detFiscal.Expenditure;
import gr.aueb.dmst.detFiscal.Ministry;
import gr.aueb.dmst.detFiscal.Revenue;


public class BudgetSummary {


    private List<Ministry> ministries;
    private List<Revenue> revenues;
    private List<Expenditure> expenditures;
    private double surplus;
    private double deficit;
    // Fields for Comparative Budget (2024)
    private List<Revenue> revenues2024;
    private List<Expenditure> expenditures2024;

    public BudgetSummary() {
        this.revenues = new ArrayList<>();
        this.expenditures = new ArrayList<>();
        this.surplus = 0.0;
        this.deficit = 0.0;
        this.revenues2024 = new ArrayList<>();
        this.expenditures2024 = new ArrayList<>();
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
    /**
     * Adds a Ministry object to the list of ministries.
     */
    public void addMinistry(Ministry m) {
        this.ministries.add(m);
    }
    
    /**
     * Returns the list of Ministry objects.
     */
    public List<Ministry> getMinistries() {
        return this.ministries;
    }
    /**
     * Returns the list of Revenue accounts for the 2024 budget.
     */
    public List<Revenue> getRevenues2024() {
        return this.revenues2024;
    }

    /**
     * Returns the list of Expenditure accounts for the 2024 budget.
     */
    public List<Expenditure> getExpenditures2024() {
        return this.expenditures2024;
    }
}