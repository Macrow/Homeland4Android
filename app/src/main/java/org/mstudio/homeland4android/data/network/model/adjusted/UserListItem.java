package org.mstudio.homeland4android.data.network.model.adjusted;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.data.network.model.raw.RawFollowers;
import org.mstudio.homeland4android.data.network.model.raw.RawFollowing;
import org.mstudio.homeland4android.ui.user_user_list.UserViewHolder;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/6/14
 * desc   :
 */
public class UserListItem extends AbstractFlexibleItem {

    private RawFollowers.FollowersBean mFollowersBean;
    private RawFollowing.FollowingBean mFollowingBean;

    public UserListItem() {
    }

    public void init(RawFollowers.FollowersBean bean) {
        mFollowersBean = bean;
    }

    public void init(RawFollowing.FollowingBean bean) {
        mFollowingBean = bean;
    }

    public String getLogin() {
        if (mFollowersBean != null) {
            return mFollowersBean.getLogin();
        } else if (mFollowingBean != null) {
            return mFollowingBean.getLogin();
        } else {
            return null;
        }
    }

    public String getName() {
        if (mFollowersBean != null) {
            return mFollowersBean.getName();
        } else if (mFollowingBean != null) {
            return mFollowingBean.getName();
        } else {
            return null;
        }
    }

    public String getAvatar() {
        if (mFollowersBean != null) {
            return mFollowersBean.getAvatar_url();
        } else if (mFollowingBean != null) {
            return mFollowingBean.getAvatar_url();
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_user;
    }

    @Override
    public UserViewHolder createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        return new UserViewHolder(inflater.inflate(getLayoutRes(), parent, false), adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, RecyclerView.ViewHolder holder, int position, List payloads) {
        UserViewHolder topicViewHolder = (UserViewHolder) holder;
        Context context = topicViewHolder.itemView.getContext();
        topicViewHolder.tvLogin.setText(getLogin());
        topicViewHolder.tvName.setText("（" + getName() + "）");

        Picasso.with(context)
                .load(getAvatar())
                .placeholder(R.mipmap.noavatar)
                .into(topicViewHolder.ivImage);
    }
}
