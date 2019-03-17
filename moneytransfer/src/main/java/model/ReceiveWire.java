package model;

import java.util.Date;

public class ReceiveWire {

    private boolean isWirePayment;
    private String beneficiaryAccount;
    private String senderRef;

    public ReceiveWire(){};

    public ReceiveWire(boolean isWirePayment, String beneficiaryAccount, String senderRef) {
        this.isWirePayment = isWirePayment;
        this.beneficiaryAccount = beneficiaryAccount;
        this.senderRef = senderRef;
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

    public String getSenderRef() {
        return senderRef;
    }

    public void setSenderRef(String senderRef) {
        this.senderRef = senderRef;
    }
}
