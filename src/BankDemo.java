import java.util.ArrayList;
import java.util.List;

public class BankDemo {
    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new SavingsAccount("SAV-001", 1000.00, 200.00));
        accounts.add(new CurrentAccount("CUR-001", 500.00, 300.00));
        accounts.add(new SavingsAccount("SAV-002", 250.00, 100.00));
        accounts.add(new CurrentAccount("CUR-002", 100.00, 150.00));

        System.out.println("=== Initial balances ===");
        for (Account a : accounts) {
            System.out.printf("%s : %.2f%n", a.getAccountNumber(), a.getBalance());
        }

        System.out.println("\n=== Deposit validation ===");
        accounts.get(0).deposit(100.00);
        accounts.get(0).deposit(-50.00);


        System.out.println("\n=== Polymorphic withdraw(400) loop ===");
        for (Account a : accounts) {
            a.withdraw(400.00);
        }


        System.out.println("\n=== Edge case 1: Savings withdrawal rejected ===");
        Account savings = accounts.get(2);
        savings.withdraw(200.00);


        System.out.println("\n=== Edge case 2: Current account overdraft within limit ===");
        Account current = accounts.get(1);
        current.withdraw(300.00);
        System.out.println("Attempting to exceed the overdraft limit:");
        current.withdraw(200.00);


        System.out.println("\n=== Polymorphic endOfMonth() loop ===");
        for (Account a : accounts) {
            a.endOfMonth();
        }

        System.out.println("\n=== Final balances ===");
        for (Account a : accounts) {
            System.out.printf("%s : %.2f%n", a.getAccountNumber(), a.getBalance());
        }
    }
}
