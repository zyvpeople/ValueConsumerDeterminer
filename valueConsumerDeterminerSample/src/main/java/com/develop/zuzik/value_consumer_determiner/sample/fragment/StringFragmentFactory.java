package com.develop.zuzik.value_consumer_determiner.sample.fragment;

import android.support.v4.app.Fragment;

import com.develop.zuzik.valueconsumerdeterminer.pageradapter.ValueFragmentFactory;

/**
 * Created by yaroslavzozulia on 9/24/17.
 */

public class StringFragmentFactory implements ValueFragmentFactory<String> {

    @Override
    public Fragment create(String value) {
        return StringFragment.newInstance(value);
    }
}
