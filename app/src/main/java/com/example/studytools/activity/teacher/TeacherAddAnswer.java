package com.example.studytools.activity.teacher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.studytools.R;
import com.example.studytools.data.preference.AppPreference;
import com.example.studytools.interfaces.CallBack;
import com.example.studytools.models.Answer;
import com.example.studytools.models.FileInfo;
import com.example.studytools.network.DataHandler;

import java.util.ArrayList;

public class TeacherAddAnswer extends AppCompatActivity {

    private Spinner mSpinnerQuestionList;
    private Spinner mSpinnerQuestionNumber;
    private Spinner mSpinnerQuestionAnswer;
    private ListView mListViewAnswerList;

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

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TeacherAddAnswer.this,
                        android.R.layout.simple_spinner_item, questionNames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                mSpinnerQuestionList.setAdapter(adapter);

            }

            @Override
            public void onFail(String msg) {

            }
        });

        mSpinnerQuestionList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(TeacherAddAnswer.this,
                            android.R.layout.simple_spinner_item, questionList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    mSpinnerQuestionNumber.setAdapter(adapter);


                    DataHandler.getAnswerList(TeacherAddAnswer.this, new CallBack<Answer>() {



                        @Override
                        public void onSuccess(ArrayList<Answer> answerArrayList) {
                            ArrayList<Answer> answerList = answerArrayList;
                            int questionCountTotal = answerList.size();
                            String answerListText[] = new String[questionCountTotal];

                            char answerChars[] = new char[26];

                            for(int i = 0;  i<26; i++){
                                answerChars[i]= (char)(65+i);
                            }

                            for (int i = 0; i < questionCountTotal; i++) {
                                answerListText[i] = "Question : " + answerList.get(i).getQuestionNum() +
                                        " Answer : " + answerChars[answerArrayList.get(i).getAnswer()-1];
                            }

                            ArrayAdapter<String> answerAdapter = new ArrayAdapter<>(TeacherAddAnswer.this, android.R.layout.simple_list_item_1, answerListText);

                            mListViewAnswerList.setAdapter(answerAdapter);

                        }

                        @Override
                        public void onFail(String msg) {

                        }
                    }, questionId);

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
        mSpinnerQuestionList = findViewById(R.id.spinner_examListPdf);
        mSpinnerQuestionNumber = findViewById(R.id.spinner_question_number);
        mSpinnerQuestionAnswer = findViewById(R.id.spinner_question_answer);
        mListViewAnswerList = findViewById(R.id.listView_answerList);
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
