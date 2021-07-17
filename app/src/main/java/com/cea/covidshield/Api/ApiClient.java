package com.cea.covidshield.Api;

import android.content.Context;

import com.chuckerteam.chucker.api.ChuckerInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static ApiClient instance;
    private static Context mContext;

    private String BASE_URL_COWIN = "https://cdn-api.co-vin.in/api/";
    private String BASE_URL_COUNTRY = "https://corona.lmao.ninja/";
    private String BASE_URL_VACCINE = "https://www.mygov.in/sites/default/files/covid/";


    public static ApiClient getInstance(Context context){
        if (instance == null ){
            instance = new ApiClient();
        }
        mContext = context;
        return instance;
    }

    public static ApiClient getInstance(){
        return instance;
    }

    private Retrofit getRetrofit_Cowin(){

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        ChuckerInterceptor chuckerInterceptor = new ChuckerInterceptor(mContext);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new ChuckerInterceptor(mContext))
                .addNetworkInterceptor(chain ->
                        chain.proceed(
                                chain.request()
                                .newBuilder()
                                .header("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
                                .build()
                        ))
                .build();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_COWIN)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    private Retrofit getRetrofit_Country(){

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new ChuckerInterceptor(mContext))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_COUNTRY)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    private Retrofit getRetrofit_Vaccine(){

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new ChuckerInterceptor(mContext))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_VACCINE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    public  ApiService getService_Cowin() {
        return getRetrofit_Cowin().create(ApiService.class);
    }

    public  ApiService getService_Country() {
        return getRetrofit_Country().create(ApiService.class);
    }
    public  ApiService getService_Vaccine() {
        return getRetrofit_Vaccine().create(ApiService.class);
    }

}

