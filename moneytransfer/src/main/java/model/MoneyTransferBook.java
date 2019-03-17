package model;

import java.util.Date;

public class MoneyTransferBook {

    private boolean isBookPayment;
    private String beneficiaryAccount;
    private float amount;
    private boolean isScheduledPayment;
    private Date paymentDate;

    public MoneyTransferBook(){};
    public MoneyTransferBook(boolean bookPayment, String beneficiaryAccount, float amount, boolean scheduledPayment, Date paymentDate) {
        this.isBookPayment = bookPayment;
        this.beneficiaryAccount = beneficiaryAccount;
        this.amount = amount;
        this.isScheduledPayment = scheduledPayment;
        this.paymentDate = paymentDate;
    }

    public boolean isBookPayment() {
        return isBookPayment;
    }

    public void setBookPayment(boolean bookPayment) {
        this.isBookPayment = bookPayment;
    }

    public String getBeneficiaryAccount() {
        return beneficiaryAccount;
    }

    public void setBeneficiaryAccount(String beneficiaryAccount) {
        this.beneficiaryAccount = beneficiaryAccount;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public boolean isScheduledPayment() {
        return isScheduledPayment;
    }

    public void setScheduledPayment(boolean scheduledPayment) {
        this.isScheduledPayment = scheduledPayment;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
