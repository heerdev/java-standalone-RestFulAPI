package model;

public class Customers {

    private int customerId;
    private String customerName;
    private String customerInfo;

    public Customers(int customerId, String customerName, String customerInfo) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerInfo = customerInfo;
    }

    public Customers(){}
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }
}

