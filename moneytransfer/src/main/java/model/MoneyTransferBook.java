package model;

import java.util.Date;
public class MoneyTransferBook {

    private boolean isBookPayment;
    private String drAccount;
    private String crAccount;
    private float amount;
    private boolean isScheduledPayment;
    private Date paymentDate;

    public MoneyTransferBook(){};

    public MoneyTransferBook(boolean isBookPayment, String drAccount, String crAccount, float amount, boolean isScheduledPayment, Date paymentDate) {
        this.isBookPayment = isBookPayment;
        this.drAccount = drAccount;
        this.crAccount = crAccount;
        this.amount = amount;
        this.isScheduledPayment = isScheduledPayment;
        this.paymentDate = paymentDate;
    }

    public boolean isBookPayment() {
        return isBookPayment;
    }

    public void setBookPayment(boolean bookPayment) {
        isBookPayment = bookPayment;
    }

    public String getDrAccount() {
        return drAccount;
    }

    public void setDrAccount(String drAccount) {
        this.drAccount = drAccount;
    }

    public String getCrAccount() {
        return crAccount;
    }

    public void setCrAccount(String crAccount) {
        this.crAccount = crAccount;
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
        isScheduledPayment = scheduledPayment;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
