package model;

import java.util.Date;

public class MoneyTransferWire {

    private boolean isWirePayment;
    private String beneficiaryAccount;
    private String benificiaryBank;
    private boolean isScheduledPayment;
    private Date scheuledDate;
    private String bic;

    public MoneyTransferWire(){}

    public MoneyTransferWire(boolean isWirePayment, String beneficiaryAccount, String benificiaryBank, boolean isScheduledPayment, Date scheuledDate, String bic) {
        this.isWirePayment = isWirePayment;
        this.beneficiaryAccount = beneficiaryAccount;
        this.benificiaryBank = benificiaryBank;
        this.isScheduledPayment = isScheduledPayment;
        this.scheuledDate = scheuledDate;
        this.bic = bic;
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

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }
}
