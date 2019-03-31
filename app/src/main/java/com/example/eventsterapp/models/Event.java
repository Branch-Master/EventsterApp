package com.example.eventsterapp.models;

import com.example.eventsterapp.*;
import java.util.List;
import java.util.ArrayList;

import java.util.Date;


public class Event {
    // Declare that this attribute is the id

    private Long id;

    private String eventName;
    private String eventInfo;
    private int groupID;
    private ArrayList<Comment> comments;
    private String tags;
    private String startDate;
    private String endDate;
    private ArrayList<User> going;
    private String location;
    private int eventSeats;
    private int visable;

    public Event() {
    }

    public Event(Long id, String eventName, String eventInfo, int groupID, String tag, String startDate, String endDate, String location, int eventSeats,int vis) {
        this.id = id;
        this.eventName = eventName;
        this.eventInfo = eventInfo;
        this.groupID = groupID;
        this.tags = tag;
        this.startDate = startDate;
        this.endDate = endDate;
        going = new ArrayList<>();
        comments = new ArrayList<>();
        this.location = location;
        this.eventSeats = eventSeats;
        this.visable = vis;
    }

    public void setComments(ArrayList<Comment> comments){this.comments = comments;}

    public void setGoing(ArrayList<User> going){this.going = going;}

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void deleteComment(Comment comment) {
        this.comments.remove(comment);
    }

    public Long getId() {
        return this.id;
    }

    public void setEventName(String name) {
        this.eventName = name;
    }

    public String getEventName() {
        return this.eventName;
    }

    public void setEventInfo(String info) {
        this.eventInfo = info;
    }

    public String getEventInfo() {
        return this.eventInfo;
    }

    public String getTag() {
        return this.tags;
    }

    public void addTag(String tag) {
        this.tags = tag;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public int getGroupID() {
        return this.groupID;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public ArrayList<User> getGoing(){
        return this.going;
    }

    public void addToGoing(User user) {
        this.going.add(user);
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

    public void setEventSeats(int eventSeats) {
        this.eventSeats = eventSeats;
    }

    public int getEventSeats() {
        return this.eventSeats;
    }

    public int getVisable() {
        return this.visable;
    }

    public void setVisable(int vis) {
        this.visable = vis;
    }

}
