package com.example.studytools.interfaces;

import com.example.studytools.models.ResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserClient {

    @Multipart
    @POST("save_file.php")
    Call<ResponseModel> uploadFile(
            @Part("file_description") RequestBody description,
            @Part MultipartBody.Part file);

    @Multipart
    @POST("update_profile.php")
    Call<ResponseModel> updateProfile(
            @Part("file_description") RequestBody description,
            @Part MultipartBody.Part file);

}
