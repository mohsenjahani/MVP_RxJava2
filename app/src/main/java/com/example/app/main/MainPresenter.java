package com.example.app.main;

import android.os.Handler;

import com.example.app.core.BasePresenter;

/**
 * Created by Neo on 2018-09-23.
 * http://m3n.ir/
 */

public class MainPresenter extends BasePresenter<IMain.Callback> implements IMain.Presenter {

    MainPresenter(IMain.Callback callback) {
        super(callback);
        mCallback.showLoading();
    }

    @Override
    public void loadData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mCallback.hideLoading();
                mCallback.setData("TEST DATA");
            }
        }, 2000);
    }
}
