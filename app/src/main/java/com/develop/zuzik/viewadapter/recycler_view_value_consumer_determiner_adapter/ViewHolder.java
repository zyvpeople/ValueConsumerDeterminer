package com.develop.zuzik.viewadapter.recycler_view_value_consumer_determiner_adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class ViewHolder<V extends View> extends RecyclerView.ViewHolder {
    public final V view;

    public ViewHolder(V view) {
        super(view);
        this.view = view;
    }
}
