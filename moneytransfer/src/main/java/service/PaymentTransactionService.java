package service;

import database.mapper.AccountsRowMapper;
import database.mapper.DBEntityMapper;
import factory.DBConnectionFactory;
import model.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Set;


public class PaymentTransactionService {

    public int savePaymentTransactionDb(PaymentTransaction paymentTransaction){
        String pattern = "dd-MM-yyyy hh:mm:ss";
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(pattern);
        paymentTransaction.setTxn_dt(simpleDateFormat.format(new Date()));

        String insertPaymentTransaction="INSERT INTO payment_transaction (cr_account,dr_account,sender_ref,txn_dt,amount,currency,transfer_type,CrDr,bic) VALUES("+paymentTransaction.getCr_account()+","+paymentTransaction.getDr_account()+","+paymentTransaction.getSender_ref()+",'"+paymentTransaction.getTxn_dt()+"',"+paymentTransaction.getAmount()+","+paymentTransaction.getCurrency()+","+paymentTransaction.getTranferType()+","+paymentTransaction.getCrDr()+","+paymentTransaction.getBic()+")";

        return DBConnectionFactory.extecuteStatment(insertPaymentTransaction);
    }

    public boolean bookTransfer(MoneyTransferBook moneyTransferBook) throws IOException {
        DBEntityMapper<Accounts> dbEntityMapper= new AccountsRowMapper();
        Set<Accounts> accounts;
        Accounts crAcct=null;
        Accounts drAcct=null;
        ResultSet resultSet= DBConnectionFactory.getResultSet("select * from accounts where account_number in ("+moneyTransferBook.getCrAccount()+","+moneyTransferBook.getDrAccount()+")");

        try {
            accounts= dbEntityMapper.dbMapper(resultSet);
            for (Accounts account: accounts) {
                if(account.getAccountNumber().equalsIgnoreCase(moneyTransferBook.getCrAccount())){
                        crAcct=account;
                    } else if(account.getAccountNumber().equalsIgnoreCase(moneyTransferBook.getDrAccount())){
                        drAcct = account;
                }
            }
            if(drAcct.getBalance()>moneyTransferBook.getAmount()) {
                //deduct the money from the creditor
                float crCurrentBal=crAcct.getBalance() + moneyTransferBook.getAmount();
                float drCurrentBal=drAcct.getBalance() - moneyTransferBook.getAmount();

                //update creditor account balance
                int updateCrBalance=updateAccountBalance(crAcct, crCurrentBal);

                //update payment transaction table
                PaymentTransaction paymentTransaction= new PaymentTransaction();
                paymentTransaction.setCr_account( moneyTransferBook.getCrAccount());
                paymentTransaction.setDr_account(moneyTransferBook.getDrAccount());
                paymentTransaction.setAmount(moneyTransferBook.getAmount());

                paymentTransaction.setCurrency("'USD'");
                paymentTransaction.setTranferType("'BOOK'");
                int pymntTxnUpdate = savePaymentTransactionDb(paymentTransaction);

                //update debtor account balance
                int updateDrBalance=updateAccountBalance(drAcct, drCurrentBal);
                if(updateDrBalance==1 && pymntTxnUpdate ==1 && updateCrBalance==1) {
                    DBConnectionFactory.commit();
                    return true;
                }else{
                    DBConnectionFactory.rollBack();
                    return false;
                }

            }else{
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean wireTransferRCDT(MoneyTransferWireRCDT moneyTransferWireRCDT) throws IOException {
        DBEntityMapper<Accounts> dbEntityMapper= new AccountsRowMapper();
        Set<Accounts> accounts;
        Accounts crAcct=null;

        ResultSet resultSet= DBConnectionFactory.getResultSet("select * from accounts where account_number = "+moneyTransferWireRCDT.getBeneficiaryAccount());

        try {
            accounts= dbEntityMapper.dbMapper(resultSet);
            for (Accounts account: accounts) {
                if(account.getAccountNumber().equalsIgnoreCase(moneyTransferWireRCDT.getBeneficiaryAccount())) {
                    crAcct = account;
                }
            }

                //deduct the money from the creditor
                float crCurrentBal=crAcct.getBalance() + moneyTransferWireRCDT.getAmount();


                //update beneficiary account balance
                int updateCrBalance=updateAccountBalance(crAcct, crCurrentBal);

                //update payment transaction table
                PaymentTransaction paymentTransaction= new PaymentTransaction();
                paymentTransaction.setCr_account( moneyTransferWireRCDT.getBeneficiaryAccount());
                paymentTransaction.setTranferType("'WIRE'");
                paymentTransaction.setCurrency("'USD'");
                paymentTransaction.setSender_ref("'"+moneyTransferWireRCDT.getSenderRef()+"'");
                paymentTransaction.setAmount(moneyTransferWireRCDT.getAmount());
                int pymntTxnUpdate = savePaymentTransactionDb(paymentTransaction);

                //update debtor account balance

                if(pymntTxnUpdate ==1 && updateCrBalance==1) {
                    DBConnectionFactory.commit();
                    return true;
                }else{
                    DBConnectionFactory.rollBack();
                    return false;
                }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean wireTransferICDT(MoneyTransferWireICDT moneyTransferWireICDT) throws IOException {
        DBEntityMapper<Accounts> dbEntityMapper= new AccountsRowMapper();
        Set<Accounts> accounts;
       // Accounts crAcct=null;
        Accounts drAcct=null;
        ResultSet resultSet= DBConnectionFactory.getResultSet("select * from accounts where account_number ="+moneyTransferWireICDT.getDebtorAccount());

        try {
            accounts= dbEntityMapper.dbMapper(resultSet);
            for (Accounts account: accounts) {
                if(account.getAccountNumber().equalsIgnoreCase(moneyTransferWireICDT.getDebtorAccount())){
                    drAcct = account;
                }
            }
            if(drAcct.getBalance()>moneyTransferWireICDT.getAmount()) {
                //deduct the money from the creditor

                float drCurrentBal=drAcct.getBalance() - moneyTransferWireICDT.getAmount();



                //update payment transaction table
                PaymentTransaction paymentTransaction= new PaymentTransaction();
                paymentTransaction.setDr_account(moneyTransferWireICDT.getDebtorAccount());
                paymentTransaction.setAmount(moneyTransferWireICDT.getAmount());
                paymentTransaction.setBic("'"+moneyTransferWireICDT.getBic()+"'");
                paymentTransaction.setCurrency("'USD'");
                paymentTransaction.setTranferType("'WIRE'");
                int pymntTxnUpdate = savePaymentTransactionDb(paymentTransaction);

                //update debtor account balance
                int updateDrBalance=updateAccountBalance(drAcct, drCurrentBal);
                if(updateDrBalance==1 && pymntTxnUpdate ==1 ) {
                    DBConnectionFactory.commit();
                    return true;
                }else{
                    DBConnectionFactory.rollBack();
                    return false;
                }

            }else{
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    private int updateAccountBalance(Accounts crAcct, float crCurrentBal) {
        String insertDrAccountBal="update accounts set balance="+crCurrentBal +"where account_number="+crAcct.getAccountNumber();
        return DBConnectionFactory.extecuteStatment(insertDrAccountBal);
    }
}
