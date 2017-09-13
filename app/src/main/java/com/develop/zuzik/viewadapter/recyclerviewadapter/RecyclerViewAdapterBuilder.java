package com.develop.zuzik.viewadapter.recyclerviewadapter;

import android.util.Pair;

import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.Predicate;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueViewFactory;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ViewFactory;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ViewHolderStrategy;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ViewHolderStrategyFactory;
import com.develop.zuzik.viewadapter.recyclerviewadapter.predicate.ClassPredicate;
import com.develop.zuzik.viewadapter.recyclerviewadapter.predicate.EqualityPredicate;
import com.develop.zuzik.viewadapter.recyclerviewadapter.predicate.ReferencePredicate;
import com.develop.zuzik.viewadapter.recyclerviewadapter.strategy.EmptyViewHolderStrategy;
import com.develop.zuzik.viewadapter.recyclerviewadapter.strategy.ErrorViewHolderStrategy;
import com.develop.zuzik.viewadapter.recyclerviewadapter.strategy.PredicateViewHolderStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */
public class RecyclerViewAdapterBuilder<Value> {

    public static <Value> RecyclerViewAdapterBuilder<Value> create() {
        return new RecyclerViewAdapterBuilder<>();
    }

    private ViewHolderStrategyFactory<Value> factory = new ViewHolderStrategyFactory<Value>() {
        @Override
        public ViewHolderStrategy<Value> create(ViewHolderStrategy<Value> strategy) {
            return new EmptyViewHolderStrategy<>(new EmptyViewFactory(), strategy);
        }
    };
    private final List<Pair<Predicate<Value>, ValueViewFactory<Value>>> factories = new ArrayList<>();

    private RecyclerViewAdapterBuilder() {
    }

    public <V extends Value> RecyclerViewAdapterBuilder<Value> viewForClass(Class<V> valueClass, ValueViewFactory<V> factory) {
        factories.add(new Pair<Predicate<Value>, ValueViewFactory<Value>>(new ClassPredicate<Value>((Class<Value>) valueClass), (ValueViewFactory<Value>) factory));
        return this;
    }

    public <V extends Value> RecyclerViewAdapterBuilder<Value> viewForEquality(V value, ValueViewFactory<V> factory) {
        factories.add(new Pair<Predicate<Value>, ValueViewFactory<Value>>(new EqualityPredicate<Value>(value), (ValueViewFactory<Value>) factory));
        return this;
    }

    public <V extends Value> RecyclerViewAdapterBuilder<Value> viewForReference(V reference, ValueViewFactory<V> factory) {
        factories.add(new Pair<Predicate<Value>, ValueViewFactory<Value>>(new ReferencePredicate<Value>(reference), (ValueViewFactory<Value>) factory));
        return this;
    }

    public RecyclerViewAdapterBuilder<Value> throwErrorIfValueCantBeDisplayed() {
        factory = new ViewHolderStrategyFactory<Value>() {
            @Override
            public ViewHolderStrategy<Value> create(ViewHolderStrategy<Value> strategy) {
                return new ErrorViewHolderStrategy<>(strategy);
            }
        };
        return this;
    }

    public RecyclerViewAdapterBuilder<Value> displayEmptyViewIfValueCantBeDisplayed() {
        return displayCustomViewIfValueCantBeDisplayed(new EmptyViewFactory());
    }

    public RecyclerViewAdapterBuilder<Value> displayCustomViewIfValueCantBeDisplayed(final ViewFactory viewFactory) {
        factory = new ViewHolderStrategyFactory<Value>() {
            @Override
            public ViewHolderStrategy<Value> create(ViewHolderStrategy<Value> strategy) {
                return new EmptyViewHolderStrategy<>(viewFactory, strategy);
            }
        };
        return this;
    }

    public RecyclerViewAdapter<Value> build() {
        return new RecyclerViewAdapter<>(factory.create(new PredicateViewHolderStrategy<Value>(factories)));
    }
}
