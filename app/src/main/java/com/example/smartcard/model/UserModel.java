package com.example.smartcard.model;

import java.io.Serializable;
import java.util.List;

public class UserModel implements Serializable {
    //Attributes
    private String firstname;
    private String lastname;
    private String work;
    private List<CVModel> curriculum;
    private List<ProjectModel> projects;
    private List<TeamMemberModel> team;

    //Getter
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getWork() {
        return work;
    }
    public List<CVModel> getCurriculum() {
        return curriculum;
    }
    public List<ProjectModel> getProjects() {
        return projects;
    }
    public List<TeamMemberModel> getTeam() {
        return team;
    }

    //Setter
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setWork(String work) {
        this.work = work;
    }
    public void setCurriculum(List<CVModel> curriculum) {
        this.curriculum = curriculum;
    }
    public void setProjects(List<ProjectModel> projects) {
        this.projects = projects;
    }
    public void setTeam(List<TeamMemberModel> team) {
        this.team = team;
    }
}
