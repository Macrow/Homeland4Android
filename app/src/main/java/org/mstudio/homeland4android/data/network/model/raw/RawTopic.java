package org.mstudio.homeland4android.data.network.model.raw;

import com.google.gson.annotations.SerializedName;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/04
 * desc   :
 */
public class RawTopic {

    @SerializedName("topic")
    private TopicBean topic;
    @SerializedName("meta")
    private MetaBean meta;

    public TopicBean getTopic() {
        return topic;
    }

    public void setTopic(TopicBean topic) {
        this.topic = topic;
    }

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public static class TopicBean {
        /**
         * id : 1
         * title : Let's Party!
         * created_at : 2011-10-28T00:47:29.000+08:00
         * updated_at : 2017-01-09T17:57:13.643+08:00
         * replied_at : 2015-01-12T21:50:13.019+08:00
         * replies_count : 40
         * node_name : 公告
         * node_id : 21
         * last_reply_user_id : 16157
         * last_reply_user_login : bluealert
         * excellent : 0
         * likes_count : 3
         * suggested_at : null
         * closed_at : null
         * deleted : false
         * user : {"id":1,"login":"Rei","name":"Rei","avatar_url":"https://l.ruby-china.org/user/avatar/1.jpg!large","abilities":{"update":false,"destroy":false}}
         * body : ![](https://ruby-china-files.b0.upaiyun.com/photo/2016/bcdbdc7e55d377969dc56b5705e4e072.jpg)

         * body_html : <p><img src="https://ruby-china-files.b0.upaiyun.com/photo/2016/bcdbdc7e55d377969dc56b5705e4e072.jpg" title="" alt=""></p>
         * hits : 4255
         * abilities : {"update":false,"destroy":false,"ban":false,"excellent":false,"unexcellent":false,"close":false,"open":false}
         */

        @SerializedName("id")
        private int id;
        @SerializedName("title")
        private String title;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("updated_at")
        private String updatedAt;
        @SerializedName("replied_at")
        private String repliedAt;
        @SerializedName("replies_count")
        private int repliesCount;
        @SerializedName("node_name")
        private String nodeName;
        @SerializedName("node_id")
        private int nodeId;
        @SerializedName("last_reply_user_id")
        private int lastReplyUserId;
        @SerializedName("last_reply_user_login")
        private String lastReplyUserLogin;
        @SerializedName("excellent")
        private int excellent;
        @SerializedName("likes_count")
        private int likesCount;
        @SerializedName("suggested_at")
        private Object suggestedAt;
        @SerializedName("closed_at")
        private Object closedAt;
        @SerializedName("deleted")
        private boolean deleted;
        @SerializedName("user")
        private UserBean user;
        @SerializedName("body")
        private String body;
        @SerializedName("body_html")
        private String bodyHtml;
        @SerializedName("hits")
        private int hits;
        @SerializedName("abilities")
        private AbilitiesBeanX abilities;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getRepliedAt() {
            return repliedAt;
        }

        public void setRepliedAt(String repliedAt) {
            this.repliedAt = repliedAt;
        }

        public int getRepliesCount() {
            return repliesCount;
        }

        public void setRepliesCount(int repliesCount) {
            this.repliesCount = repliesCount;
        }

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        public int getNodeId() {
            return nodeId;
        }

        public void setNodeId(int nodeId) {
            this.nodeId = nodeId;
        }

        public int getLastReplyUserId() {
            return lastReplyUserId;
        }

        public void setLastReplyUserId(int lastReplyUserId) {
            this.lastReplyUserId = lastReplyUserId;
        }

        public String getLastReplyUserLogin() {
            return lastReplyUserLogin;
        }

        public void setLastReplyUserLogin(String lastReplyUserLogin) {
            this.lastReplyUserLogin = lastReplyUserLogin;
        }

        public int getExcellent() {
            return excellent;
        }

        public void setExcellent(int excellent) {
            this.excellent = excellent;
        }

        public int getLikesCount() {
            return likesCount;
        }

        public void setLikesCount(int likesCount) {
            this.likesCount = likesCount;
        }

        public Object getSuggestedAt() {
            return suggestedAt;
        }

        public void setSuggestedAt(Object suggestedAt) {
            this.suggestedAt = suggestedAt;
        }

        public Object getClosedAt() {
            return closedAt;
        }

        public void setClosedAt(Object closedAt) {
            this.closedAt = closedAt;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getBodyHtml() {
            return bodyHtml;
        }

        public void setBodyHtml(String bodyHtml) {
            this.bodyHtml = bodyHtml;
        }

        public int getHits() {
            return hits;
        }

        public void setHits(int hits) {
            this.hits = hits;
        }

        public AbilitiesBeanX getAbilities() {
            return abilities;
        }

        public void setAbilities(AbilitiesBeanX abilities) {
            this.abilities = abilities;
        }

        public static class UserBean {
            /**
             * id : 1
             * login : Rei
             * name : Rei
             * avatar_url : https://l.ruby-china.org/user/avatar/1.jpg!large
             * abilities : {"update":false,"destroy":false}
             */

            @SerializedName("id")
            private int id;
            @SerializedName("login")
            private String login;
            @SerializedName("name")
            private String name;
            @SerializedName("avatar_url")
            private String avatarUrl;
            @SerializedName("abilities")
            private AbilitiesBean abilities;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLogin() {
                return login;
            }

            public void setLogin(String login) {
                this.login = login;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public AbilitiesBean getAbilities() {
                return abilities;
            }

            public void setAbilities(AbilitiesBean abilities) {
                this.abilities = abilities;
            }

            public static class AbilitiesBean {
                /**
                 * update : false
                 * destroy : false
                 */

                @SerializedName("update")
                private boolean update;
                @SerializedName("destroy")
                private boolean destroy;

                public boolean isUpdate() {
                    return update;
                }

                public void setUpdate(boolean update) {
                    this.update = update;
                }

                public boolean isDestroy() {
                    return destroy;
                }

                public void setDestroy(boolean destroy) {
                    this.destroy = destroy;
                }
            }
        }

        public static class AbilitiesBeanX {
            /**
             * update : false
             * destroy : false
             * ban : false
             * excellent : false
             * unexcellent : false
             * close : false
             * open : false
             */

            @SerializedName("update")
            private boolean update;
            @SerializedName("destroy")
            private boolean destroy;
            @SerializedName("ban")
            private boolean ban;
            @SerializedName("excellent")
            private boolean excellent;
            @SerializedName("unexcellent")
            private boolean unexcellent;
            @SerializedName("close")
            private boolean close;
            @SerializedName("open")
            private boolean open;

            public boolean isUpdate() {
                return update;
            }

            public void setUpdate(boolean update) {
                this.update = update;
            }

            public boolean isDestroy() {
                return destroy;
            }

            public void setDestroy(boolean destroy) {
                this.destroy = destroy;
            }

            public boolean isBan() {
                return ban;
            }

            public void setBan(boolean ban) {
                this.ban = ban;
            }

            public boolean isExcellent() {
                return excellent;
            }

            public void setExcellent(boolean excellent) {
                this.excellent = excellent;
            }

            public boolean isUnexcellent() {
                return unexcellent;
            }

            public void setUnexcellent(boolean unexcellent) {
                this.unexcellent = unexcellent;
            }

            public boolean isClose() {
                return close;
            }

            public void setClose(boolean close) {
                this.close = close;
            }

            public boolean isOpen() {
                return open;
            }

            public void setOpen(boolean open) {
                this.open = open;
            }
        }
    }

    public static class MetaBean {
        /**
         * followed : false
         * liked : false
         * favorited : false
         */

        @SerializedName("followed")
        private boolean followed;
        @SerializedName("liked")
        private boolean liked;
        @SerializedName("favorited")
        private boolean favorited;

        public boolean isFollowed() {
            return followed;
        }

        public void setFollowed(boolean followed) {
            this.followed = followed;
        }

        public boolean isLiked() {
            return liked;
        }

        public void setLiked(boolean liked) {
            this.liked = liked;
        }

        public boolean isFavorited() {
            return favorited;
        }

        public void setFavorited(boolean favorited) {
            this.favorited = favorited;
        }
    }
}
