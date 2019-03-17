package model;

public class Accounts {

    private int accountId;
    private int customerId;
    private int accountTypeId;
    private int accountNumber;
    private float balance;

    public Accounts(int accountId, int customerId, int accountTypeId, int accountNumber, float balance) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.accountTypeId = accountTypeId;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(int accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
