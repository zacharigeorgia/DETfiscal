public class Ministy {
    private String code;
    private String name;
    private double regularBudget;
    private double publicInvestments;
    private double total;

    public String getCode() {
         return code;
         }
    public void setCode(String code) {
         this.code = code;
          }

    public String getName() {
         return name;
          }
    public void setName(String name) {
        this.name = name;
        }

    public double getRegularBudget() {
         return regularBudget;
         }
    public void setRegularBudget(double regularBudget) {
         this.regularBudget = regularBudget;
         }

    public double getPublicInvestments() {
        return publicInvestments;
        }
    public void setPublicInvestments(double publicInvestments) {
         this.publicInvestments = publicInvestments;
         }

    public double getTotal() {
         return total;
         }
    public void setTotal(double total) {
         this.total = total;
         }
@Override
    public String toString() {
            return String.format("[%s] %-50s : %,15.2f €", name, total, regularBudget);
         }
}
