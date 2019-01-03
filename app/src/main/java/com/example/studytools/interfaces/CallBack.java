package com.example.studytools.interfaces;

import java.util.ArrayList;

public interface CallBack<T> {
    void onSuccess(ArrayList<T> list);

    void onFail(String msg);
}
