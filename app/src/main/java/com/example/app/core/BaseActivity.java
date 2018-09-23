package com.example.app.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by jose.cardoso on 06-06-2017.
 */

public class BaseActivity<T extends BasePresenter> extends AppCompatActivity{

    protected T mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mPresenter != null) {
            mPresenter.onViewAttached();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onViewDetached();
        }
    }
}
