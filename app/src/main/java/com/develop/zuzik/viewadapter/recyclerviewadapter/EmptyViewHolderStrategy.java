package com.develop.zuzik.viewadapter.recyclerviewadapter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueView;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueViewFactory;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ViewFactory;
import com.develop.zuzik.viewadapter.recyclerviewadapter.viewholder.ViewHolder;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

class EmptyViewHolderStrategy<Value> implements ViewHolderStrategy<Value> {

    private static final int NOT_EXISTED_VIEW_TYPE = -1;
    private final ViewFactory factory;

    public EmptyViewHolderStrategy(ViewFactory factory) {
        this.factory = factory;
    }

    @Override
    public int getItemViewType(int position,
                               RecyclerViewAdapterState<Value> state) {
        int factoryPosition = state.findFactoryPositionForValueAtPosition(position);
        if (factoryPosition == NOT_EXISTED_VIEW_TYPE) {
            Log.e(
                    getClass().getSimpleName(),
                    String.format(
                            "%s for %s is not set",
                            ValueViewFactory.class.getSimpleName(),
                            state.values.get(position).getClass().getSimpleName()));
        }
        return factoryPosition;
    }

    @Override
    public ViewHolder<View> onCreateViewHolder(Context context,
                                               int viewType,
                                               RecyclerViewAdapterState<Value> state) {
        return new ViewHolder<>(viewType == NOT_EXISTED_VIEW_TYPE
                ? factory.create(context)
                : (View) state.factories.get(viewType).second.create(context));
    }

    @Override
    public void onBindViewHolder(ViewHolder<View> holder,
                                 int position,
                                 RecyclerViewAdapterState<Value> state) {
        if (getItemViewType(position, state) != NOT_EXISTED_VIEW_TYPE) {
            ((ValueView<Value>) holder.view).setValue(state.values.get(position));
        }
    }
}