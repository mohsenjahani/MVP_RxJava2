package com.example.app.core;

import com.example.app.utils.RxUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

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

    /**
     * Applies Rx Schedulers and binds the observable to the fragment lifecycle
     */
    protected <T> ObservableTransformer<T, T> applySchedulersAndBind() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(final Observable<T> upstream) {
                return upstream
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                bindToLifecycle(disposable);
                            }
                        })
                        .compose(RxUtils.<T>applySchedulers());
            }
        };
    }

    /**
     * Binds a disposable to this presenter lifecycle
     *
     * @param d Disposable to be added
     */
    protected void bindToLifecycle(Disposable d) {
        mCompositeDisposable.add(d);
    }
}
