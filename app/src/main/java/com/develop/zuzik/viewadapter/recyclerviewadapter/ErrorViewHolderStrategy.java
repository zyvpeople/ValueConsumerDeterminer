package com.develop.zuzik.viewadapter.recyclerviewadapter;

import android.content.Context;
import android.view.View;

import com.develop.zuzik.viewadapter.recyclerviewadapter.exception.ValueViewFactoryIsNotSetException;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueView;
import com.develop.zuzik.viewadapter.recyclerviewadapter.viewholder.ViewHolder;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

class ErrorViewHolderStrategy<Value> implements ViewHolderStrategy<Value> {

    @Override
    public int getItemViewType(int position,
                               RecyclerViewAdapterState<Value> state) {
        int factoryPosition = state.findFactoryPositionForValueAtPosition(position);
        if (factoryPosition != -1) {
            return factoryPosition;
        } else {
            throw new ValueViewFactoryIsNotSetException(state.values.get(position).getClass());
        }
    }

    @Override
    public ViewHolder<View> onCreateViewHolder(Context context,
                                               int viewType,
                                               RecyclerViewAdapterState<Value> state) {
        return new ViewHolder<>((View) state.factories.get(viewType).second.create(context));
    }

    @Override
    public void onBindViewHolder(ViewHolder<View> holder,
                                 int position,
                                 RecyclerViewAdapterState<Value> state) {
        ((ValueView<Value>) holder.view).setValue(state.values.get(position));
    }
}
