package com.xh.testnetworkwithretrofit.ui.impl;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xh.testnetworkwithretrofit.R;
import com.xh.testnetworkwithretrofit.presenter.IMainPresenter;
import com.xh.testnetworkwithretrofit.presenter.impl.MainPresenter;
import com.xh.testnetworkwithretrofit.ui.IMainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IMainView {

    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.btn_original_retrofit)
    Button mBtnOriginalRetrofit;
    @BindView(R.id.btn_retrofit_with_rxJava)
    Button mBtnRetrofitWithRxJava;
    @BindView(R.id.btn_retrofit_with_rxJava_io)
    Button mBtnRetrofitWithRxJavaIo;
    @BindView(R.id.activity_main)
    RelativeLayout mActivityMain;

    private IMainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenter(this);
    }

    @OnClick({R.id.btn_original_retrofit, R.id.btn_retrofit_with_rxJava, R.id.btn_retrofit_with_rxJava_io})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_original_retrofit://原生retrofit
                clickOnOriginalRetrofit();
                break;
            case R.id.btn_retrofit_with_rxJava://原生retrofit和rxJava混合
                mPresenter.clickOnRetrofitWithRxJava();
                break;
            case R.id.btn_retrofit_with_rxJava_io:
                mPresenter.clickOnRetrofitWithRxJavaIo();
                break;
        }
    }

    /**
     * 原生retrofit
     */
    private void clickOnOriginalRetrofit() {
        mPresenter.clickOnOriginalRetrofit();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
