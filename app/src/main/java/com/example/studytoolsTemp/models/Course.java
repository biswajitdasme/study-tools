package com.example.studytoolsTemp.models;

public class Course {
    private int id;
    private int semester;
    private String course;

    public Course(int id, int semester, String course) {
        this.id = id;
        this.semester = semester;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
