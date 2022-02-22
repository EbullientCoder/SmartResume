package com.example.smartcard.database.dao;

import android.widget.Toast;

import com.example.smartcard.database.MySqlConnect;
import com.example.smartcard.database.query.QueryCV;
import com.example.smartcard.database.query.QueryPortfolio;
import com.example.smartcard.model.CVModel;
import com.example.smartcard.model.ProjectModel;
import com.squareup.picasso.Picasso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PortfolioDAO {
    //Constructor
    public PortfolioDAO(){}

    //Get User's Projects
    public static List<ProjectModel> getProjects(){
        //DB Connection
        Statement statement = null;
        Connection connection;

        //List
        List<ProjectModel> projects = new ArrayList<>();

        //Query
        try{
            connection = MySqlConnect.getInstance().getDBConnection();

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryPortfolio.getPortfolio(statement);

            //Return the CVItems List
            if(rs.first()){
                do{
                    //Get the Title and the Description
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    String imageLink = rs.getString("image");

                    //Compile the List
                    ProjectModel item = new ProjectModel();
                    item.setTitle(title);
                    item.setDescription(description);
                    item.setImageLink(imageLink);
                    projects.add(item);
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
        return projects;
    }


    //Add a Project to the Portfolio
    public static void addProject(String title, String description, String link){
        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            //Insert Project Query
            QueryPortfolio.insertProject(statement, title, description, link);


            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
