package com.example.studytoolsTemp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.studytoolsTemp.R;
import com.example.studytoolsTemp.activity.student.StudentSignUpActivity;
import com.example.studytoolsTemp.network.DataHandler;
import com.example.studytoolsTemp.utils.Validator;

public class LoginActivity extends AppCompatActivity {

    private final String LOG_TAG = LoginActivity.class.getSimpleName();
    private static final int REQUEST_SIGNUP = 0;

    private EditText mEditTextUsername;
    private EditText mEditTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeViews();


    }

    public void goToSignupActivity(View view) {
        Intent intent = new Intent(this, StudentSignUpActivity.class);
        startActivityForResult(intent, REQUEST_SIGNUP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP && resultCode == RESULT_OK) {

            String username = data.getStringExtra("username");
            String password = data.getStringExtra("password");

            mEditTextUsername.setText(username);
            mEditTextPassword.setText(password);
        }
    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void initializeViews() {
        mEditTextUsername = findViewById(R.id.input_login_username);
        mEditTextPassword = findViewById(R.id.input_login_password);
    }


    public void login(View view) {

        if (Validator.validateName(mEditTextUsername) && Validator.validatePassword(mEditTextPassword)) {

            String username = mEditTextUsername.getText().toString();
            String password = mEditTextPassword.getText().toString();

            DataHandler.login(this, username, password);

        }
    }


}
