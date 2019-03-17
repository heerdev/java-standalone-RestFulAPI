package model;

public class BillingAccounts {

    private int billingId;
    private int customerId;
    private int accountId;
    private float billAmount;
    private String comments;

    public BillingAccounts(int billingId, int customerId, int accountId, float billAmount, String comments) {
        this.billingId = billingId;
        this.customerId = customerId;
        this.accountId = accountId;
        this.billAmount = billAmount;
        this.comments = comments;
    }

    public int getBillingId() {
        return billingId;
    }

    public void setBillingId(int billingId) {
        this.billingId = billingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public float getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(float billAmount) {
        this.billAmount = billAmount;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
