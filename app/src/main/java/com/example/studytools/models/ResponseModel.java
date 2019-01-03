package com.example.studytools.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ResponseModel implements Parcelable {
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;
    public final static Creator<ResponseModel> CREATOR = new Creator<ResponseModel>() {

        @SuppressWarnings({
                "unchecked"
        })
        public ResponseModel createFromParcel(Parcel in) {
            ResponseModel instance = new ResponseModel();
            instance.success = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.message = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public ResponseModel[] newArray(int size) {
            return (new ResponseModel[size]);
        }

    };


    public boolean isSuccess() {
        return success;
    }


    public String getMessage() {
        return message;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }
}
