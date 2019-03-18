package util.db;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class  DBSchema {

    public static final String dropBankLocation ="DROP table  IF EXISTS  BANK_LOCATION";
    public static final String dropCustomer="DROP table  IF EXISTS  CUSTOMERS";
    public static final String dropBillingAccount="DROP table  IF EXISTS  BILLING_ACCOUNTS";
    public static final String dropAccounts="DROP table  IF EXISTS  ACCOUNTS";
    public static final String dropAccountType="DROP table  IF EXISTS  ACCOUNT_TYPE";
    public static final String dropPaymentTransaction="DROP table  IF EXISTS  PAYMENT_TRANSACTION";



    public static final String bankLocation ="CREATE TABLE  BANK_LOCATION (branch_id INTEGER  not null, address varchar(255),PRIMARY KEY (branch_id))";
    public static final String customer="CREATE TABLE  CUSTOMERS (customer_id INTEGER  not null, customer_name varchar(255), customer_info varchar(255),PRIMARY KEY (customer_id))";
    public static final String billingAccount="CREATE TABLE  BILLING_ACCOUNTS (billing_id INTEGER  not null,customer_id INTEGER  not null, account_id INTEGER  not null,bill_amount float, comments varchar(255),PRIMARY KEY (billing_id))";
    public static final String accounts="CREATE TABLE  ACCOUNTS (account_id INTEGER  not null, customer_id INTEGER  not null,account_type_id INTEGER  not null,account_number varchar(20),balance float,PRIMARY KEY (account_id))";
    public static final String accountType="CREATE TABLE  ACCOUNT_TYPE (account_type_id INTEGER  not null, account_type varchar(255),PRIMARY KEY (account_type_id))";

    public static final String paymentTransaction="CREATE TABLE PAYMENT_TRANSACTION(pymt_txn_id INTEGER  not null auto_increment,cr_account INTEGER not null,dr_account INTEGER , sender_ref varchar(255),txn_dt varchar(255) ,amount float ,currency varchar(255) , transfer_type varchar(255) , CrDr varchar(10),bic varchar(255),PRIMARY KEY (pymt_txn_id))";




    public static final String createAccoun1="INSERT INTO ACCOUNTS values(1,2,3,'1111',100)";
    public static final String createAccoun2="INSERT INTO ACCOUNTS values(10,20,1,'1112',50)";
    public static final String createCustomer1="INSERT INTO CUSTOMERS  values(2,'zubair','mumbai')";
    public static final String createCustomer2="INSERT INTO CUSTOMERS  values(20,'john','london')";

    public static Set<String> cleanSchemaScript(){
        Set<String> scripts=new HashSet<String>();
        scripts.add(dropBankLocation);
        scripts.add(dropCustomer);
        scripts.add(dropBillingAccount);
        scripts.add(dropAccounts);
        scripts.add(dropAccountType);
        scripts.add(dropPaymentTransaction);

        return scripts;

    }
    public static Set<String> createTableScript(){
        Set<String> scripts=new HashSet<String>();
        scripts.add(bankLocation);
        scripts.add(customer);
        scripts.add(billingAccount);
        scripts.add(accounts);
        scripts.add(accountType);
        scripts.add(paymentTransaction);
        return scripts;

    }

    public static Set<String> createDataScript(){
        Set<String> scripts=new HashSet<String>();
        scripts.add(createAccoun1);
        scripts.add(createAccoun2);
        scripts.add(createCustomer1);
        scripts.add(createCustomer2);

        return scripts;

    }
}
