package model;

public class BankLocation {

    private int branchId;
    private String address;

    public BankLocation(int branchId, String address) {
        this.branchId = branchId;
        this.address = address;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
