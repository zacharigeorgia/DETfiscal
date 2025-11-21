public class BudgetDetails {
    private double inflation;
    private double gdp;
    private double deptRatio;
    public double setInflation() {
        inflation = 2,6%;
    }
    public double getInflation() {
        return Inflation;
    }
    public double setGDP() {
        gdp = 2,2;
    }
    public double getGDP()M{
        return gdp;
    }
    public double setDeptRatio() {
        deptRatio = 147,6%;
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
