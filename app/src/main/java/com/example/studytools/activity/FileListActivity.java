package com.example.studytools.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.studytools.R;
import com.example.studytools.data.preference.AppPreference;
import com.example.studytools.models.FileInfo;
import com.example.studytools.network.DataHandler;

import java.util.ArrayList;

public class FileListActivity extends AppCompatActivity {
    private ArrayList<FileInfo> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);

        boolean forStudent = getIntent().getBooleanExtra("forStudent", false);
        boolean isQuestion = getIntent().getBooleanExtra("isQuestion", false);

        if (forStudent) {
            DataHandler.getFilesOfUser(this, null, isQuestion);
        } else {
            DataHandler.getFilesOfUser(this, AppPreference.getUserId(this), isQuestion);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.button_menu_logout:
                DataHandler.logout(this);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
