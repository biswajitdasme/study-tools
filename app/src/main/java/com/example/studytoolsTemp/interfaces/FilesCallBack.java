package com.example.studytoolsTemp.interfaces;

import com.example.studytoolsTemp.models.FileInfo;

import java.util.ArrayList;

public interface FilesCallBack {
    void onSuccess(ArrayList<FileInfo> examList);

    void onFail(String msg);
}
