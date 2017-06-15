package org.mstudio.homeland4android.ui.notifications;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.data.network.model.adjusted.NotificationListItem;
import org.mstudio.homeland4android.data.network.model.adjusted.ProgressItem;
import org.mstudio.homeland4android.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager;
import eu.davidea.flexibleadapter.items.IFlexible;

public class NotificationsFragment extends BaseFragment implements NotificationsContract.View {

    private List<NotificationListItem> mNotificationList = new ArrayList<>();
    private FlexibleAdapter<IFlexible> mNotificationListAdapter;

    private NotificationsContract.Presenter mPresenter;

    @BindView(R.id.swlContainer)
    SwipeRefreshLayout swlContainer;
    @BindView(R.id.rvNotificationList)
    RecyclerView rvNotificationList;

    public NotificationsFragment() {}

    public static NotificationsFragment newInstance() {
        return new NotificationsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
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
    public void onReloadButtonFromFailedClick() {
        mPresenter.start();
    }

    @Override
    public void setPresenter(NotificationsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void finishActivity() {
        getActivity().finish();
    }

    private void initRecyclerView() {
        mNotificationListAdapter = new FlexibleAdapter(mNotificationList);
        rvNotificationList.setAdapter(mNotificationListAdapter);
        rvNotificationList.setLayoutManager(new SmoothScrollLinearLayoutManager(getContext()));
        rvNotificationList.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).build());
        swlContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadNotificationList(true);
            }
        });
        mNotificationListAdapter.setEndlessScrollListener(new FlexibleAdapter.EndlessScrollListener() {
            @Override
            public void noMoreLoad(int newItemsSize) {}

            @Override
            public void onLoadMore(int lastPosition, int currentPage) {
                mPresenter.loadNotificationList(false);
                mNotificationListAdapter.onLoadMoreComplete(null, 500);
            }
        }, new ProgressItem());
    }

    @Override
    public void loadNotificationList(List<NotificationListItem> notifications, boolean reload) {
        if (reload) {
            mNotificationList.clear();
            if (notifications.size() > 0) {
                swlContainer.setVisibility(View.VISIBLE);
            } else {
                swlContainer.setVisibility(View.GONE);
            }
        }
        mNotificationList.addAll(notifications);
        mNotificationListAdapter.notifyDataSetChanged();
    }

    @Override
    public void clearAllNotifications() {
        mNotificationList.clear();
        swlContainer.setVisibility(View.GONE);
    }

    @Override
    public void hideFeedbackBox() {
        super.hideFeedbackBox();
        swlContainer.setRefreshing(false);
    }
}
