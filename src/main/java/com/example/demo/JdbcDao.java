package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import  java.sql.SQLException;

public class JdbcDao {

    //Variables to store information. Used in the Connection (to connect the database)
    private static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/register_schema?useSSL=false";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "root";
    private static final String INSERT_QUERY = "INSERT INTO registration_info " +
            "(full_name, email_id, password) VALUES (?, ?, ?)";

    public void insertRecord(String fullName, String emailId, String password) throws SQLException {
        try (Connection connection = DriverManager.getConnection
                (DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        //Creates a statement using the connection object instead of hard setting values
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY))
        {
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, emailId);
            preparedStatement.setString(3, password);

            System.out.println(preparedStatement);
            //Executes the query
            preparedStatement.executeUpdate();
        } catch (SQLException exception){
            printSQLException(exception);
        }
    }

    public static void printSQLException(SQLException ex){
        for(Throwable e:ex){
            if(e instanceof SQLException){
               e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable throwable = ex.getCause();
                while (throwable != null) {
                    System.out.println("Cause: " + throwable);
                    throwable = throwable.getCause();
                }
            }
        }
    }
}



