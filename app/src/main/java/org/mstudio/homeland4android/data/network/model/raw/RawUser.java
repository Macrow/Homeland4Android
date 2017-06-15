package org.mstudio.homeland4android.data.network.model.raw;

import com.google.gson.annotations.SerializedName;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/06
 * desc   :
 */
public class RawUser {

    /**
     * user : {"id":269,"login":"macrow","name":"Macrow","avatar_url":"https://l.ruby-china.org/user/avatar/269.jpg!large","location":"重庆","company":"","twitter":"Macrow","website":"","tagline":"","github":"Macrow","created_at":"2011-11-26T10:39:39.000+08:00","topics_count":2,"replies_count":53,"following_count":0,"followers_count":1,"favorites_count":2,"level":"vip","level_name":"高级会员","bio":null,"email":"macrow_wh@163.com","abilities":{"update":false,"destroy":false}}
     * meta : {"followed":false,"blocked":false}
     */

    @SerializedName("user")
    private UserBean user;
    @SerializedName("meta")
    private MetaBean meta;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public static class UserBean {
        /**
         * id : 269
         * login : macrow
         * name : Macrow
         * avatar_url : https://l.ruby-china.org/user/avatar/269.jpg!large
         * location : 重庆
         * company :
         * twitter : Macrow
         * website :
         * tagline :
         * github : Macrow
         * created_at : 2011-11-26T10:39:39.000+08:00
         * topics_count : 2
         * replies_count : 53
         * following_count : 0
         * followers_count : 1
         * favorites_count : 2
         * level : vip
         * level_name : 高级会员
         * bio : null
         * email : macrow_wh@163.com
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
        @SerializedName("location")
        private String location;
        @SerializedName("company")
        private String company;
        @SerializedName("twitter")
        private String twitter;
        @SerializedName("website")
        private String website;
        @SerializedName("tagline")
        private String tagline;
        @SerializedName("github")
        private String github;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("topics_count")
        private int topicsCount;
        @SerializedName("replies_count")
        private int repliesCount;
        @SerializedName("following_count")
        private int followingCount;
        @SerializedName("followers_count")
        private int followersCount;
        @SerializedName("favorites_count")
        private int favoritesCount;
        @SerializedName("level")
        private String level;
        @SerializedName("level_name")
        private String levelName;
        @SerializedName("bio")
        private Object bio;
        @SerializedName("email")
        private String email;
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

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getTwitter() {
            return twitter;
        }

        public void setTwitter(String twitter) {
            this.twitter = twitter;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getTagline() {
            return tagline;
        }

        public void setTagline(String tagline) {
            this.tagline = tagline;
        }

        public String getGithub() {
            return github;
        }

        public void setGithub(String github) {
            this.github = github;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public int getTopicsCount() {
            return topicsCount;
        }

        public void setTopicsCount(int topicsCount) {
            this.topicsCount = topicsCount;
        }

        public int getRepliesCount() {
            return repliesCount;
        }

        public void setRepliesCount(int repliesCount) {
            this.repliesCount = repliesCount;
        }

        public int getFollowingCount() {
            return followingCount;
        }

        public void setFollowingCount(int followingCount) {
            this.followingCount = followingCount;
        }

        public int getFollowersCount() {
            return followersCount;
        }

        public void setFollowersCount(int followersCount) {
            this.followersCount = followersCount;
        }

        public int getFavoritesCount() {
            return favoritesCount;
        }

        public void setFavoritesCount(int favoritesCount) {
            this.favoritesCount = favoritesCount;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLevelName() {
            return levelName;
        }

        public void setLevelName(String levelName) {
            this.levelName = levelName;
        }

        public Object getBio() {
            return bio;
        }

        public void setBio(Object bio) {
            this.bio = bio;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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

    public static class MetaBean {
        /**
         * followed : false
         * blocked : false
         */

        @SerializedName("followed")
        private boolean followed;
        @SerializedName("blocked")
        private boolean blocked;

        public boolean isFollowed() {
            return followed;
        }

        public void setFollowed(boolean followed) {
            this.followed = followed;
        }

        public boolean isBlocked() {
            return blocked;
        }

        public void setBlocked(boolean blocked) {
            this.blocked = blocked;
        }
    }
}