package com.example.studytools.models;

public class ExamResult {
    private String name;
    private String examTitle;
    private int result;

    public ExamResult() {
    }

    public ExamResult(String name, String examTitle, int result) {
        this.name = name;
        this.examTitle = examTitle;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
