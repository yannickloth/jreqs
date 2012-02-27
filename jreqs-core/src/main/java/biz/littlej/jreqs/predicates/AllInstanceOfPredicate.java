package biz.littlej.jreqs.predicates;

import biz.littlej.jreqs.Reqs;

import java.io.Serializable;

public class AllInstanceOfPredicate implements Predicate<Iterable<?>>, Serializable {
    private static final long serialVersionUID = 0;
    private final Class<?> clazz;

    public AllInstanceOfPredicate(final Class<?> classParam) {
        Reqs.parameterCondition(Predicates.notNull(), classParam, "Class parameter must not be null.");
        clazz = classParam;
    }

    public boolean apply(final Iterable<?> inputParam) {
        for (final Object current : inputParam) {
            if (!clazz.isInstance(current)) {
                return false;
            }
        }
        return true;
    }
}
