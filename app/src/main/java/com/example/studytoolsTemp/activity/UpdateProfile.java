package com.example.studytoolsTemp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.studytoolsTemp.R;
import com.example.studytoolsTemp.data.preference.AppPreference;
import com.example.studytoolsTemp.models.User;
import com.example.studytoolsTemp.network.DataHandler;

public class UpdateProfile extends AppCompatActivity {

    private EditText mEditTextName;
    private EditText mEditTextEmail;
    private EditText mEditTextMobile;
    private EditText mEditTextUserName;
    private EditText mEditTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        initializeViews();

        initializeData();

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


    private void initializeData() {
        User user = AppPreference.getData(this);
        mEditTextName.setText(user.getName());
        mEditTextEmail.setText(user.getEmail());
        mEditTextMobile.setText(user.getMobile());
        mEditTextUserName.setText(user.getUsername());
        mEditTextPassword.setText(user.getPassword());
    }

    private void initializeViews() {
        mEditTextName = findViewById(R.id.input_update_name);
        mEditTextEmail = findViewById(R.id.input_update_email);
        mEditTextMobile = findViewById(R.id.input_update_mobile);
        mEditTextUserName = findViewById(R.id.input_update_username);
        mEditTextPassword = findViewById(R.id.input_update_password);
    }

    public void update(View view) {
        String name = mEditTextName.getText().toString();
        String email = mEditTextEmail.getText().toString();
        String mobile = mEditTextMobile.getText().toString();
        String username = mEditTextUserName.getText().toString();
        String password = mEditTextPassword.getText().toString();

        DataHandler.update(this, new User(name, email, mobile, username, password));
    }
}
