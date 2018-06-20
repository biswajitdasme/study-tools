package com.example.studytoolsTemp.activity.teacher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.studytoolsTemp.R;
import com.example.studytoolsTemp.data.preference.AppPreference;
import com.example.studytoolsTemp.interfaces.CallBack;
import com.example.studytoolsTemp.models.FileInfo;
import com.example.studytoolsTemp.network.DataHandler;

import java.util.ArrayList;

public class TeacherAddAnswer extends AppCompatActivity {

    private Spinner mSpinnerQuestionlist;
    private Spinner mSpinnerQuestionNumber;
    private Spinner mSpinnerQuestionAnswer;

    private ArrayList<FileInfo> examList;

    private int questionId;
    private int questionNumber;
    private int questionAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_add_answer);

        initializeViews();

        DataHandler.getQuestionsOfUser(this, AppPreference.getUserId(this), new CallBack<FileInfo>() {
            @Override
            public void onSuccess(ArrayList<FileInfo> fileList) {
                examList = fileList;

                String[] questionNames = new String[fileList.size() + 1];

                questionNames[0] = "Select Exam";

                for (int i = 0; i < fileList.size(); i++) {
                    questionNames[i + 1] = fileList.get(i).getDescription();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TeacherAddAnswer.this, android.R.layout.simple_spinner_item, questionNames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                mSpinnerQuestionlist.setAdapter(adapter);

            }

            @Override
            public void onFail(String msg) {

            }
        });

        mSpinnerQuestionlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position != 0) {

                    questionId = examList.get(position - 1).getId();

                    int numberOfQuestions = examList.get(position - 1).getQuestions();

                    String[] questionList = new String[numberOfQuestions + 1];
                    questionList[0] = "Select Question";

                    for (int i = 0; i < numberOfQuestions; i++) {
                        questionList[i + 1] = "Question " + (i + 1);
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(TeacherAddAnswer.this, android.R.layout.simple_spinner_item, questionList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    mSpinnerQuestionNumber.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinnerQuestionNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                questionNumber = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] optionList = new String[]{"Select Answer", "Answer A", "Answer B", "Answer C"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(TeacherAddAnswer.this, android.R.layout.simple_spinner_item, optionList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerQuestionAnswer.setAdapter(adapter);

        mSpinnerQuestionAnswer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                questionAnswer = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initializeViews() {
        mSpinnerQuestionlist = findViewById(R.id.spinner_examListPdf);
        mSpinnerQuestionNumber = findViewById(R.id.spinner_question_number);
        mSpinnerQuestionAnswer = findViewById(R.id.spinner_question_answer);
    }

    public void setAnswer(View view) {
        DataHandler.storeAnswer(this, questionId, questionNumber, questionAnswer);
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
