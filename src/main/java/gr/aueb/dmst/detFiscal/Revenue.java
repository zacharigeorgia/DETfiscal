package gr.aueb.dmst.detFiscal;
public class Revenue extends Account {

    // Setters (Τους χρειάζεται ο DataLoader)
    public void setName(String name) {
        this.name = name;
        }
    public void setAmount(double amount) {
        this.amount = amount;
        }

    @Override
    public String getName() {
        return name;
        }
    @Override
    public double getAmount() {
        return amount;
        }

    @Override
    public void increase(double x) {
        this.amount += amount;
    }
    @Override
    public void decrease(double x) {
        this.amount -= amount;
    }
    @Override
    public String toString() {
        return String.format("[Έσοδο] %-40s : %,15.2f ", name, amount);
    }
}
