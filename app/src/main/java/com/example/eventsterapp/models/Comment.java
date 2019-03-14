package com.example.eventsterapp.models;

import com.example.eventsterapp.*;
import java.util.List;

public class Comment {
    // Declare that this attribute is the id

    private Long id;

    private int eventID;
    private String text;
    private String userID;

    public Comment(int eventID, String text, String userID) {
        this.eventID = eventID;
        this.text = text;
        this.userID = userID;
    }

    public Long getCommentID() {
        return this.id;
    }

    public int getEventID() {
        return this.eventID;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public String getUserID() {
        return this.userID;
    }
}
