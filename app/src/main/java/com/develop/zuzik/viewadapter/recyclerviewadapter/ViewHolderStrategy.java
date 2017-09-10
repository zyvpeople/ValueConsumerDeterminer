package com.develop.zuzik.viewadapter.recyclerviewadapter;

import android.content.Context;
import android.view.View;

import com.develop.zuzik.viewadapter.recyclerviewadapter.viewholder.ViewHolder;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

interface ViewHolderStrategy<Value> {

    int getItemViewType(int position,
                        RecyclerViewAdapterState<Value> state);

    ViewHolder<View> onCreateViewHolder(Context context,
                                        int viewType,
                                        RecyclerViewAdapterState<Value> state);

    void onBindViewHolder(ViewHolder<View> holder,
                          int position,
                          RecyclerViewAdapterState<Value> state);
}
