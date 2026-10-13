
// create class
public abstract class Account {

    // initialise variables within class
    private String accoutnNumber; // user account number
    private String holderName; // account holders name
    private double balance; // balance using double 0.00


    // constructors
    public Account(String accountNumber, String holderName, double balance) {
        this.accoutnNumber = accountNumber;
        this.holderName = holderName;
        
        if ( balance < 0 ) {
            this.balance = 0.00;
        } else {
            this.balance = balance;
        }
    }

    // Getters - allows controlled read access to private fields 
    public String getAccountNumber() {
        return accoutnNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    // Setters - allows controlled write access to private fields,
    // can validate new value before accepting. outside world cannot access them

    public void setHolderName(String name) {
        if (name == null || name.isEmpty()) {
            return ;
        }
        this.holderName = name; 
        // you can also do holderName = name
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid: Deposit must be greater than 0!");
        }
        balance += amount;
        // you can also do this.balance += amount 
    }
    // you can do both ways because the 'this' keyword, is only
    // needed when a parameter and field have the same name

    public void withdraw(double amount) throws AccountException {
        if (amount <=0 || amount > balance) {
            throw new InsufficientFundsException(amount, balance);
        }
        balance -= amount;
    }

    // protected for subclass currentaccount 
    protected void deductBalance(double amount) {
        balance -= amount;
    }

    @Override 
    public String toString() {
        return String.format("Account: %s | Name: %s | Balance: £%.2f", accoutnNumber, holderName, balance);
    }
    
    public static void main(String[] args) {

        // instantiate object
        // Account acc = new Account("ACC001", "Bim Rai", 500.00);
        
        // for abstract below
        // Account bim = new CurrentAccount("ACC001", "Bim Rai", 500.00, 100.00);
        // CurrentAccount bim = new CurrentAccount("ACC001", "Bim Rai", 500.00, 100.00);

        // print object
        // System.out.println(bim);

        //deposit in account
        // bim.deposit(250);
        // then check balance again (should be 750 as 500 + 250 = 750)
        // System.out.println(bim.getBalance());

        // withdraw £50 so £750 - £50 = £700
        // bim.withdraw(50);
        // System.out.println(bim.getBalance());

        // get individual name details
        // System.out.println(bim.getAccountNumber(    )); // number
        // System.out.println(bim.getHolderName()); // name
        // System.out.println(bim.getBalance()); // curent balance

        // second account object owned by Olha
        // CurrentAccount olya = new CurrentAccount("ACC002", "Olha Sahan", 1000.00, 100.00);

        // System.out.println(olya);
        //olya.transfer(bim, 100);

        // check bims balance, expected output £800
        // System.out.println(bim.getBalance());
        // check olya balance, expected output £900
        //System.out.println(olya.getBalance());




        // test for insufficientfundsexception
        
        // SavingsAccount source = new SavingsAccount("ACC001", "Alice", 100.0, 0.03);
        // SavingsAccount target = new SavingsAccount("ACC002", "Bob",   500.0, 0.03);
        
        
        // try {
        //     source.transfer(target, 200.0);  // Should fail — not enough funds
        //     System.out.println("Transfer succeeded.");
        // } catch (InsufficientFundsException e) {
        //     System.out.println("Transfer failed: " + e.getMessage());
        //     System.out.printf("  Shortfall: \u00A3%.2f%n", e.getShortfall());
        // } finally {
        //     // 'finally' runs whether or not an exception occurred.
        //     //  Use it to clean up resources (close files, release locks, etc.)
        //     // Here we use it to log the account states after the attempt.
        //     System.out.println("After attempt:");
        //     System.out.println(source);
        //     System.out.println(target);
        // }


        // testing for Account Exception and FrozenAccountException copy pasted
        SavingsAccount acc = new SavingsAccount("ACC001", "Alice", 100.0, 0.03);
        acc.freeze();
        
        
        try {
            acc.withdraw(50.0);
        } catch (FrozenAccountException e) {
            System.out.println("Frozen: " + e.getMessage());
            // System.out.println("Account: " + e.getAccountNumber());
        } catch (InsufficientFundsException e) {
            System.out.println("Insufficient funds: " + e.getMessage());
        } catch (AccountException e) {
            System.out.println(e.getMessage());
        }
        
        
        
        try {
            acc.withdraw(50.0);
        } catch (FrozenAccountException | InsufficientFundsException e) {
            // Both types handled here
            System.out.println("Account operation failed: " + e.getMessage());
        } catch (AccountException e) {
            System.out.println(e.getMessage());
        }
        
        try {
            acc.withdraw(50.0);
        } catch (AccountException e) {
            System.out.println("General account error: " + e.getMessage());
            System.out.println("Actual type: " + e.getClass().getSimpleName());
        } 

    }

    // declare abstract method
    public abstract double calculateInterest();
}