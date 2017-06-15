package org.mstudio.homeland4android.ui.topic_detail;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikepenz.iconics.view.IconicsImageView;

import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.data.network.model.adjusted.TopicDetailItem;
import org.mstudio.homeland4android.ui.topic_add_edit.TopicAddEditActivity;
import org.mstudio.homeland4android.ui.user_profile.UserProfileActivity;
import org.mstudio.homeland4android.util.FastClickUtil;

import br.tiagohm.markdownview.MarkdownView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.viewholders.FlexibleViewHolder;

import static org.mstudio.homeland4android.ui.topic_add_edit.TopicAddEditActivity.EXTRA_TOPIC_ID;
import static org.mstudio.homeland4android.ui.user_profile.UserProfileActivity.EXTRA_USER_LOGIN_NAME;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/05
 * desc   :
 */
public class TopicDetailViewHolder extends FlexibleViewHolder {

    public @BindView(R.id.tvUserName) TextView tvUserName;
    public @BindView(R.id.tvCreatedDateTime) TextView tvCreatedDateTime;
    public @BindView(R.id.tvNodeName) TextView tvNodeName;
    public @BindView(R.id.tvLikeCount) TextView tvLikeCount;
    public @BindView(R.id.tvHitCount) TextView tvHitCount;
    public @BindView(R.id.tvReplyCount) TextView tvReplyCount;
    public @BindView(R.id.ivUserImage) CircleImageView ivUserImage;
    public @BindView(R.id.llAdminBox) LinearLayout llAdminBox;
    public @BindView(R.id.tvEdit) TextView tvEdit;
    public @BindView(R.id.mvContentBody) MarkdownView mvContentBody;
    public @BindView(R.id.iiExcellent) IconicsImageView iiExcellent;
    public @BindView(R.id.tvExcellent) TextView tvExcellent;
    public @BindView(R.id.tvBan) TextView tvBan;
    public @BindView(R.id.tvDoExcellent) TextView tvDoExcellent;
    public @BindView(R.id.tvUnDoExcellent) TextView tvUnDoExcellent;
    public @BindView(R.id.tvClose) TextView tvClose;
    public @BindView(R.id.tvOpen) TextView tvOpen;

    public TopicDetailViewHolder(View view, FlexibleAdapter adapter) {
        super(view, adapter);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.tvEdit)
    public void editTopic() {
        if (!FastClickUtil.isFastClick()) {
            Intent intent = new Intent(getContentView().getContext(), TopicAddEditActivity.class);
            TopicDetailItem item = (TopicDetailItem) mAdapter.getItem(getFlexibleAdapterPosition());
            intent.putExtra(EXTRA_TOPIC_ID, item.mTopicItem.getId());
            ((TopicDetailActivity) getContentView().getContext()).startActivityForResult(intent, 0);
        }
    }

    @OnClick(R.id.ivUserImage)
    public void loadUserProfile() {
        if (!FastClickUtil.isFastClick()) {
            Intent intent = new Intent(getContentView().getContext(), UserProfileActivity.class);
            TopicDetailItem item = (TopicDetailItem) mAdapter.getItem(getFlexibleAdapterPosition());
            intent.putExtra(EXTRA_USER_LOGIN_NAME, item.mTopicItem.getUser().getLogin());
            getContentView().getContext().startActivity(intent);
        }
    }

    @OnClick(R.id.tvBan)
    public void banTopic() {
        if (!FastClickUtil.isFastClick()) {
            ((TopicDetailActivity) getContentView().getContext()).doActionOnTopic("ban");
        }
    }

    @OnClick(R.id.tvDoExcellent)
    public void doExcellentTopic() {
        if (!FastClickUtil.isFastClick()) {
            ((TopicDetailActivity) getContentView().getContext()).doActionOnTopic("excellent");
        }
    }

    @OnClick(R.id.tvUnDoExcellent)
    public void unDoExcellentTopic() {
        if (!FastClickUtil.isFastClick()) {
            ((TopicDetailActivity) getContentView().getContext()).doActionOnTopic("unexcellent");
        }
    }

    @OnClick(R.id.tvClose)
    public void closeTopic() {
        if (!FastClickUtil.isFastClick()) {
            ((TopicDetailActivity) getContentView().getContext()).doActionOnTopic("close");
        }
    }

    @OnClick(R.id.tvOpen)
    public void openTopic() {
        if (!FastClickUtil.isFastClick()) {
            ((TopicDetailActivity) getContentView().getContext()).doActionOnTopic("open");
        }
    }

}