package model;

import java.util.Date;

public class MoneyTransferBook {

    private boolean isBookPayment;
    private String beneficiaryAccount;
    private float amount;
    private boolean isScheduledPayment;
    private Date paymentDate;

    public MoneyTransferBook(){};

    public MoneyTransferBook(boolean isBookPayment, String beneficiaryAccount, float amount, boolean isScheduledPayment, Date paymentDate) {
        this.isBookPayment = isBookPayment;
        this.beneficiaryAccount = beneficiaryAccount;
        this.amount = amount;
        this.isScheduledPayment = isScheduledPayment;
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

    @Override
    public String toString() {
        return "MoneyTransferBook{" +
                "isBookPayment=" + isBookPayment +
                ", beneficiaryAccount='" + beneficiaryAccount + '\'' +
                ", amount=" + amount +
                ", isScheduledPayment=" + isScheduledPayment +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
