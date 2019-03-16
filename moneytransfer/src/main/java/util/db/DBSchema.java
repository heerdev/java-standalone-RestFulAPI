package util.db;

import java.util.HashSet;
import java.util.Set;

public class  DBSchema {

    public static final String dropBanklocation="DROP table  IF EXISTS  BANK_LOCATION";
    public static final String dropCustomer="DROP table  IF EXISTS  CUSTOMERS";
    public static final String dropBillingAccount="DROP table  IF EXISTS  BILLING_ACCOUNTS";
    public static final String dropAccounts="DROP table  IF EXISTS  ACCOUNTS";
    public static final String dropAccountType="DROP table  IF EXISTS  ACCOUNT_TYPE";



    public static final String banklocation ="CREATE TABLE  BANK_LOCATION (branch_id INTEGER  not null, address varchar(255))";
    public static final String customer="CREATE TABLE  CUSTOMERS (customer_id INTEGER  not null, customer_name varchar(255), customer_info varchar(255))";
    public static final String billingAccount="CREATE TABLE  BILLING_ACCOUNTS (billing_id INTEGER  not null,customer_id INTEGER  not null, account_id INTEGER  not null,bill_amount float, comments varchar(255))";
    public static final String accounts="CREATE TABLE  ACCOUNTS (account_id INTEGER  not null, customer_id INTEGER  not null,account_type_id INTEGER  not null,account_nmer int,balance float)";
    public static final String accountType="CREATE TABLE  ACCOUNT_TYPE (account_type_id INTEGER  not null, account_type varchar(255))";


    public static Set<String> cleanSchemaScript(){
        Set<String> scripts=new HashSet<String>();
        scripts.add(dropBanklocation);
        scripts.add(dropCustomer);
        scripts.add(dropBillingAccount);
        scripts.add(dropAccounts);
        scripts.add(dropAccountType);

        return scripts;

    }
    public static Set<String> createTableScript(){
        Set<String> scripts=new HashSet<String>();
        scripts.add(banklocation);
        scripts.add(customer);
        scripts.add(billingAccount);
        scripts.add(accounts);
        scripts.add(accountType);
        return scripts;

    }
}
