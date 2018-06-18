package com.example.studytoolsTemp.models;

public class Exam {
    private int id;
    private String title;
    private String fileName;
    private String fileDescription;

    public Exam() {

    }

    public Exam(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Exam(int id, String title, String fileName) {
        this.id = id;
        this.title = title;
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
