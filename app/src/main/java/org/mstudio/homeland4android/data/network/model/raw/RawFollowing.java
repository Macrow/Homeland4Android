package org.mstudio.homeland4android.data.network.model.raw;

import java.util.List;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/6/14
 * desc   :
 */
public class RawFollowing {

    private List<FollowingBean> following;

    public List<FollowingBean> getFollowing() {
        return following;
    }

    public void setFollowing(List<FollowingBean> following) {
        this.following = following;
    }

    public static class FollowingBean {
        /**
         * id : 146
         * login : skandhas
         * name :
         * avatar_url : https://l.ruby-china.org/user/avatar/146.jpg!large
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
}
