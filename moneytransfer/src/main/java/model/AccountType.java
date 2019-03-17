package model;

public class AccountType {

    private int accountTypeId;
    private String accountType;

    public AccountType(int accountTypeId, String accountType) {
        this.accountTypeId = accountTypeId;
        this.accountType = accountType;
    }

    public int getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(int accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
