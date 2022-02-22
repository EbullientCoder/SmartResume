package com.example.smartcard.database.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryTeam {
    //Get the Team Members
    public static ResultSet getTeam(Statement statement) throws SQLException {
        String query = "SELECT * FROM Member;";

        return statement.executeQuery(query);
    }
}
