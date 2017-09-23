package com.develop.zuzik.valueconsumerdeterminer.pageradapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.develop.zuzik.valueconsumerdeterminer.core.interfaces.ValueConsumerDeterminer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaroslavzozulia on 9/23/17.
 */

public class ValuePagerAdapter<Value> extends FragmentStatePagerAdapter {

    private final List<Value> values = new ArrayList<>();
    private final PagerAdapterValueConsumerDeterminer<Value> determiner;

    public ValuePagerAdapter(FragmentManager fm,
                             ValueConsumerDeterminer<Value, ValueFragmentFactory> determiner) {
        super(fm);
        this.determiner = new PagerAdapterValueConsumerDeterminer<>(determiner);
    }

    public void setValues(List<Value> values) {
        this.values.clear();
        this.values.addAll(values);
    }

    @Override
    public Fragment getItem(int position) {
        return determiner.getItem(position, values);
    }

    @Override
    public int getCount() {
        return values.size();
    }
}
