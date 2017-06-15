package org.mstudio.homeland4android.ui.nodes;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.data.network.model.adjusted.NodeListHeaderItem;
import org.mstudio.homeland4android.data.network.model.adjusted.NodeListItem;
import org.mstudio.homeland4android.data.network.model.raw.RawNodes;
import org.mstudio.homeland4android.ui.base.DialogCaller;
import org.mstudio.homeland4android.util.FastClickUtil;
import org.mstudio.homeland4android.util.IconFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/07
 * desc   :
 */
public class NodesDialogFragment extends BottomSheetDialogFragment implements NodesContract.View {

    @Inject
    NodesPresenter mNodesPresenter;

    private List<AbstractFlexibleItem> mNodeAndSectionList = new ArrayList<>();
    private FlexibleAdapter<IFlexible> mNodeListAdapter;

    private NodesContract.Presenter mPresenter;

    private DialogCaller mCaller;
    private boolean mNeedFrontPage = true;

    @BindView(R.id.llFeedbackBox) ViewGroup vgFeedbackBox;
    @BindView(R.id.pbLoading) View vLoading;
    @BindView(R.id.llFailedBox) View vFailedBox;

    @BindView(R.id.rvNodeList)
    RecyclerView rvNodeList;

    public NodesDialogFragment() {}

    public void setNeedFrontPage(boolean needFrontPage) {
        mNeedFrontPage = needFrontPage;
    }

    public void setCaller(DialogCaller caller) {
        mCaller = caller;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_nodes_dialog, container, false);
        ButterKnife.bind(this, root);
        initRecyclerView();
        mPresenter.start();
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCaller = null;
        mPresenter.finish();
    }

    private void initRecyclerView() {
        mNodeListAdapter = new FlexibleAdapter(mNodeAndSectionList);
        mNodeListAdapter.setDisplayHeadersAtStartUp(true)
                        .setStickyHeaders(true)
                        .setDisplayHeadersAtStartUp(true)
                        .showAllHeaders();
        mNodeListAdapter.addListener(new FlexibleAdapter.OnItemClickListener() {
            @Override
            public boolean onItemClick(int position) {
                if (!FastClickUtil.isFastClick()) {
                    IFlexible item = mNodeListAdapter.getItem(position);
                    if (item instanceof NodeListItem) {
                        NodeListItem node = (NodeListItem) item;
                        mCaller.OnItemClick(node.mNodeItem.getId(), node.mNodeItem.getName());
                        dismiss();
                        return true;
                    }
                }
                return false;
            }
        });
        rvNodeList.setAdapter(mNodeListAdapter);
        rvNodeList.setLayoutManager(new SmoothScrollLinearLayoutManager(getContext()));
        rvNodeList.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).build());
    }

    @Override
    public void loadNodes(List<NodeListItem> nodeListItems) {
        IconFactory iconFactory = new IconFactory();
        mNodeAndSectionList.clear();
        if (mNeedFrontPage) {
            mNodeAndSectionList.add(getFrontPageItem(iconFactory.getIcon()));
        } else {
            iconFactory.getIcon();
        }
        Collections.sort(nodeListItems);
        String headerName = "";
        NodeListHeaderItem headerItem = null;
        for (NodeListItem item : nodeListItems) {
            String itemSectionName = item.mNodeItem.getSectionName();
            if (!itemSectionName.equals(headerName)) {
                headerItem = new NodeListHeaderItem(itemSectionName);
                mNodeAndSectionList.add(headerItem);
                headerName = itemSectionName;
            }
            NodeListItem nodeListItem = new NodeListItem(headerItem, item.mNodeItem, iconFactory.getIcon());
            mNodeAndSectionList.add(nodeListItem);
        }
        mNodeListAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btnCancel)
    public void CancelDialog() {
        dismiss();
    }

    @Override
    public void finishActivity() {
        dismiss();
    }

    @Override
    public void setPresenter(NodesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        showFeedbackBox();
        vFailedBox.setVisibility(View.GONE);
        vLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        showFeedbackBox();
        vLoading.setVisibility(View.GONE);
        vFailedBox.setVisibility(View.VISIBLE);
        showMessage("获取数据失败！");
    }

    @Override
    public void hideFeedbackBox() {
        vgFeedbackBox.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String msg) {
        Context context = getContext();
        if (context != null) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    public void showFeedbackBox() {
        vgFeedbackBox.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btnReloadFromFailed)
    public void onReloadButtonFromFailedClick() {
        mPresenter.start();
    }

    private NodeListItem getFrontPageItem(String iconName) {
        RawNodes.NodesBean nodeBean = new RawNodes.NodesBean();
        nodeBean.setName("首页");
        nodeBean.setId(0);
        return new NodeListItem(null, nodeBean, iconName);
    }

}
