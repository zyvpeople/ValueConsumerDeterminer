package com.develop.zuzik.valueconsumerdeterminer.core.predicate;

import com.develop.zuzik.valueconsumerdeterminer.core.interfaces.Predicate;

/**
 * Created by yaroslavzozulia on 9/12/17.
 */

public class ReferencePredicate<Value> implements Predicate<Value> {

    private final Value reference;

    public ReferencePredicate(Value reference) {
        this.reference = reference;
    }

    @Override
    public boolean isSatisfied(Value value) {
        return reference == value;
    }
}
