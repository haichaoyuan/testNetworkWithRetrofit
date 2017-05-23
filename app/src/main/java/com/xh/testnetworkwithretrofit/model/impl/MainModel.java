package com.xh.testnetworkwithretrofit.model.impl;

import com.xh.testnetworkwithretrofit.Entity.MovieEntity;
import com.xh.testnetworkwithretrofit.Services.MovieService;
import com.xh.testnetworkwithretrofit.model.IMainModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Desc:
 * Company: XueHai
 *
 * @author yuyejiang
 */
public class MainModel implements IMainModel {
    String baseUrl = "https://api.douban.com/v2/movie/";

    /**
     * 获取电影
     */
    @Override
    public void getMovie(Callback<MovieEntity> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);
        Call<MovieEntity> call = movieService.getTopMovie(0, 10);
        call.enqueue(callback);
    }

    /**
     * 结合RxJava获取电影
     */
    @Override
    public void getMovieWithRxJava(Subscriber<MovieEntity> subscriber) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);

        Observable<MovieEntity> observable = movieService.getTopMovieWithRxJava(0, 10);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
