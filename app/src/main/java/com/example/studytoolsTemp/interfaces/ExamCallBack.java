package com.example.studytoolsTemp.interfaces;

import com.example.studytoolsTemp.models.Exam;

import java.util.ArrayList;

public interface ExamCallBack {
    void onSuccess(ArrayList<Exam> examList);

    void onFail(String msg);
}