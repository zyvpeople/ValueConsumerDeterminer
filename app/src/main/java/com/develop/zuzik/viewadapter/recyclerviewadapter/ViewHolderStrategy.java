package com.develop.zuzik.viewadapter.recyclerviewadapter;

import android.content.Context;
import android.util.Pair;
import android.view.View;

import com.develop.zuzik.viewadapter.recyclerviewadapter.viewholder.ViewHolder;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueViewFactory;

import java.util.List;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

interface ViewHolderStrategy<Value> {

    int getItemViewType(int position,
                        List<Value> values,
                        List<Pair<Class<Value>, ValueViewFactory<Value>>> factories);

    ViewHolder<View> onCreateViewHolder(Context context,
                                        int viewType,
                                        List<Pair<Class<Value>, ValueViewFactory<Value>>> factories);

    void onBindViewHolder(ViewHolder<View> holder,
                          int position,
                          List<Value> values,
                          List<Pair<Class<Value>, ValueViewFactory<Value>>> factories);
}
