package org.mstudio.homeland4android.ui.user_user_list;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.data.network.model.adjusted.ProgressItem;
import org.mstudio.homeland4android.data.network.model.adjusted.UserListItem;
import org.mstudio.homeland4android.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.common.SmoothScrollGridLayoutManager;
import eu.davidea.flexibleadapter.items.IFlexible;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/14
 * desc   :
 */

public class UserUserListFragment extends BaseFragment implements UserUserListContract.View {

    private List<UserListItem> mUserList = new ArrayList<>();
    private FlexibleAdapter<IFlexible> mUserListAdapter;

    private UserUserListContract.Presenter mPresenter;

    @BindView(R.id.swlContainer)
    SwipeRefreshLayout swlContainer;
    @BindView(R.id.rvUserList)
    RecyclerView rvUserList;

    public UserUserListFragment() {}

    public static UserUserListFragment newInstance() {
        return new UserUserListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user_list, container, false);
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
    public void loadUserList(List<UserListItem> Users, boolean reload) {
        if (reload) {
            mUserList.clear();
            if (Users.size() > 0) {
                swlContainer.setVisibility(View.VISIBLE);
            } else {
                swlContainer.setVisibility(View.GONE);
            }
        }
        mUserList.addAll(Users);
        mUserListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onReloadButtonFromFailedClick() {
        mPresenter.start();
    }

    @Override
    public void setPresenter(UserUserListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private void initRecyclerView() {
        mUserListAdapter = new FlexibleAdapter(mUserList);
        rvUserList.setAdapter(mUserListAdapter);
        rvUserList.setLayoutManager(new SmoothScrollGridLayoutManager(getContext(), 4));
        swlContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadUserList(true);
            }
        });
        mUserListAdapter.setEndlessScrollListener(new FlexibleAdapter.EndlessScrollListener() {
            @Override
            public void noMoreLoad(int newItemsSize) {}

            @Override
            public void onLoadMore(int lastPosition, int currentPage) {
                mPresenter.loadUserList(false);
                mUserListAdapter.onLoadMoreComplete(null, 500);
            }
        }, new ProgressItem());
    }

    @Override
    public void hideFeedbackBox() {
        super.hideFeedbackBox();
        swlContainer.setRefreshing(false);
    }
}
