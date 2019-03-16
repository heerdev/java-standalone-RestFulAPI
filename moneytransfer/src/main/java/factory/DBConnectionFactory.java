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
            System.out.println("connected to database " +connection.toString());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

  /*  public static Connection getConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("connected to database " +connection.toString());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }*/
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

    public static void extecuteStatment(String query){
        System.out.println("executing statement " +query);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Executed  query :>>"+query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

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
