package model;

import java.util.Date;

public class MoneyTransferWireRCDT {

    private boolean isWirePayment;
    private String beneficiaryAccount;
    private String benificiaryBank;
    private boolean isScheduledPayment;
    private Date scheuledDate;
    private String senderRef;
    private float amount;

    public MoneyTransferWireRCDT(){}

    public MoneyTransferWireRCDT(boolean isWirePayment, String beneficiaryAccount, String benificiaryBank, boolean isScheduledPayment, Date scheuledDate, String senderRef, float amount) {
        this.isWirePayment = isWirePayment;
        this.beneficiaryAccount = beneficiaryAccount;
        this.benificiaryBank = benificiaryBank;
        this.isScheduledPayment = isScheduledPayment;
        this.scheuledDate = scheuledDate;
        this.senderRef = senderRef;
        this.amount = amount;
    }

    public boolean isWirePayment() {
        return isWirePayment;
    }

    public void setWirePayment(boolean wirePayment) {
        isWirePayment = wirePayment;
    }

    public String getBeneficiaryAccount() {
        return beneficiaryAccount;
    }

    public void setBeneficiaryAccount(String beneficiaryAccount) {
        this.beneficiaryAccount = beneficiaryAccount;
    }

    public String getBenificiaryBank() {
        return benificiaryBank;
    }

    public void setBenificiaryBank(String benificiaryBank) {
        this.benificiaryBank = benificiaryBank;
    }

    public boolean isScheduledPayment() {
        return isScheduledPayment;
    }

    public void setScheduledPayment(boolean scheduledPayment) {
        isScheduledPayment = scheduledPayment;
    }

    public Date getScheuledDate() {
        return scheuledDate;
    }

    public void setScheuledDate(Date scheuledDate) {
        this.scheuledDate = scheuledDate;
    }

    public String getSenderRef() {
        return senderRef;
    }

    public void setSenderRef(String senderRef) {
        this.senderRef = senderRef;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "MoneyTransferWireRCDT{" +
                "isWirePayment=" + isWirePayment +
                ", beneficiaryAccount='" + beneficiaryAccount + '\'' +
                ", benificiaryBank='" + benificiaryBank + '\'' +
                ", isScheduledPayment=" + isScheduledPayment +
                ", scheuledDate=" + scheuledDate +
                ", senderRef='" + senderRef + '\'' +
                ", amount=" + amount +
                '}';
    }
}
