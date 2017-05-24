package com.xh.testnetworkwithretrofit.presenter.impl;

import android.widget.Toast;

import com.xh.testnetworkwithretrofit.entity.MovieEntity;
import com.xh.testnetworkwithretrofit.model.IMainModel;
import com.xh.testnetworkwithretrofit.model.impl.MainModel;
import com.xh.testnetworkwithretrofit.presenter.IMainPresenter;
import com.xh.testnetworkwithretrofit.ui.IMainView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;

/**
 * Desc:
 * Company: XueHai
 *
 * @author yuyejiang
 */
public class MainPresenter implements IMainPresenter {
    private IMainModel mModel;
    private IMainView mView;
    public MainPresenter(IMainView iMainView) {
        mModel = new MainModel();
        mView = iMainView;
    }

    @Override
    public void clickOnOriginalRetrofit() {
        mModel.getMovie(new Callback<MovieEntity>() {
            @Override
            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
                Toast.makeText(mView.getContext(), response.body().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MovieEntity> call, Throwable t) {
                Toast.makeText(mView.getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void clickOnRetrofitWithRxJava() {
        mModel.getMovieWithRxJava(new Subscriber<MovieEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mView.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(MovieEntity movieEntity) {
                Toast.makeText(mView.getContext(), movieEntity.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void clickOnRetrofitWithRxJavaIo() {
        mModel.getMovieWithRxJavaAndIo(new Subscriber<MovieEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mView.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(MovieEntity movieEntity) {
                Toast.makeText(mView.getContext(), movieEntity.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
