package com.xh.testnetworkwithretrofit.model;

import com.xh.testnetworkwithretrofit.entity.MovieEntity;

import retrofit2.Callback;
import rx.Subscriber;

/**
 * Desc:
 * Company: XueHai
 *
 * @author yuyejiang
 */
public interface IMainModel {

    void getMovie(Callback<MovieEntity> callback);

    void getMovieWithRxJava(Subscriber<MovieEntity> callback);

    void getMovieWithRxJavaAndIo(Subscriber<MovieEntity> subscriber);
}
