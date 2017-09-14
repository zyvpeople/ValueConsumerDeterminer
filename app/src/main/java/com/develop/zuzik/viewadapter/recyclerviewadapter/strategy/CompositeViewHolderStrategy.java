package com.develop.zuzik.viewadapter.recyclerviewadapter.strategy;

import android.content.Context;
import android.view.View;

import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ViewHolderStrategy;
import com.develop.zuzik.viewadapter.recyclerviewadapter.view.EmptyView;
import com.develop.zuzik.viewadapter.recyclerviewadapter.viewholder.ViewHolder;

import java.util.List;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */
public class CompositeViewHolderStrategy<Value> implements ViewHolderStrategy<Value> {

    private final List<ViewHolderStrategy<Value>> strategies;

    public CompositeViewHolderStrategy(List<ViewHolderStrategy<Value>> strategies) {
        this.strategies = strategies;
    }

    @Override
    public int getViewTypesCount() {
        int count = 0;
        for (ViewHolderStrategy<Value> strategy : strategies) {
            count += strategy.getViewTypesCount();
        }
        return count + 1;
    }

    @Override
    public int findItemViewType(int position, List<Value> values) {
        int type = -1;
        for (ViewHolderStrategy<Value> strategy : strategies) {
            type = strategy.findItemViewType(position, values);
            if (type != -1) {
                break;
            }
        }
        return type;
    }

    @Override
    public ViewHolder<View> onCreateViewHolder(Context context, int viewType, List<Value> values) {
        int count = 0;
        for (ViewHolderStrategy<Value> strategy : strategies) {
            count += strategy.getViewTypesCount();
            if (viewType < count) {
                int type = count - viewType;
                return strategy.onCreateViewHolder(context, type, values);
            }
        }
        return new ViewHolder<>((View) new EmptyView<Value>(context));
    }
}
