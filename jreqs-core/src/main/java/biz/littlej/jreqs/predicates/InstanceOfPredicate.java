package biz.littlej.jreqs.predicates;

import biz.littlej.jreqs.Reqs;

import java.io.Serializable;

public class InstanceOfPredicate implements Predicate<Object>, Serializable {
    private static final long serialVersionUID = 0;
    private final Class<?> clazz;

    public InstanceOfPredicate(final Class<?> classParam) {
        Reqs.parameterCondition(Predicates.notNull(), classParam, "Class parameter must not be null.");
        clazz = classParam;
    }

    public boolean apply(final Object inputParam) {
        return clazz.isInstance(inputParam);
    }
}
