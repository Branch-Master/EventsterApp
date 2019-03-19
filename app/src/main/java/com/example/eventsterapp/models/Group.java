package com.example.eventsterapp.models;

import java.util.ArrayList;

public class Group {
    private String groupName;
    private String groupInfo;
    private Long id;
    private ArrayList<User> members;
    private Boolean visable;

    public Group(){}

    public Group(String groupName, String groupInfo, Long id, Boolean visable) {
        this.groupName = groupName;
        this.groupInfo = groupInfo;
        this.id = id;
        this.members = new ArrayList<>();
        this.visable = visable;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupInfo() {
        return groupInfo;
    }

    public void setGroupInfo(String groupInfo) {
        this.groupInfo = groupInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<User> members) {
        this.members = members;
    }

    public Boolean getVisable() {
        return visable;
    }

    public void setVisable(Boolean visable) {
        this.visable = visable;
    }
}
