package org.mstudio.homeland4android.data.network.model.adjusted;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.data.network.model.raw.RawNodes;
import org.mstudio.homeland4android.ui.nodes.NodeViewHolder;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractSectionableItem;
import eu.davidea.flexibleadapter.utils.DrawableUtils;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/07
 * desc   :
 */
public class NodeListItem extends AbstractSectionableItem<NodeViewHolder, NodeListHeaderItem> implements Comparable<NodeListItem> {

    public RawNodes.NodesBean mNodeItem;
    public String mIconName;

    public NodeListItem(NodeListHeaderItem headerItem, RawNodes.NodesBean nodesBean, String iconName) {
        super(headerItem);
        mNodeItem = nodesBean;
        mIconName = iconName;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof NodeListItem) {
            return mNodeItem.getName().equals(((NodeListItem) o).mNodeItem.getName());
        }
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_node;
    }

    @Override
    public NodeViewHolder createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        return new NodeViewHolder(inflater.inflate(getLayoutRes(), parent, false), adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, NodeViewHolder holder, int position, List payloads) {
        holder.tvNodeName.setText(mNodeItem.getName());
        holder.tvNodeDescription.setText(mNodeItem.getSummary());
        holder.iiIcon.setIcon(mIconName);

        Drawable drawable = DrawableUtils.getSelectableBackgroundCompat(
                Color.WHITE, Color.LTGRAY, Color.LTGRAY);
        DrawableUtils.setBackgroundCompat(holder.itemView, drawable);
    }

    @Override
    public int compareTo(@NonNull NodeListItem o) {
        int section1 = mNodeItem.getSectionId();
        int section2 = o.mNodeItem.getSectionId();
        int sort1 = mNodeItem.getSort();
        int sort2 = o.mNodeItem.getSort();

        if (section1 > section2) {
            return 1;
        } else if (section1 < section2) {
            return -1;
        } else {
            if (sort1 > sort2) {
                return 1;
            } else if (sort1 == sort2) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}