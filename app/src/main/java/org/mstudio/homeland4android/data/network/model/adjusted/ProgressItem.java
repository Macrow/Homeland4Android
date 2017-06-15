package org.mstudio.homeland4android.data.network.model.adjusted;

import android.animation.Animator;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import org.mstudio.homeland4android.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.Payload;
import eu.davidea.flexibleadapter.helpers.AnimatorHelper;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.viewholders.FlexibleViewHolder;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/05
 * desc   :
 */
public class ProgressItem extends AbstractFlexibleItem<ProgressItem.ProgressViewHolder> {

    private StatusEnum status = StatusEnum.MORE_TO_LOAD;

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_progress;
    }

    @Override
    public ProgressViewHolder createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        return new ProgressViewHolder(inflater.inflate(getLayoutRes(), parent, false), adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, ProgressViewHolder holder,
                               int position, List payloads) {

        holder.progressBar.setVisibility(View.GONE);
        holder.progressMessage.setVisibility(View.VISIBLE);

        if (!adapter.isEndlessScrollEnabled()) {
            setStatus(StatusEnum.DISABLE_ENDLESS);
        } else if (payloads.contains(Payload.NO_MORE_LOAD)) {
            setStatus(StatusEnum.NO_MORE_LOAD);
        }

        switch (this.status) {
            case NO_MORE_LOAD:
                holder.progressMessage.setText("加载完毕");
                // Reset to default status for next binding
                setStatus(StatusEnum.MORE_TO_LOAD);
                break;
            case DISABLE_ENDLESS:
                holder.progressMessage.setText("已禁止");
                break;
            case ON_CANCEL:
                holder.progressMessage.setText("已取消");
                // Reset to default status for next binding
                setStatus(StatusEnum.MORE_TO_LOAD);
                break;
            case ON_ERROR:
                holder.progressMessage.setText("发生错误");
                // Reset to default status for next binding
                setStatus(StatusEnum.MORE_TO_LOAD);
                break;
            default:
                holder.progressBar.setVisibility(View.VISIBLE);
                holder.progressMessage.setVisibility(View.GONE);
                break;
        }
    }

    static class ProgressViewHolder extends FlexibleViewHolder {

        @BindView(R.id.progress_bar)
        AVLoadingIndicatorView progressBar;
        @BindView(R.id.progress_message)
        TextView progressMessage;

        ProgressViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            ButterKnife.bind(this, view);
        }
        @Override
        public void scrollAnimators(@NonNull List<Animator> animators, int position, boolean isForward) {
            AnimatorHelper.scaleAnimator(animators, itemView, 0f);
        }
    }

    public enum StatusEnum {
        MORE_TO_LOAD, //Default = should have an empty Payload
        DISABLE_ENDLESS, //Endless is disabled because user has set limits
        NO_MORE_LOAD, //Non-empty Payload = Payload.NO_MORE_LOAD
        ON_CANCEL,
        ON_ERROR
    }

}