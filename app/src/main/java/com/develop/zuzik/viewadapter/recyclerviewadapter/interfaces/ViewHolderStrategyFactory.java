package com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces;

/**
 * Created by yaroslavzozulia on 9/13/17.
 */

public interface ViewHolderStrategyFactory<Value> {
    ViewHolderStrategy<Value> create(ViewHolderStrategy<Value> strategy);
}
