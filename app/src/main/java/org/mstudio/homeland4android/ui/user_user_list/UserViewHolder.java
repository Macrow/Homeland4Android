package org.mstudio.homeland4android.ui.user_user_list;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.data.network.model.adjusted.UserListItem;
import org.mstudio.homeland4android.ui.user_profile.UserProfileActivity;
import org.mstudio.homeland4android.util.FastClickUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.viewholders.FlexibleViewHolder;

import static org.mstudio.homeland4android.ui.user_profile.UserProfileActivity.EXTRA_USER_LOGIN_NAME;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/14
 * desc   :
 */
public class UserViewHolder extends FlexibleViewHolder {

    public @BindView(R.id.tvLogin) TextView tvLogin;
    public @BindView(R.id.tvName) TextView tvName;
    public @BindView(R.id.ivImage) ImageView ivImage;

    public UserViewHolder(View view, FlexibleAdapter adapter) {
        super(view, adapter);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (!FastClickUtil.isFastClick()) {
            UserListItem user = (UserListItem) mAdapter.getItem(getAdapterPosition());
            Intent intent = new Intent(view.getContext(), UserProfileActivity.class);
            intent.putExtra(EXTRA_USER_LOGIN_NAME, user.getLogin());
            view.getContext().startActivity(intent);
        }
    }

}