package com.example.studytoolsTemp.interfaces;

import com.example.studytoolsTemp.models.Answer;

import java.util.ArrayList;

public interface AnswerCallBack {
    void onSuccess(ArrayList<Answer> examList);

    void onFail(String msg);
}
