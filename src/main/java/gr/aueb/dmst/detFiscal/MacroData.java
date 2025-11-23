package gr.aueb.dmst.detFiscal;

public class MacroData {

    // Τα γενικά σύνολα (από το "summary" του JSON)
    private double totalRevenues;
    private double totalExpenditures;
    private double budgetResult;

    // Οι παράμετροι σεναρίων (από το "economicParameters" του JSON)
    private double vatRatePercent;
    private double incomeTaxRatePercent;
    private double baseRevenueForVat;
    private double baseRevenueForIncomeTax;


    public MacroData() {

    }

    // Για τα Total Revenues
    public double getTotalRevenues() { return totalRevenues; }
    public void setTotalRevenues(double totalRevenues) { this.totalRevenues = totalRevenues; }

    // Για τα Total Expenditures
    public double getTotalExpenditures() { return totalExpenditures; }
    public void setTotalExpenditures(double totalExpenditures) { this.totalExpenditures = totalExpenditures; }

    // Για το Budget Result
    public double getBudgetResult() { return budgetResult; }
    public void setBudgetResult(double budgetResult) { this.budgetResult = budgetResult; }

    // Για το VAT Rate (ΦΠΑ)
    public double getVatRatePercent() { return vatRatePercent; }
    public void setVatRatePercent(double vatRatePercent) { this.vatRatePercent = vatRatePercent; }

    // Για το Income Tax Rate (Φόρος Εισοδήματος)
    public double getIncomeTaxRatePercent() { return incomeTaxRatePercent; }
    public void setIncomeTaxRatePercent(double incomeTaxRatePercent) { this.incomeTaxRatePercent = incomeTaxRatePercent; }

    // Για τη Βάση ΦΠΑ
    public double getBaseRevenueForVat() { return baseRevenueForVat; }
    public void setBaseRevenueForVat(double baseRevenueForVat) { this.baseRevenueForVat = baseRevenueForVat; }

    // Για τη Βάση Φόρου Εισοδήματος
    public double getBaseRevenueForIncomeTax() { return baseRevenueForIncomeTax; }
    public void setBaseRevenueForIncomeTax(double baseRevenueForIncomeTax) { this.baseRevenueForIncomeTax = baseRevenueForIncomeTax; }
}