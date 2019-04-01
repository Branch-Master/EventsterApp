package com.example.eventsterapp.models;

import java.util.ArrayList;

public class Group {
    private String groupName;
    private String groupInfo;
    private ArrayList<User> members;
    private int visable;

    public Group(){}

    public Group(String groupName, String groupInfo, int visable) {
        this.groupName = groupName;
        this.groupInfo = groupInfo;
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

    public ArrayList<User> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<User> members) {
        this.members = members;
    }

    public int getVisable() {
        return visable;
    }

    public void setVisable(int visable) {
        this.visable = visable;
    }
}
