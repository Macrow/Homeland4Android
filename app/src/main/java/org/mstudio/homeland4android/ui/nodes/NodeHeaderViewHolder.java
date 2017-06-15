package org.mstudio.homeland4android.ui.nodes;

import android.view.View;
import android.widget.TextView;

import org.mstudio.homeland4android.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.viewholders.FlexibleViewHolder;

/**
 * Created by macrow on 2017/6/8.
 */

public class NodeHeaderViewHolder extends FlexibleViewHolder {

    public @BindView(R.id.tvSectionName)
    TextView tvSectionName;

    public NodeHeaderViewHolder(View view, FlexibleAdapter adapter) {
        super(view, adapter, true);
        ButterKnife.bind(this, view);
    }

}