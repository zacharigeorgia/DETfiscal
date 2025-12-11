package gr.aueb.dmst.detFiscal;

public class BudgetDetails {
    private double inflation;
    private double gdp;
    private double deptRatio;

    public void setInflation(double inflation) {
        if (inflation < 0 || inflation > 1) {
            throw new IllegalArgumentException("Άκυρη τιμή πληθωρισμού");
        }
        this.inflation = inflation;
    }


    public double getInflation() {
        return inflation;
    }

    public void setGdp(double gdp) {
        if (gdp < 0) {
            throw new IllegalArgumentException("Άκυρη τιμή G.D.P");
        }
        this.gdp = gdp;
    }

    public double getGdp() {
        return gdp;
    }

    public void setDebtRatio(double deptRatio) {
        if (deptRatio < 0 || deptRatio > 5) {
            throw new IllegalArgumentException("Άκυρη τιμή λόγου χρέους");
        }
        this.deptRatio = deptRatio;
    }

    public double getDeptRatio() {
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
