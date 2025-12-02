public class Revenue extends Account {
    private String name;
    private double amount;

    // Setters (Τους χρειάζεται ο DataLoader)
    public void setName(String name) { this.name = name; }
    public void setAmount(double amount) { this.amount = amount; }

    // Getters (Τους χρειάζεται το Test για τα asserts)
    public String getName() { return name; }
    public double getAmount() { return amount; }
    
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
        return String.format("[Έσοδο] %-40s : %,15.2f €", name, amount);
    }
}
