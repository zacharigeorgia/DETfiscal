package gr.aueb.dmst.detFiscal;

public class BudgetDetails {
    
    private final MacroData data;
    public BudgetDetails(MacroData data) {
        this.data =data;
    }

    public void checkInflation() {
        double inflation = data.getInflation();
        if (inflation < 0 || inflation > 1) {
            throw new IllegalArgumentException("Άκυρη τιμή πληθωρισμού");
        }
    }

    public void checkGdp() {
        double gdp = data.getGdp();
        if (gdp < -1 || gdp > 1) {
            throw new IllegalArgumentException("Άκυρη τιμή Α.Ε.Π.");
        }
    }


    public void checkDebtRatio() {
        double deptRatio = data.getDeptRatio();
        if (deptRatio < 0 || deptRatio > 5) {
            throw new IllegalArgumentException("Άκυρη τιμή λόγου χρέους");
        }
    }

    public void checkVatRatePercent() {
        double vatRatePerc = data.getVatRatePercent();
        if (vatRatePerc < 0 || vatRatePerc > 1) {
                throw new IllegalArgumentException("Άκυρη τιμή ποσοστό ΦΠΑ");
        }
     }

    public void checkIncomeTaxRatePercent() {
        double incomeTaxPerc = data.getIncomeTaxRatePercent(); 
        if (incomeTaxPerc < 0 || incomeTaxPerc > 1) {
                throw new IllegalArgumentException("Άκυρη τιμή ποσοστού φόρου εισοδήματος");
        }
    }

    public void checkBaseRevenueForVat() {
        double bsRevForVat = data.getBaseRevenueForVat();
        if (bsRevForVat < 0) {
                throw new IllegalArgumentException("Άκυρη τιμή βάσης εσόδων ΦΠΑ");
        }
    }

    public void checkBaseRevenueForIncomeTax() {
        double bsRevForIncomeTax = data.getBaseRevenueForIncomeTax();
        if (bsRevForIncomeTax < 0) {
            throw new IllegalArgumentException("Άκυρη τιμή βάσης εσόδων Φόρου Εισοδήματος");
        }
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
