package com.develop.zuzik.viewadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.develop.zuzik.viewadapter.recycler_view_value_consumer_determiner_adapter.RecyclerViewValueConsumerDeterminerAdapter;
import com.develop.zuzik.viewadapter.recyclerviewadapter.RecyclerViewAdapter;
import com.develop.zuzik.viewadapter.recyclerviewadapter.RecyclerViewAdapterBuilder;
import com.develop.zuzik.viewadapter.recyclerviewadapter.decorator.MatchParentWidthRecyclerViewAdapterDecorator;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.Predicate;
import com.develop.zuzik.viewadapter.value.BooleanMutableValue;
import com.develop.zuzik.viewadapter.value.StringMutableValue;
import com.develop.zuzik.viewadapter.value_consumer_determiner.builder.ValueConsumerDeterminerBuilder;
import com.develop.zuzik.viewadapter.value_consumer_determiner.interfaces.ValueConsumerDeterminer;
import com.develop.zuzik.viewadapter.valueview.BooleanMutableValueViewFactory;
import com.develop.zuzik.viewadapter.valueview.StringMutableValueViewFactory;
import com.develop.zuzik.viewadapter.view_group_value_consumer_determiner_adapter.ViewGroupValueConsumerDeterminerAdapter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

//TODO: list is changed
//TODO: different views for one model
//TODO: animation
//TODO: not only list but grid etc
//TODO: item decoration
//TODO: create special view with interface and remove checking in onBindViewHolder
//TODO: add log if set already set factory
//TODO: focus is removed when scroll item out of screen
//TODO: renaming
//TODO: fix warnings
//TODO: in builder add methods - strict mode, safe mode, qa safe mode
//TODO: check if view is not ValueView
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean useRecycler = false;
        ViewGroup recyclerViewContainer = (ViewGroup) findViewById(R.id.recyclerViewContainer);
        ViewGroup linearLayoutContainer = (ViewGroup) findViewById(R.id.linearLayoutContainer);
        ViewGroup linearLayout = (ViewGroup) findViewById(R.id.linearLayout);

        recyclerViewContainer.setVisibility(useRecycler ? View.VISIBLE : View.GONE);
        linearLayoutContainer.setVisibility(!useRecycler ? View.VISIBLE : View.GONE);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

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

//        RecyclerViewAdapter<Object> adapter = RecyclerViewAdapterBuilder
//                .create()
//                .displayCustomViewIfValueCantBeDisplayed(new QAViewFactory())
////                .viewForClass(TextValue.class, new TextValueViewFactory())
////                .viewForClass(IntValue.class, new IntValueViewFactory())
////                .viewForClass(BooleanMutableValue.class, new BooleanMutableValueViewFactory())
////                .viewForClass(StringMutableValue.class, new StringMutableValueViewFactory())
//
//                .viewForClass(TextValue.class, new TextValueViewFactory())
//                .viewForEquality(2, new IntRightViewFactory())
//                .viewForClass(Integer.class, new IntLeftViewFactory())
//                .viewForReference(intValue2, new IntValueViewFactory())
////                .viewForClass(StringMutableValue.class, new StringMutableValueViewFactory())
//                .build();
//        adapter.setValues(values);
//        recyclerView.setAdapter(new MatchParentWidthRecyclerViewAdapterDecorator<>(adapter));


        ValueConsumerDeterminerBuilder<Object> intValueBuilder = ValueConsumerDeterminerBuilder
                .create()
                .consumerForEquality(2, new IntRightViewFactory())
                .consumerForClass(Integer.class, new IntLeftViewFactory())
                .consumerForReference(intValue2, new IntValueViewFactory());

        ValueConsumerDeterminer<Object> determiner = ValueConsumerDeterminerBuilder
                .create()
                .emptyConsumer(new QAViewFactory<>())
                .consumers(intValueBuilder)
                .consumerForClass(TextValue.class, new TextValueViewFactory())
                .consumerForClass(BooleanMutableValue.class, new BooleanMutableValueViewFactory())
                .consumerForClass(StringMutableValue.class, new StringMutableValueViewFactory())
                .build();

        ExampleRecyclerViewAdapter<Object> adapter = new ExampleRecyclerViewAdapter<>(new RecyclerViewValueConsumerDeterminerAdapter<>(determiner));
        adapter.setValues(values);
        recyclerView.setAdapter(new MatchParentWidthRecyclerViewAdapterDecorator<>(adapter));

        new ViewGroupValueConsumerDeterminerAdapter<>(determiner).setValues(values, linearLayout);

    }
}
