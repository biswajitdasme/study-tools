package com.example.studytoolsTemp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.studytoolsTemp.R;
import com.example.studytoolsTemp.interfaces.CallBack;
import com.example.studytoolsTemp.models.Exam;
import com.example.studytoolsTemp.network.DataHandler;

import java.util.ArrayList;

public class ExamResultActivity extends AppCompatActivity {

    private Spinner spinnerResultExamList;
    private ArrayList<Exam> examList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_exam_result);

        spinnerResultExamList = findViewById(R.id.spinner_result_examList);

        boolean forTeacher = getIntent().getBooleanExtra("forTeacher", false);

        if (forTeacher) {
            DataHandler.getExamList(this, new CallBack<Exam>() {
                @Override
                public void onSuccess(ArrayList<Exam> arrayList) {
                    examList = arrayList;

                    String examTitles[] = new String[examList.size() + 1];
                    examTitles[0] = "Select Exam";

                    for (int i = 0; i < examList.size(); i++) {
                        examTitles[i + 1] = examList.get(i).getTitle();
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExamResultActivity.this, android.R.layout.simple_spinner_item, examTitles);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerResultExamList.setAdapter(adapter);

                    spinnerResultExamList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            if (position != 0) {
                                int examid = examList.get(position - 1).getId();
                                DataHandler.getResultList(ExamResultActivity.this, -1, examid);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                @Override
                public void onFail(String msg) {
                    // Do Stuff
                }
            }, -1,-1);
        } else {
            String userid = getIntent().getStringExtra("userid");

            spinnerResultExamList.setVisibility(View.GONE);

            DataHandler.getResultList(this, Integer.parseInt(userid), -1);
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
