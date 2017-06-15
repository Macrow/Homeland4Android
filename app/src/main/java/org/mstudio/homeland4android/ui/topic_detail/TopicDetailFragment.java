package org.mstudio.homeland4android.ui.topic_detail;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.data.network.model.adjusted.ProgressItem;
import org.mstudio.homeland4android.data.network.model.adjusted.ReplyListItem;
import org.mstudio.homeland4android.data.network.model.adjusted.TopicAndReplyListItem;
import org.mstudio.homeland4android.data.network.model.adjusted.TopicDetailItem;
import org.mstudio.homeland4android.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager;
import eu.davidea.flexibleadapter.items.IFlexible;

public class TopicDetailFragment extends BaseFragment implements TopicDetailContract.View {

    private List<IFlexible> mTopicAndReplyList = new ArrayList<>();
    private FlexibleAdapter<IFlexible> mTopicAndReplyListAdapter;

    private TopicDetailContract.Presenter mPresenter;

    @BindView(R.id.swlContainer)
    SwipeRefreshLayout swlContainer;
    @BindView(R.id.rvTopicAndReplyList)
    RecyclerView rvTopicAndReplyList;

    public TopicDetailFragment() {}

    public static TopicDetailFragment newInstance() {
        return new TopicDetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_topic_detail, container, false);
        ButterKnife.bind(this, root);
        initBoxFeedback(root);
        initRecyclerView();
        mPresenter.start();
        return root;
    }

    @Override
    public void onReloadButtonFromFailedClick() {
        mPresenter.start();
    }

    @Override
    public void setPresenter(TopicDetailContract.Presenter presenter) {
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
    public void loadTopicAndReplyList(TopicAndReplyListItem topicAndReplyListItem) {
        mTopicAndReplyList.clear();
        mTopicAndReplyList.add(topicAndReplyListItem.mTopicDetailItem);
        mTopicAndReplyList.addAll(topicAndReplyListItem.mReplyListItem);
        mTopicAndReplyListAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadReplyList(List<ReplyListItem> replyListItems) {
        mTopicAndReplyList.addAll(replyListItems);
        mTopicAndReplyListAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        mTopicAndReplyListAdapter = new FlexibleAdapter(mTopicAndReplyList);
        rvTopicAndReplyList.setAdapter(mTopicAndReplyListAdapter);
        rvTopicAndReplyList.setLayoutManager(new SmoothScrollLinearLayoutManager(getContext()));
        rvTopicAndReplyList.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).build());
        swlContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadTopicAndReplyList();
            }
        });
        mTopicAndReplyListAdapter.setEndlessScrollListener(new FlexibleAdapter.EndlessScrollListener() {
            @Override
            public void noMoreLoad(int newItemsSize) {}

            @Override
            public void onLoadMore(int lastPosition, int currentPage) {
                mPresenter.loadReplyList();
                mTopicAndReplyListAdapter.onLoadMoreComplete(null, 500);
            }
        }, new ProgressItem());
    }

    @Override
    public void initFavoriteAndFollowingButtonStatus(boolean isFavorite, boolean isLike, boolean isFollowing) {
        ((TopicDetailActivity) getActivity()).initFavoriteAndFollowingButtonStatus(isFavorite, isLike, isFollowing);
    }

    public void updateLikeCount(int count) {
        TopicDetailItem topicDetailItem = (TopicDetailItem) mTopicAndReplyList.get(0);
        topicDetailItem.mTopicItem.setLikesCount(count);
        mTopicAndReplyListAdapter.notifyItemChanged(0);
    }

    @Override
    public void hideFeedbackBox() {
        super.hideFeedbackBox();
        swlContainer.setRefreshing(false);
    }
}
