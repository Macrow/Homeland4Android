package org.mstudio.homeland4android.ui.user_topic_list;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.data.network.model.adjusted.ProgressItem;
import org.mstudio.homeland4android.data.network.model.adjusted.TopicListItem;
import org.mstudio.homeland4android.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.common.SmoothScrollGridLayoutManager;
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager;
import eu.davidea.flexibleadapter.items.IFlexible;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/04
 * desc   :
 */

public class UserTopicListFragment extends BaseFragment implements UserTopicListContract.View {

    private List<TopicListItem> mTopicList = new ArrayList<>();
    private FlexibleAdapter<IFlexible> mTopicListAdapter;

    private UserTopicListContract.Presenter mPresenter;

    @BindView(R.id.swlContainer)
    SwipeRefreshLayout swlContainer;
    @BindView(R.id.rvTopicList)
    RecyclerView rvTopicList;

    public UserTopicListFragment() {}

    public static UserTopicListFragment newInstance() {
        return new UserTopicListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_topics, container, false);
        ButterKnife.bind(this, root);
        initBoxFeedback(root);
        initRecyclerView();
        mPresenter.start();
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.finish();
    }

    @Override
    public void finishActivity() {
        getActivity().finish();
    }

    @Override
    public void loadTopicList(List<TopicListItem> topics, boolean reload) {
        if (reload) {
            mTopicList.clear();
            if (topics.size() > 0) {
                swlContainer.setVisibility(View.VISIBLE);
            } else {
                swlContainer.setVisibility(View.GONE);
            }
        }
        mTopicList.addAll(topics);
        mTopicListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onReloadButtonFromFailedClick() {
        mPresenter.start();
    }

    @Override
    public void setPresenter(UserTopicListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private void initRecyclerView() {
        mTopicListAdapter = new FlexibleAdapter(mTopicList);
        rvTopicList.setAdapter(mTopicListAdapter);
        rvTopicList.setLayoutManager(new SmoothScrollLinearLayoutManager(getContext()));
        rvTopicList.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).build());
        swlContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadTopicList(true);
            }
        });
        mTopicListAdapter.setEndlessScrollListener(new FlexibleAdapter.EndlessScrollListener() {
            @Override
            public void noMoreLoad(int newItemsSize) {}

            @Override
            public void onLoadMore(int lastPosition, int currentPage) {
                mPresenter.loadTopicList(false);
                mTopicListAdapter.onLoadMoreComplete(null, 500);
            }
        }, new ProgressItem());
    }

    @Override
    public void hideFeedbackBox() {
        super.hideFeedbackBox();
        swlContainer.setRefreshing(false);
    }
}
