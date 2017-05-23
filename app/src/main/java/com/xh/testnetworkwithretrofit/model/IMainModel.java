package com.xh.testnetworkwithretrofit.model;

import com.xh.testnetworkwithretrofit.Entity.MovieEntity;

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
}
