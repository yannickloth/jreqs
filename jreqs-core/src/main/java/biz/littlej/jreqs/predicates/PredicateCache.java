package biz.littlej.jreqs.predicates;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * This class implements a simple predicate cache that may be used by various predicate implementations to avoid
 * instantiating predicates more than needed.
 */
public final class PredicateCache {
    private static final Map<Class, Map<Object, WeakReference<Predicate>>> instantiated = new HashMap<Class, Map<Object, WeakReference<Predicate>>>();

    public static <P extends Predicate> void registerNewPredicate(final Object keyParam, final P predicateParam) {
        final Class<? extends Predicate> predicateClass = predicateParam.getClass();
        if (!instantiated.containsKey(predicateClass)) {
            instantiated.put(predicateClass, new HashMap<Object, WeakReference<Predicate>>());
        }
        instantiated.get(predicateClass).put(keyParam, new WeakReference<Predicate>(predicateParam));
    }

    /**
     * Returns null if the predicte is not registered in the cache.
     *
     * @param keyParam
     * @param predicateClassParam
     * @param <P>
     * @return
     */
    public static <P extends Predicate> P getPredicate(final Object keyParam, final Class<P> predicateClassParam) {
        final Map<Object, WeakReference<Predicate>> map = instantiated.get(predicateClassParam);
        if (map == null) {
            return null;
        }
        final WeakReference<Predicate> ref = map.get(keyParam);
        if (ref == null) {
            return null;
        }
        return (P) ref.get();
    }

    /**
     * Private constructor on final class so that the class can't be instantiated nor extended.
     */
    private PredicateCache() {
    }
}
