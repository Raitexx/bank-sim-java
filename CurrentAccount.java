public class CurrentAccount extends Account implements Printable, Transferable {
    
    // field / variables 
    private double overdraftLimit; // max amount balance can go below 0


    // Constructor: lets Account set up its fields, then sets
    // up CurrentAccount's overdraft limit variable/field
    public CurrentAccount(String accountNumber, String holderName, double balance, double overdraftLimit) {
        super(accountNumber, holderName, balance); // super calls Accounts constructor
        this.overdraftLimit = overdraftLimit; // add new field as parameter which is its own, not from Account
    }

    // write getOverdraftLimit
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    @Override 
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }

        if(getBalance() - amount < -overdraftLimit) {
            throw new IllegalArgumentException("Overdraft limit exceeded.");
        }

        deductBalance(amount); // protected method from account class 
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Overdraft Limit: £%.2f", overdraftLimit);
    }

    // abstract method
    @Override
    public double calculateInterest() {
        return 0.0;
    }

    // printable
    @Override 
    public void printDetails() {
        System.out.println(this);
    }
    // printable
    @Override
    public String getSummary() {
        return toString();
    }

    // Transferable
    @Override 
    public void transfer(Account target, double amount) {
        if (target == null) {
            throw new IllegalArgumentException("Target account required!");
        }

        withdraw(amount);
        target.deposit(amount);
    }
}
