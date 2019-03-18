package factory;

import util.db.DBSchema;

import java.sql.*;

public class DBConnectionFactory {


static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";
    private static  Connection connection;
    private  Statement statement;
    private ResultSet resultSet;

    static {
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            connection.setAutoCommit(false);
            System.out.println("connected to database " +connection.toString());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public static void createSchemaTable(){
        System.out.println("Creating table in given database...");

        try {
            Statement statement = connection.createStatement();
            for (String script: DBSchema.cleanSchemaScript()) {
                statement.executeUpdate(script);
                System.out.println(">>"+script);
            }

            for (String script: DBSchema.createTableScript()) {
                statement.executeUpdate(script);
                System.out.println(">>"+script);
            }

            System.out.println("Created table in given database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static int extecuteStatment(String query){
        System.out.println("executing statement " +query);
        int rowCount=0;
        try {
            Statement statement = connection.createStatement();
            rowCount= statement.executeUpdate(query);
            System.out.println("Executed  query :>>"+query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  rowCount;
    }

    public static ResultSet getResultSet(String query){
        System.out.println("executing statement " +query);
        ResultSet resultSet=null;
        try {
            Statement statement = connection.createStatement();
            resultSet=statement.executeQuery(query);
            System.out.println("Executed  query :>>"+query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
     return resultSet;
    }

    public static void commit()  {
        try {

            connection.commit();
            System.out.println("committed to DB is closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollBack()  {
        try {

            connection.rollback();
            System.out.println("committed to DB is closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeConnection()  {
        try {
            Statement statement = connection.createStatement();
            statement.close();
            connection.close();
            System.out.println("Connection to DB is closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
