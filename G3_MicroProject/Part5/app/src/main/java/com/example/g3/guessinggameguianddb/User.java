package com.example.g3.guessinggameguianddb;

public class User {
    private String userName;
    private String password;
    private int overallScore;
    private String userPicture;

    public User() { }

    public User(String userName, String password, int overallScore, String userPicture) {
        this.userName = userName;
        this.password = password;
        this.overallScore = overallScore;
        this.userPicture = userPicture;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getOverallscore() {
        return overallScore;
    }

    public void setOverallscore(int overallScore) {
        this.overallScore = overallScore;
    }

    public String getUserpicture() {
        return userPicture;
    }

    public void setUserpicture(String userPicture) {
        this.userPicture = userPicture;
    }

    @Override
    public String toString() {
        return userName + " - " + overallScore;
    }
}
