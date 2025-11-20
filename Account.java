public abstract class Account {
    protected String name;
    protected double amount;
    public abstract void increase(double x);
    public abstract void decrease(double x);
}