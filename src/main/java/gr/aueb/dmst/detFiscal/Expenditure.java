package gr.aueb.dmst.detFiscal;

public class Expenditure extends Account {
    private boolean canDecrease;
    private double maxIncreasePercent;


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

    @Override
    public String getName() {
        return name;
        }
    @Override
    public double getAmount() {
         return amount;
          }
    public boolean isCanDecrease() {
         return canDecrease;
         }
    public double getMaxIncreasePercent() {
        return maxIncreasePercent;
    }


    @Override
    public void increase(double amount) {
        // Εδώ θα μπει αργότερα ο έλεγχος του maxIncreasePercent

            this.amount += amount;

    }

    @Override
    public void decrease(double amount) {
        // Εδώ θα μπει αργότερα ο έλεγχος του canDecrease

            this.amount -= amount;

    }

    @Override
    public String toString() {
        String lockStatus = canDecrease ? "" : " [ΚΛΕΙΔΩΜΕΝΟ]";
        return String.format("[Έξοδο] %-40s : %,15.2f  %s", name, amount, lockStatus);
    }
}

