package com.develop.zuzik.value_consumer_determiner.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.develop.zuzik.value_consumer_determiner.R;
import com.develop.zuzik.value_consumer_determiner.sample.value.BooleanMutableValue;
import com.develop.zuzik.value_consumer_determiner.sample.value.IntValue;
import com.develop.zuzik.value_consumer_determiner.sample.value.StringMutableValue;
import com.develop.zuzik.value_consumer_determiner.sample.value.TextValue;
import com.develop.zuzik.value_consumer_determiner.sample.valueview.BooleanMutableValueViewFactory;
import com.develop.zuzik.value_consumer_determiner.sample.valueview.IntLeftViewFactory;
import com.develop.zuzik.value_consumer_determiner.sample.valueview.IntRightViewFactory;
import com.develop.zuzik.value_consumer_determiner.sample.valueview.IntValueViewFactory;
import com.develop.zuzik.value_consumer_determiner.sample.valueview.QAViewFactory;
import com.develop.zuzik.value_consumer_determiner.sample.valueview.StringMutableValueViewFactory;
import com.develop.zuzik.value_consumer_determiner.sample.valueview.TextValueViewFactory;
import com.develop.zuzik.valueconsumerdeterminer.core.builder.ValueConsumerDeterminerBuilder;
import com.develop.zuzik.valueconsumerdeterminer.core.interfaces.ValueConsumer;
import com.develop.zuzik.valueconsumerdeterminer.core.interfaces.ValueConsumerDeterminer;
import com.develop.zuzik.valueconsumerdeterminer.recyclerview.ValueRecyclerViewAdapter;
import com.develop.zuzik.valueconsumerdeterminer.recyclerview.ValueViewFactory;
import com.develop.zuzik.valueconsumerdeterminer.viewgroup.ViewGroupValueConsumerDeterminer;

import java.util.Arrays;
import java.util.List;

public class ViewGroupSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group_sample);

        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.linearLayout);

        IntValue intValue2 = new IntValue(2);
        List<Object> values = Arrays.asList(
                new TextValue("Alpha"),
                new TextValue("Beta"),
                new TextValue("Gamma"),
                new IntValue(1),
                intValue2,
                new IntValue(3),
                new StringMutableValue(""),
                new StringMutableValue("1"),
                new StringMutableValue("2"),
                new StringMutableValue("3"),
                new StringMutableValue("4"),
                new BooleanMutableValue(false),
                new BooleanMutableValue(true),
                new BooleanMutableValue(false),
                new BooleanMutableValue(true),
                new BooleanMutableValue(false),
                new BooleanMutableValue(true),
                new BooleanMutableValue(false),
                new BooleanMutableValue(true),
                new BooleanMutableValue(false),
                new BooleanMutableValue(true),
                new BooleanMutableValue(false),
                new BooleanMutableValue(true),
                new BooleanMutableValue(false),
                new BooleanMutableValue(true),
                new BooleanMutableValue(false),
                2,
                3);

        ValueConsumerDeterminerBuilder<Object, ValueViewFactory> intValueBuilder = ValueConsumerDeterminerBuilder
                .create(ValueViewFactory.class)
                .withEquality(2, new IntRightViewFactory())
                .withClass(Integer.class, new IntLeftViewFactory())
                .withReference(intValue2, new IntValueViewFactory())
                .withClass(IntValue.class, new IncorrectValueConsumer<IntValue>());

        ValueConsumerDeterminer<Object, ValueViewFactory> determiner = ValueConsumerDeterminerBuilder
                .create(ValueViewFactory.class)
                .withDefault(new QAViewFactory<>())
                .withConsumers(intValueBuilder)
                .withClass(TextValue.class, new TextValueViewFactory())
                .withClass(BooleanMutableValue.class, new BooleanMutableValueViewFactory())
                .withClass(StringMutableValue.class, new StringMutableValueViewFactory())
                .build();

        new ViewGroupValueConsumerDeterminer<>(determiner).setValues(values, viewGroup);
    }

    private static final class IncorrectValueConsumer<Value> implements ValueConsumer<Value> {
    }
}
