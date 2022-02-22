package com.example.smartcard.model;

import java.io.Serializable;

public class ProjectModel implements Serializable {
    //Attributes
    private String title;
    private String description;
    private String imageLink;

    //Constructor
    public ProjectModel(){ }

    public ProjectModel(String title, String description, String imageLink) {
        this.title = title;
        this.description = description;
        this.imageLink = imageLink;
    }

    //Getter
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getImageLink() {
        return imageLink;
    }

    //Setter
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
