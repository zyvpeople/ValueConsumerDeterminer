package com.develop.zuzik.valueconsumerdeterminer.core.builder;

import android.util.Log;

import com.develop.zuzik.valueconsumerdeterminer.core.composite.CompositeValueConsumerDeterminer;
import com.develop.zuzik.valueconsumerdeterminer.core.empty.EmptyValueConsumerDeterminer;
import com.develop.zuzik.valueconsumerdeterminer.core.interfaces.ValueConsumer;
import com.develop.zuzik.valueconsumerdeterminer.core.interfaces.ValueConsumerDeterminer;
import com.develop.zuzik.valueconsumerdeterminer.core.predicate.ClassPredicate;
import com.develop.zuzik.valueconsumerdeterminer.core.predicate.EqualityPredicate;
import com.develop.zuzik.valueconsumerdeterminer.core.predicate.PredicateValueConsumerDeterminer;
import com.develop.zuzik.valueconsumerdeterminer.core.predicate.ReferencePredicate;

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
    private ValueConsumer<Value> defaultConsumerOrNull = null;

    private ValueConsumerDeterminerBuilder(Class<Consumer> consumerClass) {
        this.consumerClass = consumerClass;
    }

    public <V extends Value> ValueConsumerDeterminerBuilder<Value, Consumer> withClass(Class<V> valueClass, ValueConsumer<V> consumer) {
        if (isValidValueConsumer((ValueConsumer<Value>) consumer)) {
            determiners.add(new PredicateValueConsumerDeterminer<Value, Consumer>(new ClassPredicate<Value>((Class<Value>) valueClass), (ValueConsumer<Value>) consumer));
        } else {
            logW("Can't register " + valueClass + " with " + consumer.getClass() + ". Must be " + consumerClass);
        }
        return this;
    }

    public <V extends Value> ValueConsumerDeterminerBuilder<Value, Consumer> withEquality(V value, ValueConsumer<V> consumer) {
        if (isValidValueConsumer((ValueConsumer<Value>) consumer)) {
            determiners.add(new PredicateValueConsumerDeterminer<Value, Consumer>(new EqualityPredicate<Value>(value), (ValueConsumer<Value>) consumer));
        } else {
            logW("Can't register value " + value + " with " + consumer.getClass() + ". Must be " + consumerClass);
        }
        return this;
    }

    public <V extends Value> ValueConsumerDeterminerBuilder<Value, Consumer> withReference(V reference, ValueConsumer<V> consumer) {
        if (isValidValueConsumer((ValueConsumer<Value>) consumer)) {
            determiners.add(new PredicateValueConsumerDeterminer<Value, Consumer>(new ReferencePredicate<Value>(reference), (ValueConsumer<Value>) consumer));
        } else {
            logW("Can't register reference " + reference + " with " + consumer.getClass() + ". Must be " + consumerClass);
        }
        return this;
    }

    public ValueConsumerDeterminerBuilder<Value, Consumer> withDefault(ValueConsumer<Value> emptyConsumer) {
        if (isValidValueConsumer(emptyConsumer)) {
            defaultConsumerOrNull = emptyConsumer;
        } else {
            logW("Can't register empty consumer " + emptyConsumer + " with " + emptyConsumer.getClass() + ". Must be " + consumerClass);
        }
        return this;
    }

    public ValueConsumerDeterminerBuilder<Value, Consumer> withConsumers(ValueConsumerDeterminerBuilder<Value, Consumer> builder) {
        determiners.addAll(builder.determiners);
        return this;
    }

    public ValueConsumerDeterminer<Value, Consumer> build() {
        CompositeValueConsumerDeterminer<Value, Consumer> compositeDeterminer = new CompositeValueConsumerDeterminer<>(determiners);
        return defaultConsumerOrNull == null
                ? compositeDeterminer
                : new EmptyValueConsumerDeterminer<>(compositeDeterminer, defaultConsumerOrNull);
    }

    private boolean isValidValueConsumer(ValueConsumer<Value> consumer) {
        return consumerClass.isAssignableFrom(consumer.getClass());
    }

    private void logW(String message) {
        Log.w(getClass().getSimpleName(), message);
    }
}
