package com.xh.testnetworkwithretrofit.Services;

import com.xh.testnetworkwithretrofit.Entity.MovieEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Desc:
 * Company: xuehai
 * Copyright: Copyright (c) 2016
 *
 * @author hc
 * @version 1.0
 * @since 2017/5/23 0023
 */
public interface MovieService {
    @GET("top250")
    Call<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);

    @GET("top250")
    Observable<MovieEntity> getTopMovieWithRxJava(@Query("start") int start, @Query("count") int count);
}
