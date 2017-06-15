package org.mstudio.homeland4android.ui.profile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.squareup.picasso.Picasso;

import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.data.network.model.adjusted.User;
import org.mstudio.homeland4android.ui.base.AlertDialogCaller;
import org.mstudio.homeland4android.ui.base.BaseFragment;
import org.mstudio.homeland4android.ui.notifications.NotificationsActivity;
import org.mstudio.homeland4android.ui.user_topic_list.UserTopicListActivity;
import org.mstudio.homeland4android.ui.user_user_list.UserUserListActivity;
import org.mstudio.homeland4android.util.AlertDialogUtil;
import org.mstudio.homeland4android.util.FastClickUtil;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static org.mstudio.homeland4android.ui.user_profile.UserProfileActivity.EXTRA_USER_LOGIN_NAME;
import static org.mstudio.homeland4android.ui.user_topic_list.UserTopicListActivity.EXTRA_USER_TOPIC_LIST_TYPE;
import static org.mstudio.homeland4android.ui.user_user_list.UserUserListActivity.EXTRA_USER_USER_LIST_TYPE;

public class ProfileFragment extends BaseFragment implements ProfileContract.View, AlertDialogCaller {

    private ProfileContract.Presenter mPresenter;

    @BindView(R.id.ivUserImage)
    CircleImageView ivUserImage;
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.tvUserIntro)
    TextView tvUserIntro;
    @BindView(R.id.tvTopicCount)
    TextView tvTopicCount;
    @BindView(R.id.tvReplyCount)
    TextView tvReplyCount;
    @BindView(R.id.tvFollowingCount)
    TextView tvFollowingCount;
    @BindView(R.id.tvFollowerCount)
    TextView tvFollowerCount;
    @BindView(R.id.tvFavoriteCount)
    TextView tvFavoriteCount;
    @BindView(R.id.tvUserCreatedAt)
    TextView tvUserCreatedAt;
    @BindView(R.id.trNotifications)
    TableRow trNotifications;
    @BindView(R.id.tvMyNotification)
    TextView tvMyNotification;

    public ProfileFragment() {}

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.finish();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, root);
        initBoxFeedback(root);
        mPresenter.start();
        return root;
    }

    @Override
    public void loadProfile(User user) {
        tvUserName.setText(user.mUser.getName() + " (" + user.mUser.getLogin() + ")");
        tvTopicCount.setText(String.valueOf(user.mUser.getTopicsCount()));
        tvReplyCount.setText(String.valueOf(user.mUser.getRepliesCount()));
        tvFollowingCount.setText(String.valueOf(user.mUser.getFollowingCount()));
        tvFollowerCount.setText(String.valueOf(user.mUser.getFollowersCount()));
        tvFavoriteCount.setText(String.valueOf(user.mUser.getFavoritesCount()));
        tvUserIntro.setText(user.mUser.getLevelName() + " (" + user.mUser.getLevel() + ")");
        tvUserCreatedAt.setText(TimeUtils.getFriendlyTimeSpanByNow(user.mUser.getCreatedAt(), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")));
        Picasso.with(getContext())
                .load(user.mUser.getAvatarUrl())
                .placeholder(R.mipmap.noavatar)
                .into(ivUserImage);
    }

    @Override
    public void updateUnreadNotificationCount(int count) {
        tvMyNotification.setText("我的提醒" + " (" + String.valueOf(count) + "条未读)");
        if (count > 0) {
            tvMyNotification.setTextColor(Color.parseColor("#b90011"));
        }
    }

    @OnClick(R.id.trNotifications)
    public void loadNotificationsActivity() {
        if (!FastClickUtil.isFastClick()) {
            Intent intent = new Intent(getContext(), NotificationsActivity.class);
            startActivityForResult(intent, 0);
        }
    }

    @OnClick(R.id.btnLogout)
    public void Logout() {
        if (!FastClickUtil.isFastClick()) {
            AlertDialogUtil.buildDialog(getActivity(), this).show();
        }
    }

    @OnClick(R.id.tvFavoriteCount)
    public void loadUserFavoriteTopicList() {
        if (!FastClickUtil.isFastClick()) {
            Intent intent = new Intent(getContext(), UserTopicListActivity.class);
            intent.putExtra(EXTRA_USER_LOGIN_NAME, mPresenter.getProfileLoginName());
            intent.putExtra(EXTRA_USER_TOPIC_LIST_TYPE, "favorites");
            getContext().startActivity(intent);
        }
    }

    @OnClick(R.id.tvTopicCount)
    public void loadUserPublishTopicList() {
        if (!FastClickUtil.isFastClick()) {
            Intent intent = new Intent(getContext(), UserTopicListActivity.class);
            intent.putExtra(EXTRA_USER_LOGIN_NAME, mPresenter.getProfileLoginName());
            intent.putExtra(EXTRA_USER_TOPIC_LIST_TYPE, "publish");
            getContext().startActivity(intent);
        }
    }

    @OnClick(R.id.tvFollowerCount)
    public void loadUserFollowers() {
        if (!FastClickUtil.isFastClick()) {
            Intent intent = new Intent(getContext(), UserUserListActivity.class);
            intent.putExtra(EXTRA_USER_LOGIN_NAME, mPresenter.getProfileLoginName());
            intent.putExtra(EXTRA_USER_USER_LIST_TYPE, "followers");
            getContext().startActivity(intent);
        }
    }

    @OnClick(R.id.tvFollowingCount)
    public void loadUserFollowing() {
        if (!FastClickUtil.isFastClick()) {
            Intent intent = new Intent(getContext(), UserUserListActivity.class);
            intent.putExtra(EXTRA_USER_LOGIN_NAME, mPresenter.getProfileLoginName());
            intent.putExtra(EXTRA_USER_USER_LIST_TYPE, "following");
            getContext().startActivity(intent);
        }
    }

    @Override
    public void finishActivity() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void onReloadButtonFromFailedClick() {
        mPresenter.loadProfile();
    }

    @Override
    public void OnConfirmClick() {
        mPresenter.Logout();
    }
}
