package org.mstudio.homeland4android.data.network.model.raw;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/6/10
 * desc   :
 */
public class RawReply {
    /**
     * reply : {"id":1,"body_html":"<p>从今天开始，我们就有个窝了 <\/p>","body":"从今天开始，我们就有个窝了 ","topic_id":1,"created_at":"2011-10-28T00:50:32.000+08:00","updated_at":"2013-04-19T11:00:47.896+08:00","likes_count":0,"action":null,"target_type":null,"deleted":false,"user":{"id":2,"login":"huacnlee","name":"李华顺","avatar_url":"https://l.ruby-china.org/user/avatar/2/de6df3.png!large","abilities":{"update":false,"destroy":false}},"topic_title":"Let's Party!","abilities":{"update":false,"destroy":false}}
     * meta : null
     */

    private ReplyBean reply;
    private Object meta;

    public ReplyBean getReply() {
        return reply;
    }

    public void setReply(ReplyBean reply) {
        this.reply = reply;
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public static class ReplyBean {
        /**
         * id : 1
         * body_html : <p>从今天开始，我们就有个窝了 </p>
         * body : 从今天开始，我们就有个窝了
         * topic_id : 1
         * created_at : 2011-10-28T00:50:32.000+08:00
         * updated_at : 2013-04-19T11:00:47.896+08:00
         * likes_count : 0
         * action : null
         * target_type : null
         * deleted : false
         * user : {"id":2,"login":"huacnlee","name":"李华顺","avatar_url":"https://l.ruby-china.org/user/avatar/2/de6df3.png!large","abilities":{"update":false,"destroy":false}}
         * topic_title : Let's Party!
         * abilities : {"update":false,"destroy":false}
         */

        private int id;
        private String body_html;
        private String body;
        private int topic_id;
        private String created_at;
        private String updated_at;
        private int likes_count;
        private Object action;
        private Object target_type;
        private boolean deleted;
        private UserBean user;
        private String topic_title;
        private AbilitiesBeanX abilities;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBody_html() {
            return body_html;
        }

        public void setBody_html(String body_html) {
            this.body_html = body_html;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public int getTopic_id() {
            return topic_id;
        }

        public void setTopic_id(int topic_id) {
            this.topic_id = topic_id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public int getLikes_count() {
            return likes_count;
        }

        public void setLikes_count(int likes_count) {
            this.likes_count = likes_count;
        }

        public Object getAction() {
            return action;
        }

        public void setAction(Object action) {
            this.action = action;
        }

        public Object getTarget_type() {
            return target_type;
        }

        public void setTarget_type(Object target_type) {
            this.target_type = target_type;
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

        public String getTopic_title() {
            return topic_title;
        }

        public void setTopic_title(String topic_title) {
            this.topic_title = topic_title;
        }

        public AbilitiesBeanX getAbilities() {
            return abilities;
        }

        public void setAbilities(AbilitiesBeanX abilities) {
            this.abilities = abilities;
        }

        public static class UserBean {
            /**
             * id : 2
             * login : huacnlee
             * name : 李华顺
             * avatar_url : https://l.ruby-china.org/user/avatar/2/de6df3.png!large
             * abilities : {"update":false,"destroy":false}
             */

            private int id;
            private String login;
            private String name;
            private String avatar_url;
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

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
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

                private boolean update;
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
             */

            private boolean update;
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
}
