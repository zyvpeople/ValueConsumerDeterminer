package com.develop.zuzik.viewadapter.recyclerviewadapter;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class ValueViewFactoryIsNotSet extends RuntimeException {

    public ValueViewFactoryIsNotSet(Class valueClass) {
        super(String.format(
                "%s for %s is not set",
                ValueViewFactory.class.getSimpleName(),
                valueClass.getSimpleName()));
    }
}
