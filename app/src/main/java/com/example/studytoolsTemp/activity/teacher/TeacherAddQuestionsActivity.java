package com.example.studytoolsTemp.activity.teacher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studytoolsTemp.R;
import com.example.studytoolsTemp.interfaces.ExamCallBack;
import com.example.studytoolsTemp.models.Exam;
import com.example.studytoolsTemp.models.Question;
import com.example.studytoolsTemp.network.DataHandler;

import java.util.ArrayList;

public class TeacherAddQuestionsActivity extends AppCompatActivity {

    private Spinner spinnerQuestionList;
    private EditText editTextQuestion;
    private EditText editTextOption1;
    private EditText editTextOption2;
    private EditText editTextOption3;
    private Spinner spinnerAnswer;

    private ArrayList<Exam> examList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_add_questions);

        initializeViews();

        DataHandler.getExamList(this, new ExamCallBack() {
            @Override
            public void onSuccess(ArrayList<Exam> arrayList) {
                examList = arrayList;
                String examTitles[] = new String[examList.size() + 1];
                examTitles[0] = "Select Exam";

                for (int i = 0; i < examList.size(); i++) {
                    examTitles[i + 1] = examList.get(i).getTitle();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TeacherAddQuestionsActivity.this, android.R.layout.simple_spinner_item, examTitles);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerQuestionList.setAdapter(adapter);

            }

            @Override
            public void onFail(String msg) {
                // Do Stuff
            }
        },-1);

        String options[] = new String[]{"Select Answer", "Option 1", "Option 2", "Option 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(TeacherAddQuestionsActivity.this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAnswer.setAdapter(adapter);
    }

    private void initializeViews() {
        spinnerQuestionList = findViewById(R.id.spinner_examList);
        editTextQuestion = findViewById(R.id.editText_question);
        editTextOption1 = findViewById(R.id.editText_option1);
        editTextOption2 = findViewById(R.id.editText_option2);
        editTextOption3 = findViewById(R.id.editText_option3);
        spinnerAnswer = findViewById(R.id.spinner_answer);
    }


    public void addQuestion(View view) {
        int quesId = (int) spinnerQuestionList.getSelectedItemId();
        String question = editTextQuestion.getText().toString();
        String option1 = editTextOption1.getText().toString();
        String option2 = editTextOption2.getText().toString();
        String option3 = editTextOption3.getText().toString();
        int answerId = (int) spinnerAnswer.getSelectedItemId();

        if (quesId == 0) {
            Toast.makeText(this, "Select a exam", Toast.LENGTH_SHORT).show();
        } else if (answerId == 0) {
            Toast.makeText(this, "Select a answer", Toast.LENGTH_SHORT).show();
        } else {
            quesId = examList.get(quesId-1).getId();
            Question q = new Question(quesId, question, option1, option2, option3, answerId);
            DataHandler.addQuestion(this,q);
        }
    }
}
