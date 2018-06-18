package com.example.studytoolsTemp.utils;

import android.widget.EditText;

public class Validator {

    public static boolean validateEmail(EditText emailText) {

        boolean valid = true;

        String email = emailText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("Enter A Valid Email Address");
            valid = false;
        } else {
            emailText.setError(null);
        }

        return valid;
    }

    public static boolean validatePassword(EditText passwordText) {

        boolean valid = true;

        String password = passwordText.getText().toString();

        if (password.isEmpty() || password.length() < 4) {
            passwordText.setError("At Least 4 Alphanumeric Characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }

    public static boolean validateName(EditText nameText) {
        boolean valid = true;

        String name = nameText.getText().toString();

        if (name.isEmpty() || name.length() < 4) {
            nameText.setError("At Least 4 Characters");
            valid = false;
        } else {
            nameText.setError(null);
        }

        return valid;
    }


    public static boolean validateMobile(EditText mobileText) {
        boolean valid = true;

        String mobile = mobileText.getText().toString();

        if (mobile.isEmpty() || mobile.length() != 11) {
            mobileText.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            mobileText.setError(null);
        }

        return valid;
    }


}
