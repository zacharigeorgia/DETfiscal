package gr.aueb.dmst.detFiscal;

public class Expenditure {
    private String name;
    private double amount;
    private boolean canDecrease;
    private double maxIncreasePercent;

    // Setters
    public void setName(String name) {
        this.name = name;
        }
    public void setAmount(double amount) {
        this.amount = amount;
        }
    public void setCanDecrease(boolean canDecrease) {
         this.canDecrease = canDecrease;
         }
    public void setMaxIncreasePercent(double maxIncreasePercent) {
        this.maxIncreasePercent = maxIncreasePercent;
         }

    // Getters
    public String getName() {
        return name;
        }
    public double getAmount() {
         return amount;
          }
    public boolean isCanDecrease() {
         return canDecrease;
         }
    public double getMaxIncreasePercent() {
        return maxIncreasePercent;
        }
}