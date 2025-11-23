package gr.aueb.dmst.detFiscal;

public class Revenue {
    private String name;
    private double amount;

    // Setters (Τους χρειάζεται ο DataLoader)
    public void setName(String name) { this.name = name; }
    public void setAmount(double amount) { this.amount = amount; }

    // Getters (Τους χρειάζεται το Test για τα asserts)
    public String getName() { return name; }
    public double getAmount() { return amount; }
}