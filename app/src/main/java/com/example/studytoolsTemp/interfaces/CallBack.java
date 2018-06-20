package com.example.studytoolsTemp.interfaces;

import java.util.ArrayList;

public interface CallBack<T> {
    void onSuccess(ArrayList<T> list);

    void onFail(String msg);
}
