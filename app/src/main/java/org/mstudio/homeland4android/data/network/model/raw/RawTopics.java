package org.mstudio.homeland4android.data.network.model.raw;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/04
 * desc   :
 */
public class RawTopics {

    @SerializedName("topics")
    private List<TopicsBean> topics;

    public List<TopicsBean> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicsBean> topics) {
        this.topics = topics;
    }

    public static class TopicsBean {
        /**
         * id : 32986
         * title : 这个 bug 怎么解决
         * created_at : 2017-05-13T17:49:07.996+08:00
         * updated_at : 2017-05-13T23:17:35.766+08:00
         * replied_at : 2017-05-13T23:17:35.665+08:00
         * replies_count : 6
         * node_name : 新手问题
         * node_id : 52
         * last_reply_user_id : 31035
         * last_reply_user_login : nicetyler
         * excellent : 0
         * likes_count : 0
         * suggested_at : null
         * closed_at : null
         * deleted : false
         * user : {"id":31035,"login":"nicetyler","name":"王轶坤","avatar_url":"https://ruby-china.org/system/letter_avatars/2/N/247_192_0/240.png","abilities":{"update":false,"destroy":false}}
         * hits : 130
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
             * id : 31035
             * login : nicetyler
             * name : 王轶坤
             * avatar_url : https://ruby-china.org/system/letter_avatars/2/N/247_192_0/240.png
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
}