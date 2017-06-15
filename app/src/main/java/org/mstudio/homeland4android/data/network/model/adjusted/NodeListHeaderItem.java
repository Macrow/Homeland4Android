package org.mstudio.homeland4android.data.network.model.adjusted;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.ui.nodes.NodeHeaderViewHolder;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractHeaderItem;

/**
 * Created by macrow on 2017/6/8.
 */

public class NodeListHeaderItem extends AbstractHeaderItem<NodeHeaderViewHolder> {

    private String mSectionName;

    public NodeListHeaderItem(String name) {
        mSectionName = name;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_node_header;
    }

    @Override
    public NodeHeaderViewHolder createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        return new NodeHeaderViewHolder(inflater.inflate(getLayoutRes(), parent, false), adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, NodeHeaderViewHolder holder, int position, List payloads) {
        holder.tvSectionName.setText(mSectionName);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof NodeListHeaderItem) {
            return mSectionName.equals(((NodeListHeaderItem) o).mSectionName);
        }
        return false;
    }
}
