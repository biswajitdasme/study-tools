package com.example.studytools.activity.teacher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studytools.R;
import com.example.studytools.network.DataHandler;

public class TeacherAddSubjects extends AppCompatActivity {

    private Spinner spinnerSemesters;
    private EditText editTextSubject;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_add_subjects);

        spinnerSemesters = findViewById(R.id.spinner_semesters);
        editTextSubject = findViewById(R.id.editText_subject_title);
        button = findViewById(R.id.button_add_course);

        String semesters[] = {"Select Semester", "1st Semester", "2nd Semester", "3rd Semester",
                "4th Semester", "5th Semester", "6th Semester", "7th Semester", "8th Semester"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, semesters);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSemesters.setAdapter(adapter);

        spinnerSemesters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i != 0) {
                    editTextSubject.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                }
                else{
                    editTextSubject.setVisibility(View.INVISIBLE);
                    button.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void addSubject(View view) {

        long semesterId = spinnerSemesters.getSelectedItemId();
        if (semesterId == 0) {
            Toast.makeText(this, "Select Semester" + semesterId, Toast.LENGTH_SHORT).show();
        } else {
            String title = editTextSubject.getText().toString();

            DataHandler.storeCourse(this, (int) semesterId, title);

        }
    }
}
