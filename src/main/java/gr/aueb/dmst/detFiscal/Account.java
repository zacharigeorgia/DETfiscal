package gr.aueb.dmst.detFiscal;
public  abstract class Account {
    protected String name;
    protected double amount;
    public abstract void increase(double x);
    public abstract void decrease(double x);

    public String getName() {
        return name;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmmount(double newAmount) {
        amount = newAmount;
    }
}
