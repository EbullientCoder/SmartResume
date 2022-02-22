package com.example.smartcard.database.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryPortfolio {
    //Get the Portfolio
    public static ResultSet getPortfolio(Statement statement) throws SQLException {
        String query = "SELECT * FROM Project;";

        return statement.executeQuery(query);
    }

    //Insert the Project into the DB
    public static void insertProject(Statement statement, String title, String description, String link){
        try {
            statement.executeUpdate("INSERT INTO Project(title, description, image) " +
                    "VALUES('" + title + "', '" + description + "', '" + link + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
