package com.develop.zuzik.viewadapter.value_consumer_determiner.predicate;

import com.develop.zuzik.viewadapter.value_consumer_determiner.interfaces.Predicate;

/**
 * Created by yaroslavzozulia on 9/12/17.
 */

public class ClassPredicate<Value> implements Predicate<Value> {

    private final Class<Value> valueClass;

    public ClassPredicate(Class<Value> valueClass) {
        this.valueClass = valueClass;
    }

    @Override
    public boolean isSatisfied(Value value) {
        return valueClass.equals(value.getClass());
    }
}
