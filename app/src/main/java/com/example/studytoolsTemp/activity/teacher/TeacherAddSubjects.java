package com.example.studytoolsTemp.activity.teacher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studytoolsTemp.R;
import com.example.studytoolsTemp.network.DataHandler;

public class TeacherAddSubjects extends AppCompatActivity {

    private Spinner spinnerSemesters;
    private EditText editTextSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_add_subjects);

        spinnerSemesters = findViewById(R.id.spinner_semesters);
        editTextSubject = findViewById(R.id.editText_subject_title);


        String semesters[] = {"Select Semester", "1st Semester", "2nd Semester", "3rd Semester",
                "4th Semester", "5th Semester", "6th Semester", "7th Semester", "8th Semester"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, semesters);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSemesters.setAdapter(adapter);


    }

    public void addSubject(View view) {

        long semesterId = spinnerSemesters.getSelectedItemId();
        if (semesterId == 0) {
            Toast.makeText(this, "Select Semester" + semesterId, Toast.LENGTH_SHORT).show();
        } else {
            String title = editTextSubject.getText().toString();

            DataHandler.storeCourse(this, (int)semesterId, title);

        }
    }
}
