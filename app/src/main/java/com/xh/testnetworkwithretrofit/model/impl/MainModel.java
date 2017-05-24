package com.xh.testnetworkwithretrofit.model.impl;

import com.xh.testnetworkwithretrofit.entity.MovieEntity;
import com.xh.testnetworkwithretrofit.model.IMainModel;
import com.xh.testnetworkwithretrofit.services.MovieService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
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
     * 1. Retrofit方式访问数据
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

    /**
     * 结合RxJava获取电影的基础上增加数据操作doOnIo方法
     */
    @Override
    public void getMovieWithRxJavaAndIo(Subscriber<MovieEntity> subscriber) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);

        Observable<MovieEntity> observable = movieService.getTopMovieWithRxJava(0, 10);

        observable.doOnNext(new Action1<MovieEntity>() {
                    @Override
                    public void call(MovieEntity movieEntity) {
                        doOnIo(movieEntity);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    private void doOnIo(MovieEntity movieEntity) {
        // do something db
    }
}
