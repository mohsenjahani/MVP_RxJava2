package com.example.app.main;

import com.example.app.core.BaseCallback;
import com.example.app.core.BasePresenter;

/**
 * Created by Neo on 2018-09-23.
 * http://m3n.ir/
 */

interface IMain {

    interface Callback extends BaseCallback {
        void showLoading();
        void hideLoading();

        void setData(String data);
    }

    interface Presenter {
        void loadData();

    }

}
