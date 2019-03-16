package util.db;

import java.util.HashSet;
import java.util.Set;

public class  DBSchema {

    public static final String dropRegistration="DROP table  IF EXISTS  REGISTRATION";

    public static final String dropAccount="DROP table  IF EXISTS  ACCOUNT";

    public static final String createRegistration="CREATE TABLE   REGISTRATION (id INTEGER not NULL, first VARCHAR(255),last VARCHAR(255), age INTEGER, PRIMARY KEY ( id ))";
    public static final String createAccount="CREATE TABLE  ACCOUNT (id INTEGER  not null, name varchar(255), account_number INTEGER , amount float )";



    public static Set<String> getSchemaScript(){
        Set<String> scripts=new HashSet<String>();
        scripts.add(dropRegistration);
        scripts.add(dropAccount);
        scripts.add(createRegistration);
        scripts.add(createAccount);
        return scripts;

    }
}
