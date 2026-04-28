package Uni.OOPS_Lab.Lab;

class BankingException extends Exception {
    public BankingException(String message) {
        super(message);
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) throws BankingException {
        if (balance <= 0) {
            throw new BankingException("Withdrawal failed: Account is empty.");
        }

        if (amount > balance) {
            throw new BankingException("Withdrawal failed: Insufficient funds.");
        }

        balance -= amount;
        System.out.println("Withdrawal successful. Remaining balance: " + balance);
    }
    public void setBalance(int b){
        balance = b;
    }

    public double getBalance() {
        return balance;
    }

}

public class exceptions {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(0);
        account.setBalance(100);
        try {
            account.withdraw(50);
        } catch (BankingException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("End of transaction");
        }
    }
}