public class BudgetDetails {
    private double inflation;
    private double gdp;
    private double deptRatio;
    public double getInflation() {
        return Inflation;
    }
    public double getGDP()M{
        return gdp;
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
