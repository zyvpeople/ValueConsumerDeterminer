package com.develop.zuzik.viewadapter.recyclerviewadapter;

import android.util.Log;
import android.util.Pair;

import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueViewFactory;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ViewFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class RecyclerViewAdapterBuilder<Value> {

    public static <Value> RecyclerViewAdapterBuilder<Value> create() {
        return new RecyclerViewAdapterBuilder<>();
    }

    private List<Pair<Class<Value>, ValueViewFactory<Value>>> factories = new ArrayList<>();
    private ViewHolderStrategy<Value> strategy = new EmptyViewHolderStrategy<>(new EmptyViewFactory());

    private RecyclerViewAdapterBuilder() {
    }

    public <V extends Value> RecyclerViewAdapterBuilder<Value> factory(Class<V> valueClass, ValueViewFactory<V> factory) {
        for (int i = 0; i < factories.size(); i++) {
            Pair<Class<Value>, ValueViewFactory<Value>> pair = factories.get(i);
            if (pair.first.equals(valueClass)) {
                Log.e(
                        getClass().getSimpleName(),
                        String.format(
                                "%s already has %s. %s replaced with %s",
                                valueClass.getSimpleName(),
                                ValueViewFactory.class.getSimpleName(),
                                pair.second.getClass().getSimpleName(),
                                factory.getClass().getSimpleName()));
                factories.remove(i);
                factories.add(i, new Pair<>((Class<Value>) valueClass, (ValueViewFactory<Value>) factory));
                return this;
            }
        }
        factories.add(new Pair<>((Class<Value>) valueClass, (ValueViewFactory<Value>) factory));
        return this;
    }

    public RecyclerViewAdapterBuilder<Value> throwErrorIfValueCantBeDisplayed() {
        strategy = new ErrorViewHolderStrategy<>();
        return this;
    }

    public RecyclerViewAdapterBuilder<Value> displayEmptyViewIfValueCantBeDisplayed() {
        return displayCustomViewIfValueCantBeDisplayed(new EmptyViewFactory());
    }

    public RecyclerViewAdapterBuilder<Value> displayCustomViewIfValueCantBeDisplayed(ViewFactory viewFactory) {
        strategy = new EmptyViewHolderStrategy<>(viewFactory);
        return this;
    }

    public RecyclerViewAdapter<Value> build() {
        return new RecyclerViewAdapter<>(factories, strategy);
    }
}
