package org.mstudio.homeland4android.ui.topic_add_edit;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.mstudio.homeland4android.HomelandApplication;
import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.data.network.model.adjusted.TopicDetailItem;
import org.mstudio.homeland4android.di.component.DaggerNodesComponent;
import org.mstudio.homeland4android.di.module.NodesModule;
import org.mstudio.homeland4android.ui.base.BaseFragment;
import org.mstudio.homeland4android.ui.base.DialogCaller;
import org.mstudio.homeland4android.ui.nodes.NodesDialogFragment;
import org.mstudio.homeland4android.util.FastClickUtil;
import org.mstudio.homeland4android.util.ImageGetterUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TopicAddEditFragment extends BaseFragment implements TopicAddEditContract.View, DialogCaller {

    private TopicAddEditContract.Presenter mPresenter;
    private boolean mIsTopicDirty = false;

    @BindView(R.id.tvNodeName)
    TextView tvNodeName;
    @BindView(R.id.tvNodeId)
    TextView tvNodeId;
    @BindView(R.id.etTopicTitle)
    EditText etTopicTitle;
    @BindView(R.id.etTopicBody)
    EditText etTopicBody;

    public TopicAddEditFragment() {}

    public static TopicAddEditFragment newInstance(int topicId) {
        Bundle args = new Bundle();
        args.putInt(TopicAddEditActivity.EXTRA_TOPIC_ID, topicId);
        TopicAddEditFragment fragment = new TopicAddEditFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_topic_add_edit, container, false);
        ButterKnife.bind(this, root);
        initBoxFeedback(root);
        mPresenter.start();
        return root;
    }

    @Override
    public void setPresenter(TopicAddEditContract.Presenter presenter) {
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
    public boolean validateTopic() {
        String inputTitle = etTopicTitle.getText().toString().trim();
        String inputBody = etTopicBody.getText().toString().trim();
        String inputNodeId = tvNodeId.getText().toString();
        if (inputTitle.equals("")) {
            showMessage("标题不能为空");
            return false;
        } else if (inputBody.equals("")) {
            showMessage("内容不能为空");
            return false;
        } else if (inputNodeId.equals("")) {
            showMessage("请选择节点");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String getTitle() {
        return etTopicTitle.getText().toString();
    }

    @Override
    public int getNodeId() {
        return Integer.parseInt(tvNodeId.getText().toString());
    }

    @Override
    public String getBody() {
        return etTopicBody.getText().toString();
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public boolean isTopicDirty() {
        return mIsTopicDirty;
    }

    @OnClick({R.id.tvNodeName, R.id.iiNodeIcon})
    public void SelectNode() {
        if (!FastClickUtil.isFastClick()) {
            NodesDialogFragment dialog = new NodesDialogFragment();
            dialog.setNeedFrontPage(false);
            DaggerNodesComponent.builder()
                    .basicComponent(((HomelandApplication) getContext().getApplicationContext()).getBasicComponent())
                    .nodesModule(new NodesModule(dialog)).build()
                    .inject(dialog);
            dialog.setCaller(this);
            dialog.show(getActivity().getSupportFragmentManager(), null);
        }
    }

    @Override
    public void onReloadButtonFromFailedClick() {
        mPresenter.start();
    }

    @Override
    public void OnItemClick(int id, String name) {
        tvNodeName.setText(name);
        tvNodeId.setText(String.valueOf(id));
    }

    @Override
    public void loadTopic(TopicDetailItem topicDetailItem) {
        tvNodeName.setText(topicDetailItem.mTopicItem.getNodeName());
        tvNodeId.setText(String.valueOf(topicDetailItem.mTopicItem.getNodeId()));
        etTopicTitle.setText(topicDetailItem.mTopicItem.getTitle());
        etTopicBody.setText(topicDetailItem.mTopicItem.getBody());
    }

    @Override
    public void uploadPhoto(String image_url) {
        int position = etTopicBody.getSelectionStart();
        Editable edit = etTopicBody.getEditableText();
        if (position < 0 || position >= edit.length() ){
            edit.append(ImageGetterUtil.getFormatUrl(image_url));
        } else {
            edit.insert(position, ImageGetterUtil.getFormatUrl(image_url));
        }
    }

    @Override
    public void startListenTopicChanged() {
        etTopicTitle.addTextChangedListener(getTopicDirtyWatcher());
        etTopicBody.addTextChangedListener(getTopicDirtyWatcher());
    }

    private TextWatcher getTopicDirtyWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                mIsTopicDirty = true;
            }
        };
    }
}
