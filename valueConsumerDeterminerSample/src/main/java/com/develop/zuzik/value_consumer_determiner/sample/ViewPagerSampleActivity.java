package com.develop.zuzik.value_consumer_determiner.sample;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.develop.zuzik.value_consumer_determiner.R;
import com.develop.zuzik.value_consumer_determiner.sample.fragment.QAFragmentFactory;
import com.develop.zuzik.value_consumer_determiner.sample.fragment.StringFragmentFactory;
import com.develop.zuzik.valueconsumerdeterminer.core.builder.ValueConsumerDeterminerBuilder;
import com.develop.zuzik.valueconsumerdeterminer.core.interfaces.ValueConsumerDeterminer;
import com.develop.zuzik.valueconsumerdeterminer.pageradapter.ValueFragmentFactory;
import com.develop.zuzik.valueconsumerdeterminer.pageradapter.ValuePagerAdapter;

import java.util.Arrays;
import java.util.List;

public class ViewPagerSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_sample);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        List<Object> values = Arrays.<Object>asList(
                "String A",
                "String B",
                "String C",
                4,
                "String F");

        ValueConsumerDeterminer<Object, ValueFragmentFactory> determiner = ValueConsumerDeterminerBuilder
                .create(ValueFragmentFactory.class)
                .withDefault(new QAFragmentFactory<>())
                .withClass(String.class, new StringFragmentFactory())
                .build();

        ValuePagerAdapter<Object> adapter = new ValuePagerAdapter<>(getSupportFragmentManager(), determiner);
        adapter.setValues(values);
        viewPager.setAdapter(adapter);
    }
}
