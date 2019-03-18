package com.example.eventsterapp.models;

public class ChildRow {
    private int icon;
    private String text;
    private Long id;
    private String type;

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public ChildRow(int icon, String text, Long id, String type) {
        this.icon = icon;
        this.text = text;
        this.id = id;
        this.type = type;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
