# java-standalone-RestFulAPI

## start app command
mvn clean install -DskipTests exec:java

## Run test on local
Please start the app before running the API test
mvn clean test

## Types money transfer desinged for the app
There are 2 types pf transfer
1. Book transfer
2. ICDT - outing wire transfer
3. RCDT - recieving wire transfer

##API
BOOK
URl: http://localhost:8080/book-transfer
Body:
{"drAccount":"1112","crAccount":"1111","amount":1,"paymentDate":null,"scheduledPayment":false,"bookPayment":true}

RCDT
URL:http://localhost:8080/wire-transfer/rcdt
Body:
{"beneficiaryAccount":"1111","benificiaryBank":"CHASE","scheuledDate":null,"senderRef":"SSSS","amount":100.0,"wirePayment":true,"scheduledPayment":false}

ICDT
URL: http://localhost:8080/wire-transfer/icdt
Body:
{"debtorAccount":"1111","bic":"xxxx","amount":12.0,"wirePayment":true}


