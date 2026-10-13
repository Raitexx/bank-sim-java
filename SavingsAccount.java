public class SavingsAccount extends Account implements Printable, Transferable {
    
    //field 
    private double interestRate;
    private boolean isFrozen = false;

    public SavingsAccount(String accountNumber, String holderName, double balance, double interestRate) {
        super(accountNumber, holderName, balance);

        if (interestRate < 0.0 || interestRate > 1.0) {
            throw new IllegalArgumentException("invalid: Interest rate must be between 0.0% and 1.0%.");
        }

        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    // add freeze method which sets the field value to true
    public void freeze() {
        isFrozen = true;
    }

    // add unfreeze method which sets the field value to false
    public void unfreeze() {
        isFrozen = false;
    }

    // add a getter isFrozen to return the field value
    
    public boolean isFrozen() {
        return isFrozen;
    }

    @Override 
    public String toString() {
        String parentInfo = super.toString();
        return parentInfo.replace("Account", "SavingsAccount") + String.format(" |  Interest Rate: %%.1f%", interestRate * 100);
    }

    public void applyInterest() {
        double interest = getBalance() * interestRate;

        if (interest > 0) {
            deposit(interest);
        }
    }

    @Override
        public double calculateInterest() {
            double interest = getBalance() * interestRate;
            return interest;
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

    // Transfer
    // Transferable
    @Override 
    public void transfer(Account target, double amount) throws AccountException {

        withdraw(amount);
        target.deposit(amount);
    }
    
    @Override 
    // add withdraw 
    public void withdraw(double amount) throws AccountException {
        if (isFrozen) {
            throw new FrozenAccountException("Account is frozen.");
        }

        super.withdraw(amount); 
    }
}
