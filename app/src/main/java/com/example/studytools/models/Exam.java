package com.example.studytools.models;

public class Exam {
    private int id;
    private String title;
    private String fileName;
    private int duration;

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

    public Exam(int id, String title, String fileName, int duration) {
        this.id = id;
        this.title = title;
        this.fileName = fileName;
        this.duration = duration;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
