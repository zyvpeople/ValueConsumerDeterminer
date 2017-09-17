package com.develop.zuzik.viewadapter.value_consumer_determiner.predicate;

import com.develop.zuzik.viewadapter.value_consumer_determiner.interfaces.Predicate;

/**
 * Created by yaroslavzozulia on 9/12/17.
 */

public class EqualityPredicate<Value> implements Predicate<Value> {

    private final Value value;

    public EqualityPredicate(Value value) {
        this.value = value;
    }

    @Override
    public boolean isSatisfied(Value value) {
        return this.value.equals(value);
    }
}
