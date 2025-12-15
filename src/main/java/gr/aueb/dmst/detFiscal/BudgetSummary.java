package gr.aueb.dmst.detFiscal;
import java.util.ArrayList;
import java.util.List;

public class BudgetSummary {

    // ΔΙΟΡΘΩΣΗ: Κρατάμε μόνο έναν ορισμό για κάθε λίστα
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
        this.ministries = new ArrayList<>();
        this.surplus = 0.0;
        this.deficit = 0.0;
        this.revenues2024 = new ArrayList<>();
        this.expenditures2024 = new ArrayList<>();
    }

    /**
     * Adds a Ministry object to the list of ministries.
     */
    public void addMinistry(Ministry m) {
        this.ministries.add(m);
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
     */
    public void addExpenditure(Expenditure e) {
        this.expenditures.add(e);
    }

    // --- Getters για Frontend/Λειτουργίες ---

    public List<Ministry> getMinistries() {
        return this.ministries;
    }

    public List<Revenue> getRevenues() {
        return this.revenues;
    }

    public List<Expenditure> getExpenditures() {
        return this.expenditures;
    }

    /**
     * Returns the list of Revenue accounts for the 2024 budget. (ΑΠΟ ΤΗΝ DEVELOP)
     */
    public List<Revenue> getRevenues2024() {
        return this.revenues2024;
    }

    /**
     * Returns the list of Expenditure accounts for the 2024 budget. (ΑΠΟ ΤΗΝ DEVELOP)
     */
    public List<Expenditure> getExpenditures2024() {
        return this.expenditures2024;
    }

    // --- Υπολογισμοί & Αναζήτηση ---

    /**
     * Calculates and returns the total amount of all revenues in the list.
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
     */
    public double calculateBalance() {
        return calculateTotalRevenues() - calculateTotalExpenditures();
    }

    /**
     * Searches for an Account (Revenue or Expenditure) by name.
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
     * Searches for a Ministry object by name. (ΑΠΟ ΤΗΝ FIX/ARCHITECTURE)
     */
    public Ministry searchMinistry(String name) {
        for (Ministry m : ministries) {
            if (m.getName().equalsIgnoreCase(name) || m.getName().contains(name)) {
                return m;
            }
        }
        return null;
    }
}
