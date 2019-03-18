package model;

public class MoneyTransferWireICDT {

    private boolean isWirePayment;
    private String debtorAccount;

    private String bic;
    private float amount;

    public MoneyTransferWireICDT(){};

    public MoneyTransferWireICDT(boolean isWirePayment, String debtorAccount, String bic, float amount) {
        this.isWirePayment = isWirePayment;
        this.debtorAccount = debtorAccount;
        this.bic = bic;
        this.amount = amount;
    }

    public boolean isWirePayment() {
        return isWirePayment;
    }

    public void setWirePayment(boolean wirePayment) {
        isWirePayment = wirePayment;
    }

    public String getDebtorAccount() {
        return debtorAccount;
    }

    public void setDebtorAccount(String debtorAccount) {
        this.debtorAccount = debtorAccount;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
