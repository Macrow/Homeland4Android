package org.mstudio.homeland4android.data.network.model.raw;

import java.util.List;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/07
 * desc   :
 */
public class RawNotifications {

    private List<NotificationsBean> notifications;

    public List<NotificationsBean> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationsBean> notifications) {
        this.notifications = notifications;
    }

    public static class NotificationsBean {
        /**
         * id : 586209
         * created_at : 2016-08-29T21:04:39.145+08:00
         * updated_at : 2016-08-29T21:04:39.145+08:00
         * type : Mention
         * read : true
         * actor : {"id":6910,"login":"luoyi","name":"roy","avatar_url":"https://l.ruby-china.org/user/avatar/6910.jpg!large","abilities":{"update":false,"destroy":false}}
         * mention_type : Topic
         * mention : {"id":30928,"title":"关于 kindeditor 存 附件信息到数据库的问题","created_at":"2016-08-29T21:04:39.133+08:00","updated_at":"2016-09-17T15:00:14.156+08:00","replied_at":"2016-09-01T09:51:41.642+08:00","replies_count":6,"node_name":"新手问题","node_id":52,"last_reply_user_id":6910,"last_reply_user_login":"luoyi","excellent":0,"likes_count":0,"suggested_at":null,"closed_at":null,"deleted":false,"user":{"id":6910,"login":"luoyi","name":"roy","avatar_url":"https://l.ruby-china.org/user/avatar/6910.jpg!large","abilities":{"update":false,"destroy":false}},"hits":662,"abilities":{"update":false,"destroy":false,"ban":false,"excellent":false,"unexcellent":false,"close":false,"open":false}}
         * topic : {"id":30928,"title":"关于 kindeditor 存 附件信息到数据库的问题","created_at":"2016-08-29T21:04:39.133+08:00","updated_at":"2016-09-17T15:00:14.156+08:00","replied_at":"2016-09-01T09:51:41.642+08:00","replies_count":6,"node_name":"新手问题","node_id":52,"last_reply_user_id":6910,"last_reply_user_login":"luoyi","excellent":0,"likes_count":0,"suggested_at":null,"closed_at":null,"deleted":false,"user":{"id":6910,"login":"luoyi","name":"roy","avatar_url":"https://l.ruby-china.org/user/avatar/6910.jpg!large","abilities":{"update":false,"destroy":false}},"hits":662,"abilities":{"update":false,"destroy":false,"ban":false,"excellent":false,"unexcellent":false,"close":false,"open":false}}
         * reply : {"id":324376,"body_html":"<p><code>p 'Hello World'<\/code> 这样写才是最短的吧 <img title=\":joy:\" alt=\"😂\" src=\"https://twemoji.b0.upaiyun.com/2/svg/1f602.svg\" class=\"twemoji\"> <\/p>","body":"`p 'Hello World'` 这样写才是最短的吧 :joy: ","topic_id":33220,"created_at":"2017-06-13T10:43:04.009+08:00","updated_at":"2017-06-13T10:43:04.009+08:00","likes_count":0,"action":null,"target_type":null,"deleted":false,"user":{"id":14823,"login":"hw676018683","name":"专属","avatar_url":"https://l.ruby-china.org/user/avatar/14823.jpg!large","abilities":{"update":false,"destroy":false}},"abilities":{"update":false,"destroy":false}}
         */

        private int id;
        private String created_at;
        private String updated_at;
        private String type;
        private boolean read;
        private ActorBean actor;
        private String mention_type;
        private MentionBean mention;
        private TopicBean topic;
        private ReplyBean reply;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isRead() {
            return read;
        }

        public void setRead(boolean read) {
            this.read = read;
        }

        public ActorBean getActor() {
            return actor;
        }

        public void setActor(ActorBean actor) {
            this.actor = actor;
        }

        public String getMention_type() {
            return mention_type;
        }

        public void setMention_type(String mention_type) {
            this.mention_type = mention_type;
        }

        public MentionBean getMention() {
            return mention;
        }

        public void setMention(MentionBean mention) {
            this.mention = mention;
        }

        public TopicBean getTopic() {
            return topic;
        }

        public void setTopic(TopicBean topic) {
            this.topic = topic;
        }

        public ReplyBean getReply() {
            return reply;
        }

        public void setReply(ReplyBean reply) {
            this.reply = reply;
        }

        public static class ActorBean {
            /**
             * id : 6910
             * login : luoyi
             * name : roy
             * avatar_url : https://l.ruby-china.org/user/avatar/6910.jpg!large
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

        public static class MentionBean {
            /**
             * id : 30928
             * title : 关于 kindeditor 存 附件信息到数据库的问题
             * created_at : 2016-08-29T21:04:39.133+08:00
             * updated_at : 2016-09-17T15:00:14.156+08:00
             * replied_at : 2016-09-01T09:51:41.642+08:00
             * replies_count : 6
             * node_name : 新手问题
             * node_id : 52
             * last_reply_user_id : 6910
             * last_reply_user_login : luoyi
             * excellent : 0
             * likes_count : 0
             * suggested_at : null
             * closed_at : null
             * deleted : false
             * user : {"id":6910,"login":"luoyi","name":"roy","avatar_url":"https://l.ruby-china.org/user/avatar/6910.jpg!large","abilities":{"update":false,"destroy":false}}
             * hits : 662
             * abilities : {"update":false,"destroy":false,"ban":false,"excellent":false,"unexcellent":false,"close":false,"open":false}
             */

            private int id;
            private String title;
            private String created_at;
            private String updated_at;
            private String replied_at;
            private int replies_count;
            private String node_name;
            private int node_id;
            private int last_reply_user_id;
            private String last_reply_user_login;
            private int excellent;
            private int likes_count;
            private Object suggested_at;
            private Object closed_at;
            private boolean deleted;
            private UserBean user;
            private int hits;
            private AbilitiesBeanXX abilities;

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

            public String getReplied_at() {
                return replied_at;
            }

            public void setReplied_at(String replied_at) {
                this.replied_at = replied_at;
            }

            public int getReplies_count() {
                return replies_count;
            }

            public void setReplies_count(int replies_count) {
                this.replies_count = replies_count;
            }

            public String getNode_name() {
                return node_name;
            }

            public void setNode_name(String node_name) {
                this.node_name = node_name;
            }

            public int getNode_id() {
                return node_id;
            }

            public void setNode_id(int node_id) {
                this.node_id = node_id;
            }

            public int getLast_reply_user_id() {
                return last_reply_user_id;
            }

            public void setLast_reply_user_id(int last_reply_user_id) {
                this.last_reply_user_id = last_reply_user_id;
            }

            public String getLast_reply_user_login() {
                return last_reply_user_login;
            }

            public void setLast_reply_user_login(String last_reply_user_login) {
                this.last_reply_user_login = last_reply_user_login;
            }

            public int getExcellent() {
                return excellent;
            }

            public void setExcellent(int excellent) {
                this.excellent = excellent;
            }

            public int getLikes_count() {
                return likes_count;
            }

            public void setLikes_count(int likes_count) {
                this.likes_count = likes_count;
            }

            public Object getSuggested_at() {
                return suggested_at;
            }

            public void setSuggested_at(Object suggested_at) {
                this.suggested_at = suggested_at;
            }

            public Object getClosed_at() {
                return closed_at;
            }

            public void setClosed_at(Object closed_at) {
                this.closed_at = closed_at;
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

            public AbilitiesBeanXX getAbilities() {
                return abilities;
            }

            public void setAbilities(AbilitiesBeanXX abilities) {
                this.abilities = abilities;
            }

            public static class UserBean {
                /**
                 * id : 6910
                 * login : luoyi
                 * name : roy
                 * avatar_url : https://l.ruby-china.org/user/avatar/6910.jpg!large
                 * abilities : {"update":false,"destroy":false}
                 */

                private int id;
                private String login;
                private String name;
                private String avatar_url;
                private AbilitiesBeanX abilities;

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

                public AbilitiesBeanX getAbilities() {
                    return abilities;
                }

                public void setAbilities(AbilitiesBeanX abilities) {
                    this.abilities = abilities;
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

            public static class AbilitiesBeanXX {
                /**
                 * update : false
                 * destroy : false
                 * ban : false
                 * excellent : false
                 * unexcellent : false
                 * close : false
                 * open : false
                 */

                private boolean update;
                private boolean destroy;
                private boolean ban;
                private boolean excellent;
                private boolean unexcellent;
                private boolean close;
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

        public static class TopicBean {
            /**
             * id : 30928
             * title : 关于 kindeditor 存 附件信息到数据库的问题
             * created_at : 2016-08-29T21:04:39.133+08:00
             * updated_at : 2016-09-17T15:00:14.156+08:00
             * replied_at : 2016-09-01T09:51:41.642+08:00
             * replies_count : 6
             * node_name : 新手问题
             * node_id : 52
             * last_reply_user_id : 6910
             * last_reply_user_login : luoyi
             * excellent : 0
             * likes_count : 0
             * suggested_at : null
             * closed_at : null
             * deleted : false
             * user : {"id":6910,"login":"luoyi","name":"roy","avatar_url":"https://l.ruby-china.org/user/avatar/6910.jpg!large","abilities":{"update":false,"destroy":false}}
             * hits : 662
             * abilities : {"update":false,"destroy":false,"ban":false,"excellent":false,"unexcellent":false,"close":false,"open":false}
             */

            private int id;
            private String title;
            private String created_at;
            private String updated_at;
            private String replied_at;
            private int replies_count;
            private String node_name;
            private int node_id;
            private int last_reply_user_id;
            private String last_reply_user_login;
            private int excellent;
            private int likes_count;
            private Object suggested_at;
            private Object closed_at;
            private boolean deleted;
            private UserBeanX user;
            private int hits;
            private AbilitiesBeanXXXX abilities;

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

            public String getReplied_at() {
                return replied_at;
            }

            public void setReplied_at(String replied_at) {
                this.replied_at = replied_at;
            }

            public int getReplies_count() {
                return replies_count;
            }

            public void setReplies_count(int replies_count) {
                this.replies_count = replies_count;
            }

            public String getNode_name() {
                return node_name;
            }

            public void setNode_name(String node_name) {
                this.node_name = node_name;
            }

            public int getNode_id() {
                return node_id;
            }

            public void setNode_id(int node_id) {
                this.node_id = node_id;
            }

            public int getLast_reply_user_id() {
                return last_reply_user_id;
            }

            public void setLast_reply_user_id(int last_reply_user_id) {
                this.last_reply_user_id = last_reply_user_id;
            }

            public String getLast_reply_user_login() {
                return last_reply_user_login;
            }

            public void setLast_reply_user_login(String last_reply_user_login) {
                this.last_reply_user_login = last_reply_user_login;
            }

            public int getExcellent() {
                return excellent;
            }

            public void setExcellent(int excellent) {
                this.excellent = excellent;
            }

            public int getLikes_count() {
                return likes_count;
            }

            public void setLikes_count(int likes_count) {
                this.likes_count = likes_count;
            }

            public Object getSuggested_at() {
                return suggested_at;
            }

            public void setSuggested_at(Object suggested_at) {
                this.suggested_at = suggested_at;
            }

            public Object getClosed_at() {
                return closed_at;
            }

            public void setClosed_at(Object closed_at) {
                this.closed_at = closed_at;
            }

            public boolean isDeleted() {
                return deleted;
            }

            public void setDeleted(boolean deleted) {
                this.deleted = deleted;
            }

            public UserBeanX getUser() {
                return user;
            }

            public void setUser(UserBeanX user) {
                this.user = user;
            }

            public int getHits() {
                return hits;
            }

            public void setHits(int hits) {
                this.hits = hits;
            }

            public AbilitiesBeanXXXX getAbilities() {
                return abilities;
            }

            public void setAbilities(AbilitiesBeanXXXX abilities) {
                this.abilities = abilities;
            }

            public static class UserBeanX {
                /**
                 * id : 6910
                 * login : luoyi
                 * name : roy
                 * avatar_url : https://l.ruby-china.org/user/avatar/6910.jpg!large
                 * abilities : {"update":false,"destroy":false}
                 */

                private int id;
                private String login;
                private String name;
                private String avatar_url;
                private AbilitiesBeanXXX abilities;

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

                public AbilitiesBeanXXX getAbilities() {
                    return abilities;
                }

                public void setAbilities(AbilitiesBeanXXX abilities) {
                    this.abilities = abilities;
                }

                public static class AbilitiesBeanXXX {
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

            public static class AbilitiesBeanXXXX {
                /**
                 * update : false
                 * destroy : false
                 * ban : false
                 * excellent : false
                 * unexcellent : false
                 * close : false
                 * open : false
                 */

                private boolean update;
                private boolean destroy;
                private boolean ban;
                private boolean excellent;
                private boolean unexcellent;
                private boolean close;
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

        public static class ReplyBean {
            /**
             * id : 324376
             * body_html : <p><code>p 'Hello World'</code> 这样写才是最短的吧 <img title=":joy:" alt="😂" src="https://twemoji.b0.upaiyun.com/2/svg/1f602.svg" class="twemoji"> </p>
             * body : `p 'Hello World'` 这样写才是最短的吧 :joy:
             * topic_id : 33220
             * created_at : 2017-06-13T10:43:04.009+08:00
             * updated_at : 2017-06-13T10:43:04.009+08:00
             * likes_count : 0
             * action : null
             * target_type : null
             * deleted : false
             * user : {"id":14823,"login":"hw676018683","name":"专属","avatar_url":"https://l.ruby-china.org/user/avatar/14823.jpg!large","abilities":{"update":false,"destroy":false}}
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
            private UserBeanXX user;
            private AbilitiesBeanXXXXXX abilities;

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

            public UserBeanXX getUser() {
                return user;
            }

            public void setUser(UserBeanXX user) {
                this.user = user;
            }

            public AbilitiesBeanXXXXXX getAbilities() {
                return abilities;
            }

            public void setAbilities(AbilitiesBeanXXXXXX abilities) {
                this.abilities = abilities;
            }

            public static class UserBeanXX {
                /**
                 * id : 14823
                 * login : hw676018683
                 * name : 专属
                 * avatar_url : https://l.ruby-china.org/user/avatar/14823.jpg!large
                 * abilities : {"update":false,"destroy":false}
                 */

                private int id;
                private String login;
                private String name;
                private String avatar_url;
                private AbilitiesBeanXXXXX abilities;

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

                public AbilitiesBeanXXXXX getAbilities() {
                    return abilities;
                }

                public void setAbilities(AbilitiesBeanXXXXX abilities) {
                    this.abilities = abilities;
                }

                public static class AbilitiesBeanXXXXX {
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

            public static class AbilitiesBeanXXXXXX {
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
}
