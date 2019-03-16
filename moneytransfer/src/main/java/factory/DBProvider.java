package factory;

import util.db.DBSchema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBProvider {


    private final Connection connection;
    private final Statement statement;

    public DBProvider(DBProviderBuilder dbProviderBuilder) {
        this.connection = dbProviderBuilder.connection;
        this.statement = dbProviderBuilder.statement;
    }


    public static class DBProviderBuilder {

        static final String JDBC_DRIVER = "org.h2.Driver";
        static final String DB_URL = "jdbc:h2:~/test";

        //  Database credentials
        static final String USER = "sa";
        static final String PASS = "";
        private  Connection connection;
        private  Statement statement;

        public DBProviderBuilder() {

        }

        public  DBProviderBuilder getConnection() {
            try {
                Class.forName(JDBC_DRIVER);
                System.out.println("Connecting to database...");
                this.connection = DriverManager.getConnection(DB_URL,USER,PASS);
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            return this;

        }

        public  DBProviderBuilder extecuteStatment(){
            System.out.println("Creating table in given database...");

            try {
                this.statement = connection.createStatement();
                for (String script: DBSchema.getSchemaScript()) {
                    statement.executeUpdate(script);
                    System.out.println(">>"+script);
                }


                System.out.println("Created table in given database...");
            } catch (SQLException e) {
                e.printStackTrace();
            }



            return this;
        }

        public  DBProviderBuilder closeConnection()  {
            try {
                this.statement.close();
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return this;
        }

        public DBProvider build() {
            DBProvider dbProvider =  new DBProvider(this);

            return dbProvider;
        }

    }



}

