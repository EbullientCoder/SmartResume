package com.example.smartcard.facade;

import android.widget.Toast;

import com.example.smartcard.activities.SplashScreen;
import com.example.smartcard.database.dao.CurriculumDAO;
import com.example.smartcard.database.dao.PortfolioDAO;
import com.example.smartcard.database.dao.TeamDAO;
import com.example.smartcard.database.dao.UserDAO;
import com.example.smartcard.model.CVModel;
import com.example.smartcard.model.ProjectModel;
import com.example.smartcard.model.TeamMemberModel;
import com.example.smartcard.model.UserModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class GetUser{

    //Methods
    public UserModel getUser(){
        //Using the Parallel execution to speed up the process
        List<String> credentials = UserDAO.getUserCredentials();
        List<CVModel> CVItems = CurriculumDAO.getCurriculum();
        List<ProjectModel> projects = PortfolioDAO.getProjects();
        List<TeamMemberModel> members = TeamDAO.getTeam();

        //Reverse the Lists
        Collections.reverse(CVItems);
        Collections.reverse(projects);

        //Create User Model
        UserModel user = new UserModel();

        user.setFirstname(credentials.get(0));
        user.setLastname(credentials.get(1));
        user.setWork(credentials.get(2));
        //Curriculum
        user.setCurriculum(CVItems);
        //Projects
        user.setProjects(projects);
        //Members
        user.setTeam(members);

        return user;
    }
}
