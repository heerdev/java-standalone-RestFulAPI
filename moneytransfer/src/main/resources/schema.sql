DROP table  IF EXISTS  REGISTRATION ;
DROP table  IF EXISTS  ACCOUNT ;
CREATE TABLE   REGISTRATION (id INTEGER not NULL, first VARCHAR(255),last VARCHAR(255), age INTEGER, PRIMARY KEY ( id ));
CREATE TABLE  ACCOUNT (id INTEGER  not null, name varchar(255), account_number INTEGER , amount float );
