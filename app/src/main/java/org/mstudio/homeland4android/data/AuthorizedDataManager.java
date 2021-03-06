package org.mstudio.homeland4android.data;

import android.content.Context;
import android.content.Intent;

import org.mstudio.homeland4android.data.network.ApiService;
import org.mstudio.homeland4android.data.network.OAuthService;
import org.mstudio.homeland4android.data.network.model.adjusted.NotificationListItem;
import org.mstudio.homeland4android.data.network.model.adjusted.ReplyDetailItem;
import org.mstudio.homeland4android.data.network.model.adjusted.TopicDetailItem;
import org.mstudio.homeland4android.data.network.model.adjusted.TopicListItem;
import org.mstudio.homeland4android.data.network.model.adjusted.User;
import org.mstudio.homeland4android.data.network.model.raw.RawActionResult;
import org.mstudio.homeland4android.data.network.model.raw.RawLikeResult;
import org.mstudio.homeland4android.data.network.model.raw.RawNotifications;
import org.mstudio.homeland4android.data.network.model.raw.RawReply;
import org.mstudio.homeland4android.data.network.model.raw.RawToken;
import org.mstudio.homeland4android.data.network.model.raw.RawTopic;
import org.mstudio.homeland4android.data.network.model.raw.RawTopics;
import org.mstudio.homeland4android.data.network.model.raw.RawUnreadNotificationResult;
import org.mstudio.homeland4android.data.network.model.raw.RawUploadPhoto;
import org.mstudio.homeland4android.data.network.model.raw.RawUser;
import org.mstudio.homeland4android.ui.login.LoginActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.HttpException;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/06
 * desc   :
 */
public class AuthorizedDataManager {

    private ApiService mApiService;
    private OAuthService mOAuthService;
    private TokenManager mTokenManager;

    @Inject
    public AuthorizedDataManager(ApiService apiService, OAuthService oAuthService, TokenManager tokenManager) {
        mApiService = apiService;
        mOAuthService = oAuthService;
        mTokenManager = tokenManager;
    }

    public Observable<User> loadMyProfile() {
        return getTransObservable(mApiService.getMyProfile(mTokenManager.getAccessToken())
            .map(new Function<RawUser, User>() {
                @Override
                public User apply(@NonNull RawUser rawUser) throws Exception {
                    return new User(rawUser.getUser(), rawUser.getMeta());
                }
            }));
    }

    public Observable<RawUnreadNotificationResult> loadUnreadNotificationCount() {
        return getTransObservable(mApiService.loadUnreadNotificationCount(mTokenManager.getAccessToken()));
    }

    public Observable<TopicDetailItem> createTopic(final String title, final int nodeId, final String body) {
        return getTransObservable(mApiService.createTopic(title, nodeId, body, mTokenManager.getAccessToken())
                .map(new Function<RawTopic, TopicDetailItem>() {
                    @Override
                    public TopicDetailItem apply(@NonNull RawTopic rawTopic) throws Exception {
                        return new TopicDetailItem(rawTopic.getTopic(), rawTopic.getMeta());
                    }
                }));
    }

    public Observable<TopicDetailItem> updateTopic(final int id, final String title, final int nodeId, final String body) {
        return getTransObservable(mApiService.updateTopic(id, title, nodeId, body, mTokenManager.getAccessToken())
                .map(new Function<RawTopic, TopicDetailItem>() {
                    @Override
                    public TopicDetailItem apply(@NonNull RawTopic rawTopic) throws Exception {
                        return new TopicDetailItem(rawTopic.getTopic(), rawTopic.getMeta());
                    }
                }));
    }

    public Observable<TopicDetailItem> loadTopic(final int topicId) {
        return getTransObservable(mApiService.getTopicDetail(topicId, mTokenManager.getAccessToken())
                .map(new Function<RawTopic, TopicDetailItem>() {
                    @Override
                    public TopicDetailItem apply(@NonNull RawTopic rawTopic) throws Exception {
                        return new TopicDetailItem(rawTopic.getTopic(), rawTopic.getMeta());
                    }
                }));
    }

    public Observable<ReplyDetailItem> loadReply(final int replyId) {
        return getTransObservable(mApiService.getReplyDetail(replyId, mTokenManager.getAccessToken())
                .map(new Function<RawReply, ReplyDetailItem>() {
                    @Override
                    public ReplyDetailItem apply(@NonNull RawReply rawReply) throws Exception {
                        return new ReplyDetailItem(rawReply.getReply());
                    }
                }));
    }

    public Observable<ReplyDetailItem> createReply(final int topicId, final String body) {
        return getTransObservable(mApiService.createReply(topicId, body, mTokenManager.getAccessToken())
                .map(new Function<RawReply, ReplyDetailItem>() {
                    @Override
                    public ReplyDetailItem apply(@NonNull RawReply rawReply) throws Exception {
                        return new ReplyDetailItem(rawReply.getReply());
                    }
                }));
    }

    public Observable<ReplyDetailItem> updateReply(final int replyId, final String body) {
        return getTransObservable(mApiService.updateReply(replyId, body, mTokenManager.getAccessToken())
                .map(new Function<RawReply, ReplyDetailItem>() {
                    @Override
                    public ReplyDetailItem apply(@NonNull RawReply rawReply) throws Exception {
                        return new ReplyDetailItem(rawReply.getReply());
                    }
                }));
    }

    public Observable<RawActionResult> favoriteTopic(final int topicId) {
        return getTransObservable(mApiService.favoriteTopic(topicId, mTokenManager.getAccessToken()));
    }

    public Observable<RawActionResult> unfavoriteTopic(final int topicId) {
        return getTransObservable(mApiService.unfavoriteTopic(topicId, mTokenManager.getAccessToken()));
    }

    public Observable<RawLikeResult> likeTopic(final int topicId) {
        return getTransObservable(mApiService.likeTopicOrReply("topic", topicId, mTokenManager.getAccessToken()));
    }

    public Observable<RawLikeResult> unlikeTopic(final int topicId) {
        return getTransObservable(mApiService.unlikeTopicOrReply("topic", topicId, mTokenManager.getAccessToken()));
    }

    public Observable<RawActionResult> followTopic(final int topicId) {
        return getTransObservable(mApiService.followTopic(topicId, mTokenManager.getAccessToken()));
    }

    public Observable<RawActionResult> unfollowTopic(final int topicId) {
        return getTransObservable(mApiService.unfollowTopic(topicId, mTokenManager.getAccessToken()));
    }

    public Observable<RawActionResult> doActionOnTopic(final int topicId, final String actionType) {
        return getTransObservable(mApiService.doActionOnTopic(topicId, actionType, mTokenManager.getAccessToken()));
    }

    public Observable<List<NotificationListItem>> loadNotificationList(final int offset, final int limit) {
        return getTransObservable(mApiService.getNotifications(offset, limit, mTokenManager.getAccessToken())
                .map(new Function<RawNotifications, List<NotificationListItem>>() {
                    @Override
                    public List<NotificationListItem> apply(@NonNull RawNotifications rawNotifications) throws Exception {
                        List<NotificationListItem> items = new ArrayList<>();
                        for(RawNotifications.NotificationsBean notification : rawNotifications.getNotifications()) {
                            items.add(new NotificationListItem(notification));
                        }
                        return items;
                    }
                }));
    }

    public Observable<RawActionResult> clearAllNotifications() {
        return getTransObservable(mApiService.clearAllNotifications(mTokenManager.getAccessToken()));
    }

    public Observable<RawActionResult> blockUser(final String login) {
        return getTransObservable(mApiService.blockUser(login, mTokenManager.getAccessToken()));
    }

    public Observable<RawActionResult> unblockUser(final String login) {
        return getTransObservable(mApiService.unblockUser(login, mTokenManager.getAccessToken()));
    }

    public Observable<RawActionResult> followUser(final String login) {
        return getTransObservable(mApiService.followUser(login, mTokenManager.getAccessToken()));
    }

    public Observable<RawActionResult> unfollowUser(final String login) {
        return getTransObservable(mApiService.unfollowUser(login, mTokenManager.getAccessToken()));
    }

    public Observable<RawUploadPhoto> uploadPhoto(final String photo) {
        return getTransObservable(mApiService.uploadPhoto(prepareFilePart("file", photo), mTokenManager.getAccessToken()));
    }

    private MultipartBody.Part prepareFilePart(String partName, String path) {
        File file = new File(path);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg; charset=utf-8"), file);
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    private <T> Observable<T> getTransObservable(final Observable<T> o) {
        return Observable.defer(new Callable<ObservableSource<T>>() {
            @Override
            public ObservableSource<T> call() throws Exception {
                return o;
            }
        }).subscribeOn(Schedulers.io()).retryWhen(new RefreshTokenRetryFunction());
    }

    private class RefreshTokenRetryFunction implements Function<Observable<Throwable>, ObservableSource<?>> {
        @Override
        public ObservableSource<?> apply(final Observable<Throwable> throwableObservable) throws Exception {
            return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                @Override
                public ObservableSource<?> apply(Throwable throwable) throws Exception {
                    if (throwable instanceof HttpException) {
                        HttpException httpException = (HttpException) throwable;
                        if (httpException.code() == 401) {
                            return mOAuthService.getTokenByRefreshToken(mTokenManager.getRefreshToken(), "refresh_token")
                                    .doOnNext(new Consumer<RawToken>() {
                                        @Override
                                        public void accept(RawToken rawToken) throws Exception {
                                            mTokenManager.updateTokens(rawToken.getAccessToken(), rawToken.getRefreshToken());
                                        }})
                                    .doOnError(new Consumer<Throwable>() {
                                        @Override
                                        public void accept(@NonNull Throwable throwable) throws Exception {
                                            mTokenManager.Logout();
                                            Context context = mTokenManager.getContext();
                                            Intent intent = new Intent(context, LoginActivity.class);
                                            context.startActivity(intent);
                                        }
                                    });
                        }
                    }
                    return Observable.error(throwable);
                }
            });
        }
    }

    // ===================== No Force to Login =====================

    public Observable<RawUnreadNotificationResult> loadUnreadNotificationCountNoForceLogin() {
        return Observable.defer(new Callable<ObservableSource<? extends RawUnreadNotificationResult>>() {
            @Override
            public ObservableSource<? extends RawUnreadNotificationResult> call() throws Exception {
                return mApiService.loadUnreadNotificationCount(mTokenManager.getAccessToken());
            }
        }).subscribeOn(Schedulers.io()).retryWhen(new RefreshTokenNoForceLoginFunction());
    }

    private class RefreshTokenNoForceLoginFunction implements Function<Observable<Throwable>, ObservableSource<?>> {
        @Override
        public ObservableSource<?> apply(final Observable<Throwable> throwableObservable) throws Exception {
            return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                @Override
                public ObservableSource<?> apply(Throwable throwable) throws Exception {
                    if (throwable instanceof HttpException) {
                        HttpException httpException = (HttpException) throwable;
                        if (httpException.code() == 401) {
                            return mOAuthService.getTokenByRefreshToken(mTokenManager.getRefreshToken(), "refresh_token")
                                    .doOnNext(new Consumer<RawToken>() {
                                        @Override
                                        public void accept(RawToken rawToken) throws Exception {
                                            mTokenManager.updateTokens(rawToken.getAccessToken(), rawToken.getRefreshToken());
                                        }})
                                    .doOnError(new Consumer<Throwable>() {
                                        @Override
                                        public void accept(@NonNull Throwable throwable) throws Exception {
                                            mTokenManager.Logout();
                                        }
                                    });
                        }
                    }
                    return Observable.error(throwable);
                }
            });
        }
    }

    // ===================== No Force to Login =====================

}
