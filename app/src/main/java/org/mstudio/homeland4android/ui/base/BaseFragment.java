package org.mstudio.homeland4android.ui.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.mstudio.homeland4android.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/04
 * desc   :
 */
public abstract class BaseFragment extends Fragment {

    @BindView(R.id.llFeedbackBox) ViewGroup vgFeedbackBox;
    @BindView(R.id.pbLoading) View vLoading;
    @BindView(R.id.llFailedBox) View vFailedBox;

    protected void initBoxFeedback(View root) {
        ButterKnife.bind(this, root);
    }

    public void showLoading() {
        showFeedbackBox();
        vFailedBox.setVisibility(View.GONE);
        vLoading.setVisibility(View.VISIBLE);
    }

    public void showError() {
        showFeedbackBox();
        vLoading.setVisibility(View.GONE);
        vFailedBox.setVisibility(View.VISIBLE);
        showMessage("获取数据失败！");
    }

    public void hideFeedbackBox() {
        vgFeedbackBox.setVisibility(View.GONE);
    }

    public void showMessage(String msg) {
        Context context = getContext();
        if (context != null) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    private void showFeedbackBox() {
        vgFeedbackBox.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btnReloadFromFailed)
    public abstract void onReloadButtonFromFailedClick();
}
