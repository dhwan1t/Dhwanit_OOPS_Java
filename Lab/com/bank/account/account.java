package Uni.OOPS_Lab.Lab.com.bank.account;

public class account {
    public static class savingsAccount extends account{
        savingsAccount(int accountNumber, int balance) {
            super(accountNumber, balance);
        }
        int addInterestRate(int rate) {
            return rate / 100;
        }
    }

    int accountNumber;
    static int balance;
    account(int accountNumber, int balance) {
        this.accountNumber = accountNumber;
        account.balance = balance;
    }
    public static void deposit(int add){
        balance += add;
    }
    public static void withdraw(int withdraw){
        balance -= withdraw;
    }
}

