public class InsufficientFundsException extends AccountException {
    
    // fields
    private final double requestedAmount;
    private final double currentBalance;


    // constructor
    public InsufficientFundsException(double requestedAmount, double currentBalance) {
        super(String.format("Insufficient Funds: Requested £%.2f but balance is only £%.2f.", requestedAmount, currentBalance));
        
        this.requestedAmount = requestedAmount;
        this.currentBalance = currentBalance;
    }
    
    // second constructor accepting throwable cause
    public InsufficientFundsException(double requestedAmount,
        double currentBalance,
        Throwable cause) {
            super(String.format(
                "Insufficient Funds: Requested £%.2f but balance is only £%.2f.", requestedAmount, currentBalance
            ), cause);

            this.requestedAmount = requestedAmount;
            this.currentBalance = currentBalance;
    }

    // getter
    public double getRequestedAmount() {
        return requestedAmount;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    // convenience method getShortfall()
    public double getShortfall() {
        return requestedAmount - currentBalance;
    }

}

