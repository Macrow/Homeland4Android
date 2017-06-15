package org.mstudio.homeland4android.data.network;

import org.mstudio.homeland4android.data.network.model.raw.RawActionResult;
import org.mstudio.homeland4android.data.network.model.raw.RawFollowers;
import org.mstudio.homeland4android.data.network.model.raw.RawFollowing;
import org.mstudio.homeland4android.data.network.model.raw.RawLikeResult;
import org.mstudio.homeland4android.data.network.model.raw.RawNodes;
import org.mstudio.homeland4android.data.network.model.raw.RawNotifications;
import org.mstudio.homeland4android.data.network.model.raw.RawReplies;
import org.mstudio.homeland4android.data.network.model.raw.RawReply;
import org.mstudio.homeland4android.data.network.model.raw.RawTopic;
import org.mstudio.homeland4android.data.network.model.raw.RawTopics;
import org.mstudio.homeland4android.data.network.model.raw.RawUnreadNotificationResult;
import org.mstudio.homeland4android.data.network.model.raw.RawUploadPhoto;
import org.mstudio.homeland4android.data.network.model.raw.RawUser;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/04
 * desc   :
 */
public interface ApiService {

    @GET("topics.json")
    Observable<RawTopics> getTopicList(@Query("node_id") String nodeId,
                                       @Query("type") String type,
                                       @Query("offset") int offset,
                                       @Query("limit") int limit,
                                       @Query("access_token") String access_token);

    @GET("topics/{id}.json")
    Observable<RawTopic> getTopicDetail(@Path("id") int topicId,
                                        @Query("access_token") String access_token);

    @GET("topics/{id}/replies.json")
    Observable<RawReplies> getReplyList(@Path("id") int topicId,
                                        @Query("offset") int offset,
                                        @Query("limit") int limit,
                                        @Query("access_token") String access_token);

    @GET("replies/{id}.json")
    Observable<RawReply> getReplyDetail(@Path("id") int replyId,
                                        @Query("access_token") String access_token);

    @GET("users/me.json")
    Observable<RawUser> getMyProfile(@Query("access_token") String access_token);

    @GET("nodes.json")
    Observable<RawNodes> getNodeList(@Query("access_token") String access_token);

    @POST("topics.json")
    Observable<RawTopic> createTopic(@Query("title") String title,
                                     @Query("node_id") int nodeId,
                                     @Query("body") String body,
                                     @Query("access_token") String access_token);

    @POST("topics/{id}.json")
    Observable<RawTopic> updateTopic(@Path("id") int topicId,
                                     @Query("title") String title,
                                     @Query("node_id") int nodeId,
                                     @Query("body") String body,
                                     @Query("access_token") String access_token);

    @POST("topics/{id}/replies.json")
    Observable<RawReply> createReply(@Path("id") int topicId,
                                     @Query("body") String body,
                                     @Query("access_token") String access_token);

    @POST("replies/{id}.json")
    Observable<RawReply> updateReply(@Path("id") int replyId,
                                     @Query("body") String body,
                                     @Query("access_token") String access_token);

    @POST("topics/{id}/favorite.json")
    Observable<RawActionResult> favoriteTopic(@Path("id") int topicId,
                                              @Query("access_token") String access_token);

    @POST("topics/{id}/unfavorite.json")
    Observable<RawActionResult> unfavoriteTopic(@Path("id") int topicId,
                                                @Query("access_token") String access_token);

    @POST("likes.json")
    Observable<RawLikeResult> likeTopicOrReply(@Query("obj_type") String type,
                                               @Query("obj_id") int id,
                                               @Query("access_token") String access_token);

    @DELETE("likes.json")
    Observable<RawLikeResult> unlikeTopicOrReply(@Query("obj_type") String type,
                                                 @Query("obj_id") int id,
                                                 @Query("access_token") String access_token);

    @POST("topics/{id}/follow.json")
    Observable<RawActionResult> followTopic(@Path("id") int topicId,
                                            @Query("access_token") String access_token);

    @POST("topics/{id}/unfollow.json")
    Observable<RawActionResult> unfollowTopic(@Path("id") int topicId,
                                              @Query("access_token") String access_token);

    @POST("topics/{id}/action.json")
    Observable<RawActionResult> doActionOnTopic(@Path("id") int topicId,
                                                @Query("type") String actionType,
                                                @Query("access_token") String access_token);

    @GET("notifications/unread_count.json")
    Observable<RawUnreadNotificationResult> loadUnreadNotificationCount(@Query("access_token") String access_token);

    @GET("notifications.json")
    Observable<RawNotifications> getNotifications(@Query("offset") int offset,
                                                  @Query("limit") int limit,
                                                  @Query("access_token") String access_token);

    @DELETE("notifications/all")
    Observable<RawActionResult> clearAllNotifications(@Query("access_token") String access_token);

    @GET("users/{login}.json")
    Observable<RawUser> loadUserProfile(@Path("login") String login,
                                        @Query("access_token") String access_token);

    @GET("users/{login}/favorites.json")
    Observable<RawTopics> loadUserFavoriteTopics(@Path("login") String login,
                                                 @Query("offset") int offset,
                                                 @Query("limit") int limit,
                                                 @Query("access_token") String access_token);

    @GET("users/{login}/topics.json")
    Observable<RawTopics> loadUserPublishTopics(@Path("login") String login,
                                                @Query("offset") int offset,
                                                @Query("limit") int limit,
                                                @Query("access_token") String access_token);

    @GET("users/{login}/followers.json")
    Observable<RawFollowers> loadUserFollowers(@Path("login") String login,
                                               @Query("offset") int offset,
                                               @Query("limit") int limit,
                                               @Query("access_token") String access_token);

    @GET("users/{login}/following.json")
    Observable<RawFollowing> loadUserFollowing(@Path("login") String login,
                                               @Query("offset") int offset,
                                               @Query("limit") int limit,
                                               @Query("access_token") String access_token);

    @POST("users/{login}/block.json")
    Observable<RawActionResult> blockUser(@Path("login") String login,
                                          @Query("access_token") String access_token);

    @POST("users/{login}/unblock.json")
    Observable<RawActionResult> unblockUser(@Path("login") String login,
                                            @Query("access_token") String access_token);

    @POST("users/{login}/follow.json")
    Observable<RawActionResult> followUser(@Path("login") String login,
                                           @Query("access_token") String access_token);

    @POST("users/{login}/unfollow.json")
    Observable<RawActionResult> unfollowUser(@Path("login") String login,
                                             @Query("access_token") String access_token);

    @Multipart
    @POST("photos.json")
    Observable<RawUploadPhoto> uploadPhoto(@Part MultipartBody.Part file,
                                           @Query("access_token") String access_token);
}