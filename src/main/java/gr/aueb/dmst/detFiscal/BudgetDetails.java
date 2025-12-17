package gr.aueb.dmst.detFiscal;
<<<<<<< HEAD
=======

import main.java.gr.aueb.dmst.detFiscal.BudgetSummary;

>>>>>>> 722febb791850e392e86098976aa05c07380be0d
public class BudgetDetails {

    private final MacroData data;
    private BudgetSummary summaryBalance;
    private double balance;

    public BudgetDetails(MacroData data, BudgetSummary summaryBalance) {
        this.data =data;
        this.summaryBalance = summaryBalance;
    }

    public void checkInflation() {
        double inflation = data.getInflation();
        if (inflation < 0 || inflation > 100) {
            throw new IllegalArgumentException("Άκυρη τιμή πληθωρισμού");
        }
    }

    public double getInflation() {
        return data.getInflation();
    }

    public void checkGdp() {
        double gdp = data.getGdp();
        if (gdp < -100 || gdp > 100) {
            throw new IllegalArgumentException("Άκυρη τιμή Α.Ε.Π.");
        }
    }

    public double getGdp() {
        return data.getGdp();
    }


    public void checkDebtRatio() {
        double debtRatio = data.getDebtRatio();
        if (debtRatio < 0 || debtRatio > 100) {
            throw new IllegalArgumentException("Άκυρη τιμή λόγου χρέους");
        }
    }

    public double getDebtRatio() {
        return data.getDebtRatio();
    }


    public void checkVatRatePercent() {
        double vatRatePerc = data.getVatRatePercent();
        if (vatRatePerc < 0 || vatRatePerc > 100) {
                throw new IllegalArgumentException("Άκυρη τιμή ποσοστό ΦΠΑ");
        }
    }

    public double getVatRatePercent() {
        return data.getVatRatePercent();
    }

    public void checkIncomeTaxRatePercent() {
        double incomeTaxPerc = data.getIncomeTaxRatePercent();
        if (incomeTaxPerc < 0 || incomeTaxPerc > 100) {
                throw new IllegalArgumentException("Άκυρη τιμή ποσοστού φόρου εισοδήματος");
        }
    }

    public double getIncomeTaxRatePercent() {
        return data.getIncomeTaxRatePercent();
    }

    public void checkBaseRevenueForVat() {
        double bsRevForVat = data.getBaseRevenueForVat();
        if (bsRevForVat < 0) {
                throw new IllegalArgumentException("Άκυρη τιμή βάσης εσόδων ΦΠΑ");
        }
    }

    public double getBaseRevenueForVat() {
        return data.getBaseRevenueForVat();
    }

    public void checkBaseRevenueForIncomeTax() {
        double bsRevForIncomeTax = data.getBaseRevenueForIncomeTax();
        if (bsRevForIncomeTax < 0) {
            throw new IllegalArgumentException("Άκυρη τιμή βάσης εσόδων Φόρου Εισοδήματος");
        }
    }

    public double getBaseRevenueForIncomeTax() {
        return data.getBaseRevenueForIncomeTax();
    }

    public void valueForBalance () {
        balance = summaryBalance.calculateBalance();
    }

    public String characterizeTotal() {
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
