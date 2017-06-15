package org.mstudio.homeland4android.ui.notifications;

import org.mstudio.homeland4android.data.AuthorizedDataManager;
import org.mstudio.homeland4android.data.network.model.adjusted.NotificationListItem;
import org.mstudio.homeland4android.data.network.model.raw.RawActionResult;
import org.mstudio.homeland4android.util.AppConstant;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/6/13
 * desc   :
 */
public class NotificationsPresenter implements NotificationsContract.Presenter {

    private AuthorizedDataManager mAuthorizedDataManager;
    private NotificationsContract.View mNotificationsView;
    private CompositeDisposable mCompositeDisposable;
    private boolean mFirstTimeLoad = true;
    private boolean mLoadNotificationListFinish = false;
    private int mOffset = 0;

    @Inject
    public NotificationsPresenter(AuthorizedDataManager authorizedDataManager,
                                  NotificationsContract.View notificationsView,
                                  CompositeDisposable compositeDisposable) {
        mAuthorizedDataManager = authorizedDataManager;
        mNotificationsView = notificationsView;
        mCompositeDisposable = compositeDisposable;
    }

    @Inject
    public void setListener() {
        mNotificationsView.setPresenter(this);
    }


    @Override
    public void start() {
        loadNotificationList(true);
    }

    @Override
    public void finish() {
        mCompositeDisposable.dispose();
        mNotificationsView = null;
    }

    @Override
    public void loadNotificationList(final boolean reload) {
        if (reload) {
            mOffset = 0;
            mLoadNotificationListFinish = false;
        } else if (mLoadNotificationListFinish) {
            return;
        } else {
            mOffset += AppConstant.PAGE_LIMIT;
        }
        mAuthorizedDataManager.loadNotificationList(mOffset, AppConstant.PAGE_LIMIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NotificationListItem>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        if (mFirstTimeLoad) {
                            mNotificationsView.showLoading();
                        }
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull List<NotificationListItem> notificationListItemList) {
                        mNotificationsView.loadNotificationList(notificationListItemList, reload);
                        if (notificationListItemList.size() < AppConstant.PAGE_LIMIT) {
                            mLoadNotificationListFinish = true;
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        mNotificationsView.showError();
                    }

                    @Override
                    public void onComplete() {
                        mNotificationsView.hideFeedbackBox();
                        mFirstTimeLoad = false;
                    }
                });
    }

    public void clearAll() {
        mAuthorizedDataManager.clearAllNotifications()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RawActionResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull RawActionResult rawActionResult) {
                        mOffset = 0;
                        mLoadNotificationListFinish = true;
                        mNotificationsView.clearAllNotifications();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        mNotificationsView.showMessage("清空提醒失败！");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
