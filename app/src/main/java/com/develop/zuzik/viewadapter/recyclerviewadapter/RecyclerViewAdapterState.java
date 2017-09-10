package com.develop.zuzik.viewadapter.recyclerviewadapter;

import android.util.Pair;

import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueViewFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaroslavzozulia on 9/11/17.
 */

class RecyclerViewAdapterState<Value> {

    public final List<Value> values = new ArrayList<>();
    public final List<Pair<Class<Value>, ValueViewFactory<Value>>> factories;

    public RecyclerViewAdapterState(List<Pair<Class<Value>, ValueViewFactory<Value>>> factories) {
        this.factories = factories;
    }

    public void setValues(List<Value> values) {
        this.values.clear();
        this.values.addAll(values);
    }

    public int findFactoryPositionForValueAtPosition(int position) {
        Class<?> valueClass = values.get(position).getClass();
        for (int i = 0; i < factories.size(); i++) {
            if (factories.get(i).first.equals(valueClass)) {
                return i;
            }
        }
        return -1;
    }
}
