package org.mstudio.homeland4android.ui.reply_add_edit;


import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.data.network.model.adjusted.ReplyDetailItem;
import org.mstudio.homeland4android.ui.base.BaseFragment;
import org.mstudio.homeland4android.util.ImageGetterUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

import static org.mstudio.homeland4android.ui.reply_add_edit.ReplyAddEditActivity.EXTRA_REPLY_ID;
import static org.mstudio.homeland4android.ui.topic_detail.TopicDetailActivity.EXTRA_TOPIC_ID;

public class ReplyAddEditFragment extends BaseFragment implements ReplyAddEditContract.View {

    private ReplyAddEditContract.Presenter mPresenter;
    private boolean mIsReplyDirty = false;

    @BindView(R.id.etReplyBody)
    EditText etReplyBody;

    public ReplyAddEditFragment() {}

    public static ReplyAddEditFragment newInstance(int topicId, int replyId) {
        Bundle args = new Bundle();
        args.putInt(EXTRA_TOPIC_ID, topicId);
        args.putInt(EXTRA_REPLY_ID, replyId);
        ReplyAddEditFragment fragment = new ReplyAddEditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_reply_add_edit, container, false);
        ButterKnife.bind(this, root);
        initBoxFeedback(root);
        mPresenter.start();
        return root;
    }

    @Override
    public void setPresenter(ReplyAddEditContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.finish();
    }

    @Override
    public void finishActivity() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public boolean validateReply() {
        String inputBody = etReplyBody.getText().toString().trim();
        if (inputBody.equals("")) {
            showMessage("内容不能为空");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String getBody() {
        return etReplyBody.getText().toString();
    }

    @Override
    public boolean isReplyDirty() {
        return mIsReplyDirty;
    }

    @Override
    public void loadReply(ReplyDetailItem replyDetailItem) {
        etReplyBody.setText(replyDetailItem.mReplyItem.getBody());
    }

    @Override
    public void uploadPhoto(String image_url) {
        int position = etReplyBody.getSelectionStart();
        Editable edit = etReplyBody.getEditableText();
        if (position < 0 || position >= edit.length() ){
            edit.append(ImageGetterUtil.getFormatUrl(image_url));
        } else {
            edit.insert(position, ImageGetterUtil.getFormatUrl(image_url));
        }
    }

    @Override
    public void onReloadButtonFromFailedClick() {
        mPresenter.start();
    }

    @Override
    public void startListenReplyChanged() {
        etReplyBody.addTextChangedListener(getReplyDirtyWatcher());
    }

    private TextWatcher getReplyDirtyWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                mIsReplyDirty = true;
            }
        };
    }
}
