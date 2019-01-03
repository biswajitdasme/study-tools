package com.example.studytools.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.studytools.R;
import com.example.studytools.adapters.UserListAdapter;
import com.example.studytools.interfaces.CallBack;
import com.example.studytools.models.User;
import com.example.studytools.network.DataHandler;

import java.util.ArrayList;

public class AdminProfileActivity extends AppCompatActivity {

    private ListView userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        userList = findViewById(R.id.listVIew_userList);

        DataHandler.getUserList(this, new CallBack<User>() {
            @Override
            public void onSuccess(ArrayList<User> list) {

                UserListAdapter adapter = new UserListAdapter(AdminProfileActivity.this,list);

                userList.setAdapter(adapter);

            }

            @Override
            public void onFail(String msg) {

            }
        });



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
