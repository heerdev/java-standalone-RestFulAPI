package service;

import factory.DBConnectionFactory;
import model.MoneyTransferBook;
import model.PaymentTransaction;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PaymentTransactionService {

    public int savePaymentTransacitonDb(PaymentTransaction paymentTransaction){



        String pattern = "dd-MM-yyyy hh:mm:ss";
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(pattern);
        paymentTransaction.setTxn_dt(simpleDateFormat.format(new Date()));

        String insertPaymentTransaction="INSERT INTO payment_transaction (cr_account,dr_account,sender_ref,txn_dt,amount,currency,transfer_type,CrDr,bic) VALUES("+paymentTransaction.getCr_account()+","+paymentTransaction.getDr_account()+","+paymentTransaction.getSender_ref()+",'"+paymentTransaction.getTxn_dt()+"',"+paymentTransaction.getAmount()+","+paymentTransaction.getCurrency()+","+paymentTransaction.getTranferType()+","+paymentTransaction.getCrDr()+","+paymentTransaction.getBic()+")";
        return DBConnectionFactory.extecuteStatment(insertPaymentTransaction);
    }
}
