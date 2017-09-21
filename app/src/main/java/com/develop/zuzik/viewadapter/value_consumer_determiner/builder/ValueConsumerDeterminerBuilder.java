package com.develop.zuzik.viewadapter.value_consumer_determiner.builder;

import android.util.Log;

import com.develop.zuzik.viewadapter.value_consumer_determiner.composite.CompositeValueConsumerDeterminer;
import com.develop.zuzik.viewadapter.value_consumer_determiner.empty.EmptyValueConsumerDeterminer;
import com.develop.zuzik.viewadapter.value_consumer_determiner.interfaces.ValueConsumer;
import com.develop.zuzik.viewadapter.value_consumer_determiner.interfaces.ValueConsumerDeterminer;
import com.develop.zuzik.viewadapter.value_consumer_determiner.predicate.ClassPredicate;
import com.develop.zuzik.viewadapter.value_consumer_determiner.predicate.EqualityPredicate;
import com.develop.zuzik.viewadapter.value_consumer_determiner.predicate.PredicateValueConsumerDeterminer;
import com.develop.zuzik.viewadapter.value_consumer_determiner.predicate.ReferencePredicate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */
public class ValueConsumerDeterminerBuilder<Value, Consumer extends ValueConsumer> {

    public static <Value, Consumer extends ValueConsumer> ValueConsumerDeterminerBuilder<Value, Consumer> create(Class<Consumer> consumerClass) {
        return new ValueConsumerDeterminerBuilder<>(consumerClass);
    }

    private final Class<Consumer> consumerClass;
    private final List<ValueConsumerDeterminer<Value, Consumer>> determiners = new ArrayList<>();
    private ValueConsumer<Value> emptyConsumerOrNull = null;

    private ValueConsumerDeterminerBuilder(Class<Consumer> consumerClass) {
        this.consumerClass = consumerClass;
    }

    public <V extends Value> ValueConsumerDeterminerBuilder<Value, Consumer> consumerForClass(Class<V> valueClass, ValueConsumer<V> consumer) {
        if (isValidValueConsumer((ValueConsumer<Value>) consumer)) {
            determiners.add(new PredicateValueConsumerDeterminer<Value, Consumer>(new ClassPredicate<Value>((Class<Value>) valueClass), (ValueConsumer<Value>) consumer));
        } else {
            logW("Can't register " + valueClass + " with " + consumer.getClass() + ". Must be " + consumerClass);
        }
        return this;
    }

    public <V extends Value> ValueConsumerDeterminerBuilder<Value, Consumer> consumerForEquality(V value, ValueConsumer<V> consumer) {
        if (isValidValueConsumer((ValueConsumer<Value>) consumer)) {
            determiners.add(new PredicateValueConsumerDeterminer<Value, Consumer>(new EqualityPredicate<Value>(value), (ValueConsumer<Value>) consumer));
        } else {
            logW("Can't register value " + value + " with " + consumer.getClass() + ". Must be " + consumerClass);
        }
        return this;
    }

    public <V extends Value> ValueConsumerDeterminerBuilder<Value, Consumer> consumerForReference(V reference, ValueConsumer<V> consumer) {
        if (isValidValueConsumer((ValueConsumer<Value>) consumer)) {
            determiners.add(new PredicateValueConsumerDeterminer<Value, Consumer>(new ReferencePredicate<Value>(reference), (ValueConsumer<Value>) consumer));
        } else {
            logW("Can't register reference " + reference + " with " + consumer.getClass() + ". Must be " + consumerClass);
        }
        return this;
    }

    public ValueConsumerDeterminerBuilder<Value, Consumer> emptyConsumer
            (ValueConsumer<Value> emptyConsumer) {
        emptyConsumerOrNull = emptyConsumer;
        return this;
    }

    public ValueConsumerDeterminerBuilder<Value, Consumer> consumers
            (ValueConsumerDeterminerBuilder<Value, Consumer> builder) {
        determiners.addAll(builder.determiners);
        return this;
    }

    public ValueConsumerDeterminer<Value, Consumer> build() {
        CompositeValueConsumerDeterminer<Value, Consumer> compositeDeterminer = new CompositeValueConsumerDeterminer<>(determiners);
        return emptyConsumerOrNull == null
                ? compositeDeterminer
                : new EmptyValueConsumerDeterminer<>(compositeDeterminer, emptyConsumerOrNull);
    }

    private boolean isValidValueConsumer(ValueConsumer<Value> consumer) {
        return consumerClass.isAssignableFrom(consumer.getClass());
    }

    private void logW(String message) {
        Log.w(getClass().getSimpleName(), message);
    }
}
