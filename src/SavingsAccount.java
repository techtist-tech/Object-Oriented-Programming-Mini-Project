
public class SavingsAccount extends Account {
    private final double minimumBalance;
    private static final double MONTHLY_INTEREST_RATE = 0.02; // 2% per month

    public SavingsAccount(String accountNumber, double initialBalance, double minimumBalance) {
        super(accountNumber, initialBalance);
        this.minimumBalance = minimumBalance;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.printf("[%s] Savings withdrawal rejected: amount must be positive.%n", accountNumber);
            return;
        }
        if (balance - amount < minimumBalance) {
            System.out.printf("[%s] Savings withdrawal of %.2f REJECTED: balance would fall below the minimum of %.2f (current balance %.2f).%n",
                    accountNumber, amount, minimumBalance, balance);
            return;
        }
        balance -= amount;
        System.out.printf("[%s] Savings withdrawal of %.2f successful. New balance: %.2f%n",
                accountNumber, amount, balance);
    }

    @Override
    public void endOfMonth() {
        double interest = balance * MONTHLY_INTEREST_RATE;
        balance += interest;
        System.out.printf("[%s] Savings month-end: interest of %.2f (%.0f%%) added. New balance: %.2f%n",
                accountNumber, interest, MONTHLY_INTEREST_RATE * 100, balance);
    }
}
