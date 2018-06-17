package com.example.studytoolsTemp.activity.teacher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studytoolsTemp.R;
import com.example.studytoolsTemp.network.DataHandler;

public class TeacherAddExamActivity extends AppCompatActivity {

    private EditText editTextExamTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_add_exam);

        editTextExamTitle = findViewById(R.id.editText_exam_title);
    }

    public void addExam(View view) {
        String examTitle = editTextExamTitle.getText().toString();

        if (TextUtils.isEmpty(examTitle)) {
            Toast.makeText(this, "Exam title field is empty", Toast.LENGTH_SHORT).show();
        } else {
            DataHandler.addExamTitle(this,examTitle);
            editTextExamTitle.setText("");
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
