package com.example.studytools.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.studytools.R;
import com.example.studytools.data.preference.AppPreference;
import com.example.studytools.models.FileInfo;
import com.example.studytools.models.User;
import com.example.studytools.network.DataHandler;
import com.example.studytools.network.FileUploader;
import com.example.studytools.utils.FileUtils;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.studytools.data.constant.AppConstant.BASE_URL;

public class UpdateProfile extends AppCompatActivity {

    private EditText mEditTextName;
    private EditText mEditTextEmail;
    private EditText mEditTextMobile;
    private EditText mEditTextUserName;
    private EditText mEditTextPassword;
    private CircleImageView circleImageView;

    private static final int MY_PERMISSION_REQUEST = 100;
    private static final int PICK_FILE_REQUEST = 1;

    private static Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        checkFilePermission();

        initializeViews();
        initializeData();

    }


    private void checkFilePermission() {
        if (ContextCompat.checkSelfPermission(UpdateProfile.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(UpdateProfile.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
        }

    }

    public void chooseImage(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

        intent.setType(FileUtils.MIME_TYPE_IMAGE);
        startActivityForResult(intent, PICK_FILE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mUri = data.getData();

            File file = FileUtils.getFile(this, mUri);

            showToast(file.getName() + " Selected !");

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

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
        Glide.with(this).load(BASE_URL + "file/" + user.getImage()).into(circleImageView);

    }

    private void initializeViews() {
        mEditTextName = findViewById(R.id.input_update_name);
        mEditTextEmail = findViewById(R.id.input_update_email);
        mEditTextMobile = findViewById(R.id.input_update_mobile);
        mEditTextUserName = findViewById(R.id.input_update_username);
        mEditTextPassword = findViewById(R.id.input_update_password);
        circleImageView = findViewById(R.id.profile_image);
    }

    public void update(View view) {
        String name = mEditTextName.getText().toString();
        String email = mEditTextEmail.getText().toString();
        String mobile = mEditTextMobile.getText().toString();
        String username = mEditTextUserName.getText().toString();
        String password = mEditTextPassword.getText().toString();


        if (mUri == null || mUri.equals(Uri.EMPTY)) {
            showToast("Select a File");
        } else {
            FileUploader.uploadFile(this, mUri, new FileInfo(
                    Integer.parseInt(AppPreference.getUserId(this)),
                    name, email, mobile, username, password), true);
        }

    }

}
