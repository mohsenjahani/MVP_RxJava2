package com.example.app.core;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by jose.cardoso on 06-06-2017.
 */

public class BasePresenter<T extends BaseCallback> {

    protected T mCallback;
    private CompositeDisposable mCompositeDisposable;

    public BasePresenter(T callback) {
        mCallback = callback;
    }

    public void onViewAttached() {
        mCompositeDisposable = new CompositeDisposable();
    }

    public void onViewDetached() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }
}
