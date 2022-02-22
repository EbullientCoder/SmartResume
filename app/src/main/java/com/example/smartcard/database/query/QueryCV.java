package com.example.smartcard.database.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryCV {

    //Get the CV
    public static ResultSet getCV(Statement statement) throws SQLException {
        String query = "SELECT * FROM CV;";

        return statement.executeQuery(query);
    }

    //Insert the Experience into the DB
    public static void insertExperience(Statement statement, String date, String description){
        try {
            statement.executeUpdate("INSERT INTO CV(title, description) " +
                    "VALUES('" + date + "', '" + description + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
