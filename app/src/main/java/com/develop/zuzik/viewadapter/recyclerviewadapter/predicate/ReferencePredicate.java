package com.develop.zuzik.viewadapter.recyclerviewadapter.predicate;

import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.Predicate;

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
