package com.develop.zuzik.valueconsumerdeterminer.core.predicate;

import com.develop.zuzik.valueconsumerdeterminer.core.interfaces.Predicate;

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
