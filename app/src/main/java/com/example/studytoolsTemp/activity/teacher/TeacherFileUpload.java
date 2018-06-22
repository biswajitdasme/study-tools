package com.example.studytoolsTemp.activity.teacher;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studytoolsTemp.R;
import com.example.studytoolsTemp.data.preference.AppPreference;
import com.example.studytoolsTemp.interfaces.CallBack;
import com.example.studytoolsTemp.models.Course;
import com.example.studytoolsTemp.models.Exam;
import com.example.studytoolsTemp.models.FileInfo;
import com.example.studytoolsTemp.network.DataHandler;
import com.example.studytoolsTemp.network.FileUploader;
import com.example.studytoolsTemp.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;

public class TeacherFileUpload extends AppCompatActivity {

    private static final String TAG = TeacherFileUpload.class.getSimpleName();

    private static final int MY_PERMISSION_REQUEST = 100;
    private static final int PICK_FILE_REQUEST = 1;

    private static Uri mUri;

    private EditText mEditText;
    private EditText mEditTextNumberOfQuestions;
    private EditText mEditTextExamDuration;
    private TextView mTextView;

    private Spinner mSpinner;
    private Spinner mSpinnerSemesterList;
    private Spinner mSpinnerCourseList;

    private int mFileType;
    private int courseId=0;
    private int mNumberOfQuestions = 0;
    private int mExamDuration = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_file_upload);

        checkFilePermission();

        initializeViews();

        initializeSpinner();

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mUri = data.getData();

            File file = FileUtils.getFile(this, mUri);

            mTextView.setText(file.getName());

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSION_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showToast("Thanks For Granting Permission");
                } else {
                    showToast("Sorry, we need permission");
                    finish();
                }
        }
    }

    private void initializeViews() {
        mEditText = findViewById(R.id.editText_description);
        mTextView = findViewById(R.id.textView_fileDescription);
        mSpinner = findViewById(R.id.spinner_upload);
        mEditTextNumberOfQuestions = findViewById(R.id.editText_number_of_questions);
        mEditTextExamDuration = findViewById(R.id.editText_exam_duration);

        mSpinnerSemesterList = findViewById(R.id.spinner_semester_list);
        mSpinnerCourseList = findViewById(R.id.spinner_course_list);
    }

    private void initializeSpinner() {
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.array_fileType, android.R.layout.simple_spinner_item);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner.setAdapter(arrayAdapter);
        mSpinner.setSelection(0);


        String semesters[] = {"Select Semester", "1st Semester", "2nd Semester", "3rd Semester",
                "4th Semester", "5th Semester", "6th Semester", "7th Semester", "8th Semester"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(TeacherFileUpload.this, android.R.layout.simple_spinner_item, semesters);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerSemesterList.setAdapter(adapter);

        mSpinnerSemesterList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position!=0){
                    DataHandler.getCourseList(TeacherFileUpload.this, new CallBack<Course>() {
                        @Override
                        public void onSuccess(final ArrayList<Course> list) {

                            final ArrayList<String> courseList = new ArrayList<>();
                            courseList.add("Select Course");
                            for (Course course : list) {
                                courseList.add(course.getCourse());
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(TeacherFileUpload.this, android.R.layout.simple_spinner_item, courseList);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            mSpinnerCourseList.setAdapter(adapter);

                            mSpinnerCourseList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                    if(position!=0)
                                        courseId = list.get(position-1).getId();

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                        }

                        @Override
                        public void onFail(String msg) {

                        }
                    }, position);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        if (mSpinner != null) {
            mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    mFileType = position;

                    if (mFileType == 2) {
                        mEditTextNumberOfQuestions.setVisibility(View.VISIBLE);
                        mEditTextExamDuration.setVisibility(View.VISIBLE);

                    } else {
                        mEditTextNumberOfQuestions.setVisibility(View.GONE);
                        mNumberOfQuestions = 0;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    mFileType = 0;
                }
            });
        }
    }

    private void checkFilePermission() {
        if (ContextCompat.checkSelfPermission(TeacherFileUpload.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(TeacherFileUpload.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
        }

    }

    public void chooseFile(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

        intent.setType(FileUtils.MIME_TYPE_APP);
        startActivityForResult(intent, PICK_FILE_REQUEST);
    }


    public void uploadFile(View view) {
        String description = mEditText.getText().toString();

        if (mFileType == 2) {
            mNumberOfQuestions = Integer.parseInt(mEditTextNumberOfQuestions.getText().toString());
            mExamDuration = Integer.parseInt(mEditTextExamDuration.getText().toString());
        }

        if (TextUtils.isEmpty(description)) {
            showToast("Enter File Name");
        } else if (mFileType == 0) {
            showToast("Select File Type");
        } else if (mUri == null || mUri.equals(Uri.EMPTY)) {
            showToast("Select a File");
        } else if (mFileType == 2 && courseId == 0) {
            showToast("Select a course");
        } else {
            FileUploader.uploadFile(this, mUri, new FileInfo(description,
                    Integer.parseInt(AppPreference.getUserId(this)), mFileType, mNumberOfQuestions, courseId, mExamDuration));

//            Toast.makeText(this, "" + mNumberOfQuestions + " " + courseId, Toast.LENGTH_SHORT).show();
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
