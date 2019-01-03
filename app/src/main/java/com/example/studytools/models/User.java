package com.example.studytools.models;

public class User {
    private String id;
    private String name;
    private String email;
    private String mobile;
    private String username;
    private String password;
    private String image;
    private int type;

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(String id, String name, String email, String mobile, String username, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.username = username;
        this.password = password;
    }

    public User(String id, String name, String email, String mobile, String username, String password, String image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.username = username;
        this.password = password;
        this.image = image;
    }

    public User(String name, String email, String mobile, String username, String password) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.username = username;
        this.password = password;
    }

    public User(String name, String email, String mobile, String username, String password, int type) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
