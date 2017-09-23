package com.develop.zuzik.valueconsumerdeterminer.core.interfaces;

/**
 * Created by yaroslavzozulia on 9/12/17.
 */

public interface Predicate<Value> {
    boolean isSatisfied(Value value);
}
