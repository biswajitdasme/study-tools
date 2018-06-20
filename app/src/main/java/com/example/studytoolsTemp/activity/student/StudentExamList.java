package com.example.studytoolsTemp.activity.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.studytoolsTemp.R;
import com.example.studytoolsTemp.data.preference.AppPreference;
import com.example.studytoolsTemp.interfaces.CallBack;
import com.example.studytoolsTemp.models.Exam;
import com.example.studytoolsTemp.network.DataHandler;

import java.util.ArrayList;

import static com.example.studytoolsTemp.activity.student.StudentQuizActivity.EXTRA_EXAM_ID;
import static com.example.studytoolsTemp.activity.student.StudentQuizActivity.EXTRA_SCORE;

public class StudentExamList extends AppCompatActivity {
    public static final int REQUEST_QUIZ_CODE = 1;

    private ArrayList<Exam> examList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_exam_list);

        DataHandler.getExamList(this, new CallBack<Exam>() {
            @Override
            public void onSuccess(ArrayList<Exam> arrayList) {
                examList = arrayList;
            }

            @Override
            public void onFail(String msg) {

            }
        }, Integer.parseInt(AppPreference.getUserId(this)));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_QUIZ_CODE && resultCode == RESULT_OK) {
            int score = data.getIntExtra(EXTRA_SCORE, 0);
            int examid = data.getIntExtra(EXTRA_EXAM_ID, 0);
            int userid = Integer.parseInt(AppPreference.getUserId(StudentExamList.this));

            DataHandler.storeResult(this, userid, examid, score);

            finish();
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