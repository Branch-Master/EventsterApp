package com.example.eventsterapp.models;

import com.example.eventsterapp.*;
import java.util.List;

public class Comment {
    // Declare that this attribute is the id

    private Long id;

    private Long eventID;
    private String text;
    private Long userID;

    public Comment(Long id, Long eventID, String text, Long userID) {
        this.id = id;
        this.eventID = eventID;
        this.text = text;
        this.userID = userID;
    }

    public Long getCommentID() {
        return this.id;
    }

    public Long getEventID() {
        return this.eventID;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public Long getUserID() {
        return this.userID;
    }
}
