package com.example.app.core;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Base Presenter that is lifecycle aware
 */
public class BasePresenter<T extends BaseCallback> {

    protected T mCallback;
    private CompositeDisposable mCompositeDisposable;

    public BasePresenter(T callback) {
        mCallback = callback;
    }

    /**
     * When the presenter is attached we create a new CompositeObservable for new observables to be
     * subscribed
     */
    public void onViewAttached() {
        mCompositeDisposable = new CompositeDisposable();
    }

    /**
     * When the presenter is detached we unsubscribe from all observables registers in the
     * CompositeObservable by clearing it
     */
    public void onViewDetached() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    /**
     * Binds the observable to the fragment lifecycle
     */
    protected <T> ObservableTransformer<T, T> applyBinding() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(final Observable<T> upstream) {
                return upstream
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                bindToLifecycle(disposable);
                            }
                        });
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
