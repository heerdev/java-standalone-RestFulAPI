package model;

import java.util.Date;

public class PaymentTransaction {

    private int id;
    private String cr_account;
    private String dr_account;
    private String sender_ref;
    private Date txn_dt;
    private float amount;
    private String currency;
    private String tranferType;
    private String crDr;


    public PaymentTransaction(){};

    public PaymentTransaction(int id, String cr_account, String dr_account, String sender_ref, Date txn_dt, float amount, String currency, String tranferType, String crDr) {
        this.id = id;
        this.cr_account = cr_account;
        this.dr_account = dr_account;
        this.sender_ref = sender_ref;
        this.txn_dt = txn_dt;
        this.amount = amount;
        this.currency = currency;
        this.tranferType = tranferType;
        this.crDr = crDr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCr_account() {
        return cr_account;
    }

    public void setCr_account(String cr_account) {
        this.cr_account = cr_account;
    }

    public String getDr_account() {
        return dr_account;
    }

    public void setDr_account(String dr_account) {
        this.dr_account = dr_account;
    }

    public String getSender_ref() {
        return sender_ref;
    }

    public void setSender_ref(String sender_ref) {
        this.sender_ref = sender_ref;
    }

    public Date getTxn_dt() {
        return txn_dt;
    }

    public void setTxn_dt(Date txn_dt) {
        this.txn_dt = txn_dt;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTranferType() {
        return tranferType;
    }

    public void setTranferType(String tranferType) {
        this.tranferType = tranferType;
    }

    public String getCrDr() {
        return crDr;
    }

    public void setCrDr(String crDr) {
        this.crDr = crDr;
    }
}
