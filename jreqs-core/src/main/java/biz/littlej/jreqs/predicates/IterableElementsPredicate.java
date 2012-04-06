/*
 * Copyright (C) 2012 Yannick LOTH, LittleJ [www.littlej.biz]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package biz.littlej.jreqs.predicates;

import biz.littlej.jreqs.Reqs;

import java.io.Serializable;

/**
 * Applies a predicate to all elements of the specified {@code Collection}.
 *
 * @author Yannick LOTH
 * @since 0.1.0
 */
public final class IterableElementsPredicate implements Predicate<Iterable<?>>, Serializable {
    private static final long serialVersionUID = 0;
    private final Predicate predicate;
    private final Matching matching;

    public enum Matching {
        /**
         * The predicate must evaluate to {@code true} for all iterable elements.
         */
        ALL,
        /**
         * The predicate must not evaluate to {@code true} for any iterable elements.
         */
        NONE,
        /**
         * The predicate must evaluate to {@code true} for exactly one iterable element.
         */
        ONE
    }

    /**
     * Constructor.
     *
     * @param predicateParam The predicate that will be applied to all iterable elements.  Must not be {@code null}.
     * @param matchingParam  Specifies how many iterable elements must evaluate to {@code true} when the predicate is applied to them.  Must not be {@code null}.
     */
    public IterableElementsPredicate(final Predicate predicateParam, final Matching matchingParam) {
        Reqs.parameterCondition(Predicates.notNull(), predicateParam, "Predicate parameter must not be null.");
        Reqs.parameterCondition(Predicates.notNull(), matchingParam, "Matching parameter must not be null.");
        predicate = predicateParam;
        matching = matchingParam;
    }

    public boolean apply(final Iterable<?> inputParam) {
        Reqs.parameterCondition(Predicates.notNull(), inputParam, "Input collection parameter must not be null.");
        switch (matching) {
            case ALL:
                for (final Object o : inputParam) {
                    if (!predicate.apply(o)) {
                        return false;
                    }
                }
                return true;
            case NONE:
                for (final Object o : inputParam) {
                    if (predicate.apply(o)) {
                        return false;
                    }
                }
                return true;
            case ONE:
                int matching = 0;
                for (final Object o : inputParam) {
                    if (predicate.apply(o)) {
                        ++matching;
                    }
                }
                return 1 == matching;
            default:
                throw new IllegalStateException("This should never happen, but it did: the matching parameter is unknown.");
        }
    }
}
