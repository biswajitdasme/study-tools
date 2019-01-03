package com.example.studytools.network;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.example.studytools.data.preference.AppPreference;
import com.example.studytools.interfaces.UserClient;
import com.example.studytools.models.FileInfo;
import com.example.studytools.models.ResponseModel;
import com.example.studytools.models.User;
import com.example.studytools.utils.FileUtils;
import com.google.gson.Gson;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FileUploader {
    public static void uploadFile(final Context context, Uri uri, final FileInfo info, final boolean isProfile) {

        Gson gson = new Gson();
        String imageData = gson.toJson(info);
        RequestBody descriptionPart = RequestBody.create(MultipartBody.FORM, imageData);


        File originalFile = FileUtils.getFile(context, uri);
        RequestBody filePart = RequestBody.create(
                MediaType.parse(context.getContentResolver().getType(uri)),
                originalFile
        );
        MultipartBody.Part file = MultipartBody.Part.createFormData("file", originalFile.getName(), filePart);


        Retrofit retrofit = RetrofitClient.getRetrofit();
        UserClient userClient = retrofit.create(UserClient.class);

        Call<ResponseModel> call = null;

        if (isProfile) {
            call = userClient.updateProfile(descriptionPart, file);
        } else {
            call = userClient.uploadFile(descriptionPart, file);
        }

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel serverResponse = response.body();

                String message = serverResponse.getMessage();

                if(!isProfile)
                showToast(context, message);
                else{
                    AppPreference.clearData(context);

                    AppPreference.setData(context, new User(String.valueOf(info.getId()),
                            info.getName(), info.getEmail(), info.getMobile(), info.getUsername(),
                            info.getPassword(),message));

                showToast(context,"Updated Successfully!");
                }



            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                showToast(context, t.getMessage());
            }
        });

    }

    private static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
