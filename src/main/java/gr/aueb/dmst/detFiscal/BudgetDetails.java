package gr.aueb.dmst.detFiscal;

public class BudgetDetails {
    // ΣΗΜΑΝΤΙΚΟ: Αφαιρούμε το 'final' για να επιτρέψουμε τη φόρτωση δεδομένων από το DataLoader
    private double inflation;
    private double gdp;
    private double deptRatio;

    public double getInflation() {
        return inflation;
    }

    // Προστίθεται ο Setter για τον Πληθωρισμό
    public void setInflation(double inflation) {
        this.inflation = inflation;
    }

    // Προστίθεται ο Setter για το ΑΕΠ (με έλεγχο ορίων)
    public void setGdp(double gdp) {
        if (gdp < -1 || gdp > 1) {
            // Ο έλεγχος ορίων είναι απαραίτητος, όπως τον έθεσε η συνάδελφός σας.
            throw new IllegalArgumentException("Άκυρη τιμή Α.Ε.Π.");
        }
        this.gdp = gdp;
    }

    public double getGdp() {
        return gdp;
    }
    
    // Η getGdp() αντικαθιστά την getGDP() για εναρμόνιση ονομάτων (convention)

    // Προστίθεται ο Setter για το Χρέος (με έλεγχο ορίων)
    public void setDebtRatio(double deptRatio) {
        if (deptRatio < 0 || deptRatio > 5) {
            throw new IllegalArgumentException("Άκυρη τιμή λόγου χρέους");
        }
        this.deptRatio = deptRatio;
    }

    public double getDeptRatio() {
        // Κρατάμε το όνομα deptRatio (αντί debtRatio) για να μην προκαλέσουμε άλλες συγκρούσεις,
        // παρόλο που το debtRatio είναι πιο σωστό ορθογραφικά.
        return deptRatio; 
    }

    public String characterizeTotal(double balance) {
        if (balance > 0) {
            return "Πλεόνασμα";
        } else if (balance < 0) {
            return "Έλλειμμα";
        } else {
            return "Ισοσκελισμένος Προϋπολογισμός";
        }
    }
    
    // Προστίθεται η μέθοδος plotGraph() (απαραίτητη αν καλείται από την FederalBudget)
    public void plotGraph() {
        System.out.println("Εκτύπωση γραφήματος...");
    }
}
