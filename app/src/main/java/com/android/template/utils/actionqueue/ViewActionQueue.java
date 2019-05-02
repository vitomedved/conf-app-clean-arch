package com.android.template.utils.actionqueue;

import java.util.Iterator;
import java.util.LinkedList;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

public final class ViewActionQueue<View> {

    private final LinkedList<Consumer<View>> viewActions = new LinkedList<>();
    private final Object queueLock = new Object();

    private final PublishSubject<Consumer<View>> viewActionSubject = PublishSubject.create();
    private final CompositeDisposable subscriptions = new CompositeDisposable();

    private boolean isPaused = true;

    public void subscribeTo(final Observable<Consumer<View>> observable, final Consumer<View> onCompleteAction, final Consumer<Throwable> errorAction) {
        subscriptions.add(observable.observeOn(AndroidSchedulers.mainThread()).subscribe(this::onResult, errorAction, () -> onResult(onCompleteAction)));
    }

    public void subscribeTo(final Single<Consumer<View>> single, final Consumer<Throwable> errorAction) {
        subscriptions.add(single.observeOn(AndroidSchedulers.mainThread()).subscribe(this::onResult, errorAction));
    }

    public void subscribeTo(final Completable completable, final Consumer<View> onCompleteAction, final Consumer<Throwable> errorAction) {
        subscriptions.add(completable.observeOn(AndroidSchedulers.mainThread()).subscribe(() -> onResult(onCompleteAction), errorAction));
    }

    private void onResult(final Consumer<View> resultAction) {
        if (isPaused) {
            synchronized (queueLock) {
                viewActions.add(resultAction);
            }
        } else {
            viewActionSubject.onNext(resultAction);
        }
    }

    public void scheduleViewAction(final Consumer<View> action) {
        onResult(action);
    }

    public void pause() {
        isPaused = true;
    }

    public void resume() {
        isPaused = false;
        consumeQueue();
    }

    public void destroy() {
        subscriptions.dispose();
        viewActionSubject.onComplete();
    }

    private void consumeQueue() {
        synchronized (queueLock) {
            final Iterator<Consumer<View>> actionIterator = viewActions.iterator();
            while (actionIterator.hasNext()) {
                final Consumer<View> pendingViewAction = actionIterator.next();
                viewActionSubject.onNext(pendingViewAction);
                actionIterator.remove();
            }
        }
    }

    public Observable<Consumer<View>> viewActionsObservable() {
        return viewActionSubject;
    }
}
