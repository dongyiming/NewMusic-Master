package com.example.nc_basic_biz.http.builder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @version : 1.0
 * @Description : 获取retrofit对象
 * @autho : dongyiming
 * @data : 2017/7/14 14:55
 */
public class RetrofitBuilder {

    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit menuRetrofit;
    private Retrofit eyeRetrofit;
    private Retrofit newsRetrofit;
    private Retrofit tcRetrofit;
    private static RetrofitBuilder instance;
    private final OkHttpClient.Builder httpclentBuilder;

    private RetrofitBuilder() {

        httpclentBuilder = new OkHttpClient.Builder();
        httpclentBuilder.sslSocketFactory(TrustAllCerts.createSSLSocketFactory());
        httpclentBuilder.hostnameVerifier(new TrustAllCerts.TrustAllHostnameVerifier());
        httpclentBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    }

    public static RetrofitBuilder getInstance() {

        if (instance == null) {
            synchronized (RetrofitBuilder.class) {
                if (instance == null) {
                    instance = new RetrofitBuilder();
                }
            }
        }
        return instance;
    }

    public Retrofit getMenuRetrofit(String BASE_URL) {

        if (menuRetrofit == null) {
            menuRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(httpclentBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return menuRetrofit;
    }

    public Retrofit getEyeRetrofit(String BASE_URL) {

        if (eyeRetrofit == null) {
            eyeRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(httpclentBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return eyeRetrofit;
    }

    public Retrofit getNewsRetrofit(String BASE_URL) {

        if (newsRetrofit == null) {
            newsRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(httpclentBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return newsRetrofit;
    }

    public Retrofit getTcRetrofit(String BASE_URL) {

        if (tcRetrofit == null) {
            tcRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(httpclentBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return tcRetrofit;
    }
}
