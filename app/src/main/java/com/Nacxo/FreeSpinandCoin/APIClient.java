package com.Nacxo.FreeSpinandCoin;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient
{
    private static Retrofit retrofit = null;
    private static String BASE_URL="http://hashtagwebhub.com/";
    public static Retrofit getClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        return retrofit;
    }

}
