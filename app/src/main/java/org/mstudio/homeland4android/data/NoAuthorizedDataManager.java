package org.mstudio.homeland4android.data;

import org.mstudio.homeland4android.data.network.ApiService;
import org.mstudio.homeland4android.data.network.model.adjusted.NodeListItem;
import org.mstudio.homeland4android.data.network.model.adjusted.ReplyListItem;
import org.mstudio.homeland4android.data.network.model.adjusted.TopicAndReplyListItem;
import org.mstudio.homeland4android.data.network.model.adjusted.TopicDetailItem;
import org.mstudio.homeland4android.data.network.model.adjusted.TopicListItem;
import org.mstudio.homeland4android.data.network.model.adjusted.User;
import org.mstudio.homeland4android.data.network.model.adjusted.UserListItem;
import org.mstudio.homeland4android.data.network.model.raw.RawFollowers;
import org.mstudio.homeland4android.data.network.model.raw.RawFollowing;
import org.mstudio.homeland4android.data.network.model.raw.RawNodes;
import org.mstudio.homeland4android.data.network.model.raw.RawReplies;
import org.mstudio.homeland4android.data.network.model.raw.RawTopic;
import org.mstudio.homeland4android.data.network.model.raw.RawTopics;
import org.mstudio.homeland4android.data.network.model.raw.RawUser;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/04
 * desc   :
 */
public class NoAuthorizedDataManager {

    private ApiService mApiService;
    private TokenManager mTokenManager;

    @Inject
    public NoAuthorizedDataManager(ApiService apiService, TokenManager tokenManager) {
        mApiService = apiService;
        mTokenManager = tokenManager;
    }

    public Observable<List<TopicListItem>> loadTopicList(String nodeId, String filterType, int offset, int limit) {
        return mApiService.getTopicList(nodeId, filterType, offset, limit, mTokenManager.getAccessToken())
                .map(new Function<RawTopics, List<TopicListItem>>() {
                    @Override
                    public List<TopicListItem> apply(@NonNull RawTopics topics) throws Exception {
                        List<TopicListItem> items = new ArrayList<>();
                        for(RawTopics.TopicsBean topic : topics.getTopics()) {
                            items.add(new TopicListItem(topic));
                        }
                        return items;
                    }
                });
    }

    public Observable<TopicAndReplyListItem> loadTopicAndReplyList(int topicId, int offset, int limit) {
        return Observable.zip(
                mApiService.getTopicDetail(topicId, mTokenManager.getAccessToken())
                        .map(new Function<RawTopic, TopicDetailItem>() {
                            @Override
                            public TopicDetailItem apply(@NonNull RawTopic rawTopic) throws Exception {
                                return new TopicDetailItem(rawTopic.getTopic(), rawTopic.getMeta());
                            }
                        }),
                mApiService.getReplyList(topicId, offset, limit, mTokenManager.getAccessToken())
                        .map(new Function<RawReplies, List<ReplyListItem>>() {
                            @Override
                            public List<ReplyListItem> apply(@NonNull RawReplies rawReplies) throws Exception {
                                List<ReplyListItem> items = new ArrayList<>();
                                for (RawReplies.RepliesBean reply : rawReplies.getReplies()) {
                                    items.add(new ReplyListItem(reply));
                                }
                                return items;
                            }
                        }),
                new BiFunction<TopicDetailItem, List<ReplyListItem>, TopicAndReplyListItem>() {
                    @Override
                    public TopicAndReplyListItem apply(@NonNull TopicDetailItem topicDetailItem, @NonNull List<ReplyListItem> replyListItems) throws Exception {
                        return new TopicAndReplyListItem(topicDetailItem, replyListItems);
                    }
                }
        );
    }

    public Observable<List<ReplyListItem>> loadReplyList(int topicId, int offset, int limit) {
        return mApiService.getReplyList(topicId, offset, limit, mTokenManager.getAccessToken())
                .map(new Function<RawReplies, List<ReplyListItem>>() {
                    @Override
                    public List<ReplyListItem> apply(@NonNull RawReplies rawReplies) throws Exception {
                        List<ReplyListItem> items = new ArrayList<>();
                        for (RawReplies.RepliesBean reply : rawReplies.getReplies()) {
                            items.add(new ReplyListItem(reply));
                        }
                        return items;
                    }
                });
    }

    public Observable<List<NodeListItem>> loadNodeList() {
        return mApiService.getNodeList(mTokenManager.getAccessToken())
                .map(new Function<RawNodes, List<NodeListItem>>() {
                    @Override
                    public List<NodeListItem> apply(@NonNull RawNodes rawNodes) throws Exception {
                        List<NodeListItem> items = new ArrayList<>();
                        for (RawNodes.NodesBean node : rawNodes.getNodes()) {
                            items.add(new NodeListItem(null, node, ""));
                        }
                        return items;
                    }
                });
    }

    public Observable<User> loadUserProfile(String login) {
        return mApiService.loadUserProfile(login, mTokenManager.getAccessToken())
                .map(new Function<RawUser, User>() {
                    @Override
                    public User apply(@NonNull RawUser rawUser) throws Exception {
                        return new User(rawUser.getUser(), rawUser.getMeta());
                    }
                });
    }

    public Observable<List<TopicListItem>> loadUserTopics(String login, String topicType, int offset, int limit) {
        if (topicType.toLowerCase().equals("favorites")) {
            return mApiService.loadUserFavoriteTopics(login, offset, limit, mTokenManager.getAccessToken())
                    .map(new Function<RawTopics, List<TopicListItem>>() {
                        @Override
                        public List<TopicListItem> apply(@NonNull RawTopics topics) throws Exception {
                            List<TopicListItem> items = new ArrayList<>();
                            for (RawTopics.TopicsBean topic : topics.getTopics()) {
                                items.add(new TopicListItem(topic));
                            }
                            return items;
                        }
                    });
        } else if (topicType.toLowerCase().equals("publish")) {
            return mApiService.loadUserPublishTopics(login, offset, limit, mTokenManager.getAccessToken())
                    .map(new Function<RawTopics, List<TopicListItem>>() {
                        @Override
                        public List<TopicListItem> apply(@NonNull RawTopics topics) throws Exception {
                            List<TopicListItem> items = new ArrayList<>();
                            for (RawTopics.TopicsBean topic : topics.getTopics()) {
                                items.add(new TopicListItem(topic));
                            }
                            return items;
                        }
                    });
        } else {
            return null;
        }
    }

    public Observable<List<UserListItem>> loadUserList(String login, String userType, int offset, int limit) {
        if (userType.toLowerCase().equals("followers")) {
            return mApiService.loadUserFollowers(login, offset, limit, mTokenManager.getAccessToken())
                    .map(new Function<RawFollowers, List<UserListItem>>() {
                        @Override
                        public List<UserListItem> apply(@NonNull RawFollowers rawFollowers) throws Exception {
                            List<UserListItem> items = new ArrayList<>();
                            for (RawFollowers.FollowersBean bean : rawFollowers.getFollowers()) {
                                UserListItem i = new UserListItem();
                                i.init(bean);
                                items.add(i);
                            }
                            return items;
                        }
                    });
        } else if (userType.toLowerCase().equals("following")) {
            return mApiService.loadUserFollowing(login, offset, limit, mTokenManager.getAccessToken())
                    .map(new Function<RawFollowing, List<UserListItem>>() {
                        @Override
                        public List<UserListItem> apply(@NonNull RawFollowing rawFollowing) throws Exception {
                            List<UserListItem> items = new ArrayList<>();
                            for (RawFollowing.FollowingBean bean : rawFollowing.getFollowing()) {
                                UserListItem i = new UserListItem();
                                i.init(bean);
                                items.add(i);
                            }
                            return items;
                        }
                    });
        } else {
            return null;
        }
    }

}
