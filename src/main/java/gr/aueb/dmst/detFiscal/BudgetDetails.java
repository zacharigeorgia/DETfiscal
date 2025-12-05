package gr.aueb.dmst.detfiscal;
public class BudgetDetails {
    private final double inflation = 2.8;
    private final double gdp = 2.1;
    private  final double deptRatio = 147.6;
    public double getInflation() {
        return inflation;
    }
    public double getGDP(){
        return gdp;
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
