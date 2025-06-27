import java.util.Scanner;

// Class to represent the user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance >= 0)
            this.balance = initialBalance;
        else
            this.balance = 0;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}

// Class to represent the ATM machine
class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        scanner = new Scanner(System.in);
    }

    public ATM() {
    }

    public void start() {
        int choice;
        do {
            displayMenu();
            choice = getUserChoice();
            handleChoice(choice);
        } while (choice != 4);
    }

    private void displayMenu() {
        System.out.println("\n=== ATM Menu ===");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next(); // discard invalid input
        }
        return scanner.nextInt();
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1 -> checkBalance();
            case 2 -> deposit();
            case 3 -> withdraw();
            case 4 -> System.out.println("Thank you for using the ATM. Goodbye!");
            default -> System.out.println("Invalid choice. Please choose between 1 and 4.");
        }
    }

    private void checkBalance() {
        System.out.printf("Your current balance is: $%.2f%n", account.getBalance());
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = getPositiveAmount();
        account.deposit(amount);
        System.out.printf("Deposited $%.2f successfully.%n", amount);
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = getPositiveAmount();
        if (account.withdraw(amount)) {
            System.out.printf("Withdrawn $%.2f successfully.%n", amount);
        } else {
            System.out.println("Withdrawal failed. Either the amount is invalid or you have insufficient funds.");
        }
    }

    private double getPositiveAmount() {
        double amount;
        while (true) {
            while (!scanner.hasNextDouble()) {
                System.out.print("Invalid input. Enter a valid amount: ");
                scanner.next(); // discard invalid input
            }
            amount = scanner.nextDouble();
            if (amount > 0)
                break;
            System.out.print("Amount must be greater than zero. Try again: ");
        }
        return amount;
    }

    public BankAccount getAccount() {
        return account;
    }

    public void setAccount(BankAccount account) {
        this.account = account;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}

// Main class to run the program
public class ATMProgram {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(500.00); // starting with $500
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}
