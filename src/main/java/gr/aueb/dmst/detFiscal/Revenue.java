public class Revenue extends Account {
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
