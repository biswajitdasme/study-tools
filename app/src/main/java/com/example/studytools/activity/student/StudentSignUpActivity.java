package com.example.studytools.activity.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.studytools.R;
import com.example.studytools.activity.LoginActivity;
import com.example.studytools.models.User;
import com.example.studytools.network.DataHandler;
import com.example.studytools.utils.Validator;

public class StudentSignUpActivity extends AppCompatActivity {

    private EditText mEditTextName;
    private EditText mEditTextEmail;
    private EditText mEditTextMobile;
    private EditText mEditTextUsername;
    private EditText mEditTextPassword;
    private Spinner mSpinnerType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup);

        initializeViews();
    }

    public void goToLoginActivity(View view) {
        finish();
    }

    private void initializeViews() {
        mEditTextName = findViewById(R.id.input_signUp_name);
        mEditTextEmail = findViewById(R.id.input_signUp_email);
        mEditTextMobile = findViewById(R.id.input_signUp_mobile);
        mEditTextUsername = findViewById(R.id.input_signUp_username);
        mEditTextPassword = findViewById(R.id.input_signUp_password);
        mSpinnerType = findViewById(R.id.spinner_user_type);


        String type[] = {"Student", "Teacher"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,type);

        mSpinnerType.setAdapter(arrayAdapter);
        mSpinnerType.setSelection(0,true);


    }

    public void signUp(View view) {
        if (Validator.validateName(mEditTextName) && Validator.validateEmail(mEditTextEmail) &&
                Validator.validateMobile(mEditTextMobile) &&
                Validator.validateName(mEditTextUsername) &&
                Validator.validatePassword(mEditTextPassword)) {

            String name = mEditTextName.getText().toString();
            String email = mEditTextEmail.getText().toString();
            String mobile = mEditTextMobile.getText().toString();
            String username = mEditTextUsername.getText().toString();
            String password = mEditTextPassword.getText().toString();
            long type = mSpinnerType.getSelectedItemId();

            DataHandler.signup(this, new User(name, email, mobile, username, password, (int)type+1));

            Intent intent = new Intent(this, LoginActivity.class);

            intent.putExtra("username", username);
            intent.putExtra("password", password);

            setResult(RESULT_OK, intent);

            finish();
        }
    }


}
