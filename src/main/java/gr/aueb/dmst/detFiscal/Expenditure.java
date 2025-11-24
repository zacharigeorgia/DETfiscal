package gr.aueb.dmst.detFiscal;

public class Expenditure extends Account {
    private boolean canDecrease;
    private double maxIncreasePercent;

    // Setters (για τον DataLoader)
    public void setCanDecrease(boolean canDecrease) {
        this.canDecrease = canDecrease;
    }
    public void setMaxIncreasePercent(double maxIncreasePercent) {
        this.maxIncreasePercent = maxIncreasePercent;
    }

    // Getters
    public boolean isCanDecrease() { 
        return canDecrease;
    }
    public double getMaxIncreasePercent() {
        return maxIncreasePercent;
    }

    @Override
    public void increase(double amount) {
        // Εδώ θα μπει αργότερα ο έλεγχος του maxIncreasePercent
        if () {
            this.amount += amount;
        }
    }

    @Override
    public void decrease(double amount) {
        // Εδώ θα μπει αργότερα ο έλεγχος του canDecrease
        if () {
            this.amount -= amount;
        }
    }

    @Override
    public String toString() {
        String lockStatus = canDecrease ? "" : " [ΚΛΕΙΔΩΜΕΝΟ]";
        return String.format("[Έξοδο] %-40s : %,15.2f € %s", name, amount, lockStatus);
    }
}
