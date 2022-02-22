package com.example.smartcard.database.dao;

import com.example.smartcard.database.MySqlConnect;
import com.example.smartcard.database.query.QueryCV;
import com.example.smartcard.database.query.QueryTeam;
import com.example.smartcard.model.CVModel;
import com.example.smartcard.model.TeamMemberModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {
    //Constructor
    public TeamDAO(){}

    //Get the Team
    public static List<TeamMemberModel> getTeam(){
        //DB Connection
        Statement statement = null;
        Connection connection;

        //List
        List<TeamMemberModel> members = new ArrayList<>();

        //Query
        try{
            connection = MySqlConnect.getInstance().getDBConnection();

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryTeam.getTeam(statement);

            //Return the CVItems List
            if(rs.first()){
                do{
                    //Get the Title and the Description
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    String description = rs.getString("description");
                    String imageLink = rs.getString("image");

                    //Compile the List
                    TeamMemberModel item = new TeamMemberModel(name + " " + surname, description, imageLink);

                    members.add(item);
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
        return members;
    }
}
