package org.mstudio.homeland4android.ui.topic_detail;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.data.network.model.adjusted.ReplyListItem;
import org.mstudio.homeland4android.data.network.model.adjusted.TopicDetailItem;
import org.mstudio.homeland4android.ui.reply_add_edit.ReplyAddEditActivity;
import org.mstudio.homeland4android.ui.user_profile.UserProfileActivity;
import org.mstudio.homeland4android.util.FastClickUtil;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.viewholders.FlexibleViewHolder;

import static org.mstudio.homeland4android.ui.reply_add_edit.ReplyAddEditActivity.EXTRA_REPLY_ID;
import static org.mstudio.homeland4android.ui.user_profile.UserProfileActivity.EXTRA_USER_LOGIN_NAME;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/05
 * desc   :
 */
public class ReplyViewHolder extends FlexibleViewHolder {

    public @BindView(R.id.tvUserName) TextView tvUserName;
    public @BindView(R.id.tvReplyNumber) TextView tvReplyNumber;
    public @BindView(R.id.tvReplyContent) HtmlTextView tvReplyContent;
    public @BindView(R.id.tvReplyCreatedDateTime) TextView tvReplyCreatedDateTime;
    public @BindView(R.id.ivUserImage) CircleImageView ivUserImage;
    public @BindView(R.id.llAdminBox) LinearLayout llAdminBox;
    public @BindView(R.id.tvEdit) TextView tvEdit;

    public ReplyViewHolder(View view, FlexibleAdapter adapter) {
        super(view, adapter);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.tvEdit)
    public void editReply() {
        if (!FastClickUtil.isFastClick()) {
            Intent intent = new Intent(getContentView().getContext(), ReplyAddEditActivity.class);
            ReplyListItem item = (ReplyListItem) mAdapter.getItem(getFlexibleAdapterPosition());
            intent.putExtra(EXTRA_REPLY_ID, item.mReplyItem.getId());
            ((TopicDetailActivity) getContentView().getContext()).startActivityForResult(intent, 0);
        }
    }

    @OnClick(R.id.ivUserImage)
    public void loadUserProfile() {
        if (!FastClickUtil.isFastClick()) {
            Intent intent = new Intent(getContentView().getContext(), UserProfileActivity.class);
            ReplyListItem item = (ReplyListItem) mAdapter.getItem(getFlexibleAdapterPosition());
            intent.putExtra(EXTRA_USER_LOGIN_NAME, item.mReplyItem.getUser().getLogin());
            getContentView().getContext().startActivity(intent);
        }
    }

}