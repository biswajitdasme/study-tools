package com.example.studytoolsTemp.interfaces;

import com.example.studytoolsTemp.models.Question;

import java.util.ArrayList;

public interface QuestionCallBack {
    void onSuccess(ArrayList<Question> quesList);

    void onFail(String msg);
}