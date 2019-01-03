package com.example.studytools.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.studytools.data.constant.AppConstant.BASE_URL;

public class RetrofitClient {

    private static Gson gson = new GsonBuilder().setLenient().create();

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory
                            .create(gson));

            retrofit = builder.build();
        }
        return retrofit;
    }

}
