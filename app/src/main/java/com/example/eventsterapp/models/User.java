package com.example.eventsterapp.models;

public class User {
    private Long id;
    private String username;
    private String password;
    private String birthday;
    private String phone;
    private String zodiac;
    private String email;
    private boolean loggedIn;

    public User(){}

    public User(Long id, String username, String password, String birthday, String phone, String zodiac, String email, boolean loggedIn) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.phone = phone;
        this.zodiac = zodiac;
        this.email = email;
        this.loggedIn = loggedIn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
