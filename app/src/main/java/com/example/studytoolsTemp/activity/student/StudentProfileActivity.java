package com.example.studytoolsTemp.activity.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.studytoolsTemp.R;
import com.example.studytoolsTemp.activity.FileListActivity;
import com.example.studytoolsTemp.activity.UpdateProfile;
import com.example.studytoolsTemp.activity.ExamResultActivity;
import com.example.studytoolsTemp.data.preference.AppPreference;
import com.example.studytoolsTemp.network.DataHandler;

public class StudentProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
    }

    @Override
    public void onBackPressed() {
        //   moveTaskToBack(true);
        DataHandler.logout(this);
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

    public void goToUpdateProfile(View view) {
        Intent intent = new Intent(this, UpdateProfile.class);
        startActivity(intent);
    }

    public void goToDocumentList(View view) {
        Intent intent = new Intent(this, FileListActivity.class);
        intent.putExtra("forStudent", true);
        intent.putExtra("isQuestion", false);
        startActivity(intent);
    }

    public void goToExamList(View view) {
        Intent intent = new Intent(this, StudentExamList.class);
        startActivity(intent);
    }

    public void goToCheckResult(View view) {
        Intent intent = new Intent(this, ExamResultActivity.class);
        intent.putExtra("forTeacher", false);
        intent.putExtra("userid", AppPreference.getUserId(StudentProfileActivity.this));
        startActivity(intent);
    }


/*    public void goToQuestionList(View view) {
        Intent intent = new Intent(this, FileListActivity.class);
        intent.putExtra("forStudent",true);
        intent.putExtra("isQuestion",true);
        startActivity(intent);
    }*/
}
