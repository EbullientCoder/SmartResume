package com.example.smartcard.database.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryUser {

    //Get the User Credentials
    public static ResultSet getCredentials(Statement statement) throws SQLException {
        String query = "SELECT * FROM User;";

        return statement.executeQuery(query);
    }
}
