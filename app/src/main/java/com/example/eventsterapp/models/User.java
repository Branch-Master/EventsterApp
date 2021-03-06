package com.example.eventsterapp.models;

public class User {
    private int id;
    private String username;
    private String password;
    private String birthday;
    private String phone;
    private String zodiac;
    private String email;
    private String info;
    private boolean loggedIn;

    public User(){}

    public User(String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.birthday = null;
        this.phone = null;
        this.zodiac = null;
        this.email = email;
        this.info = null;
        this.loggedIn = false;
    }

    public User(int id,String username, String password, String email, String bday, String phone, String zodiac, String info) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.birthday = bday;
        this.phone = phone;
        this.zodiac = zodiac;
        this.email = email;
        this.info = info;
        this.loggedIn = false;
    }



    public int getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZodiac() {
        return zodiac;
    }

    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
