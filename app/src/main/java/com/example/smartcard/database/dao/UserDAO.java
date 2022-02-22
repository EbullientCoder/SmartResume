package com.example.smartcard.database.dao;

import com.example.smartcard.database.MySqlConnect;
import com.example.smartcard.database.query.QueryCV;
import com.example.smartcard.database.query.QueryUser;
import com.example.smartcard.model.CVModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    //Constructor
    private UserDAO(){}

    //Get User Credentials
    public static List<String> getUserCredentials(){
        //DB Connection
        Statement statement = null;
        Connection connection;

        //List
        List<String> strings = new ArrayList<>();

        //Query
        try{
            connection = MySqlConnect.getInstance().getDBConnection();

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryUser.getCredentials(statement);

            //Return the CVItems List
            if(rs.first()){
                //Get the Title and the Description
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String work = rs.getString("work");

                //Compile the List
                strings.add(name);
                strings.add(surname);
                strings.add(work);
            }
            rs.close();

            statement.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        //Return the List
        return strings;
    }
}
