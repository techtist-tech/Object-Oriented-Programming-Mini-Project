
public class CurrentAccount extends Account {
    private final double overdraftLimit;
    private static final double MONTHLY_FEE = 5.00;

    public CurrentAccount(String accountNumber, double initialBalance, double overdraftLimit) {
        super(accountNumber, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.printf("[%s] Current withdrawal rejected: amount must be positive.%n", accountNumber);
            return;
        }
        if (balance - amount < -overdraftLimit) {
            System.out.printf("[%s] Current withdrawal of %.2f REJECTED: would exceed overdraft limit of %.2f (current balance %.2f).%n",
                    accountNumber, amount, overdraftLimit, balance);
            return;
        }
        balance -= amount;
        if (balance < 0) {
            System.out.printf("[%s] Current withdrawal of %.2f successful (OVERDRAFT within limit). New balance: %.2f%n",
                    accountNumber, amount, balance);
        } else {
            System.out.printf("[%s] Current withdrawal of %.2f successful. New balance: %.2f%n",
                    accountNumber, amount, balance);
        }
    }

    @Override
    public void endOfMonth() {
        balance -= MONTHLY_FEE;
        System.out.printf("[%s] Current month-end: maintenance fee of %.2f deducted. New balance: %.2f%n",
                accountNumber, MONTHLY_FEE, balance);
    }
}
