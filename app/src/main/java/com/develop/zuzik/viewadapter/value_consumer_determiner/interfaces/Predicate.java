package com.develop.zuzik.viewadapter.value_consumer_determiner.interfaces;

/**
 * Created by yaroslavzozulia on 9/12/17.
 */

public interface Predicate<Value> {
    boolean isSatisfied(Value value);
}
