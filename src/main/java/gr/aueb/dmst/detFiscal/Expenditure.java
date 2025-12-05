package gr.aueb.dmst.detFiscal;

public class Expenditure extends Account {
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
    public void increase(double amount) {
            this.amount += amount;
    }

    @Override
    public void decrease(double amount) {
         this.amount -= amount;
    }
    @Override
    public String toString() {

        return String.format("[Έξοδο] %-40s : %,15.2f €", getName(), getAmount());
    }
}

