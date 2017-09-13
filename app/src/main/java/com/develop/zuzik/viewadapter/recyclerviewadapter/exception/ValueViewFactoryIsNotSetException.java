package com.develop.zuzik.viewadapter.recyclerviewadapter.exception;

import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueViewFactory;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class ValueViewFactoryIsNotSetException extends RuntimeException {

    public ValueViewFactoryIsNotSetException(Object valueObject) {
        super(String.format(
                "%s for %s is not set",
                ValueViewFactory.class.getSimpleName(),
                valueObject));
    }
}
