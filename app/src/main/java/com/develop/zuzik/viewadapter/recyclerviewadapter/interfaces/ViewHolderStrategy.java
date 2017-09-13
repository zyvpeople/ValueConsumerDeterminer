package com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces;

import android.content.Context;
import android.view.View;

import com.develop.zuzik.viewadapter.recyclerviewadapter.viewholder.ViewHolder;

import java.util.List;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public interface ViewHolderStrategy<Value> {

    int getViewTypesCount();

    int findItemViewType(int position,
                         List<Value> values);

    ViewHolder<View> onCreateViewHolder(Context context,
                                        int viewType,
                                        List<Value> values);

    void onBindViewHolder(ViewHolder<View> holder,
                          int position,
                          List<Value> values);
}
