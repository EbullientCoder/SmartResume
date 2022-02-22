package com.example.smartcard.database.dao;


import com.example.smartcard.database.MySqlConnect;
import com.example.smartcard.database.query.QueryCV;
import com.example.smartcard.model.CVModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CurriculumDAO {
    //Constructor
    private CurriculumDAO(){}

    //Get Curriculum Vitae
    public static List<CVModel> getCurriculum() {
        //DB Connection
        Statement statement = null;
        Connection connection;

        //List
        List<CVModel> CVitems = new ArrayList<>();

        //Query
        try{
            connection = MySqlConnect.getInstance().getDBConnection();

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryCV.getCV(statement);

            //Return the CVItems List
            if(rs.first()){
                do{
                    //Get the Title and the Description
                    String title = rs.getString("title");
                    String description = rs.getString("description");

                    //Compile the List
                    CVModel item = new CVModel(title, description);
                    CVitems.add(item);
                }
                while(rs.next());
            }
            rs.close();

            statement.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        //Return the List
        return CVitems;
    }


    //Add an Experience to the Curriculum
    public static void addExperience(String date, String description){
        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            //Insert Experience Query
            QueryCV.insertExperience(statement, date, description);


            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
