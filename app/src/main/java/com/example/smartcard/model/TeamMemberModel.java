package com.example.smartcard.model;

import java.io.Serializable;

public class TeamMemberModel implements Serializable {
    //Attributes
    private String name;
    private String desc;
    private String imageLink;

    //Constructor
    public TeamMemberModel(String name, String desc, String imageLink) {
        this.name = name;
        this.desc = desc;
        this.imageLink = imageLink;
    }

    //Getter
    public String getName() {
        return name;
    }
    public String getDesc() {
        return desc;
    }
    public String getImageLink() {
        return imageLink;
    }

    //Setter
    public void setName(String name) {
        this.name = name;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
