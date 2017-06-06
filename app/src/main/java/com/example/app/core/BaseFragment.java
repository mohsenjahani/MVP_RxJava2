package com.example.app.core;

import android.support.v4.app.Fragment;

/**
 * Created by jose.cardoso on 06-06-2017.
 */

public class BaseFragment<T extends BasePresenter> extends Fragment {

    protected T mPresenter;

    @Override
    public void onStart() {
        super.onStart();
        if (mPresenter != null) {
            mPresenter.onViewAttached();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onViewDetached();
        }
    }
}
