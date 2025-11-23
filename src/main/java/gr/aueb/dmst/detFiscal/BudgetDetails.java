package gr.aueb.dmst.detfiscal;
public class BudgetDetails {
    private double inflation;
    private double gdp;
    private double deptRatio;
    public void setInflation() {
        this.inflation = 0.026;
    }
    public double getInflation() {
        return inflation;
    }
    public void setGDP() {
        this.gdp = 0.022;
    }
    public double getGDP(){
        return gdp;
    }
    public void setDeptRatio() {
        this.deptRatio = 1.476;
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
}
