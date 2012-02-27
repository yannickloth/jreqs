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
package biz.littlej.jreqs.guava;

import biz.littlej.jreqs.Reqs;
import biz.littlej.jreqs.predicates.Predicate;

import static biz.littlej.jreqs.predicates.Predicates.notNull;

/**
 * Wraps a guava predicate.  This class is intended to make use of Guava predicates.
 *
 * @param <T> The type of the object on which the predicate will be applied.
 * @author Yannick LOTH
 * @since 0.1.0
 */
public final class GuavaPredicateWrapper<T> implements Predicate<T> {
    com.google.common.base.Predicate<T> guavaPredicate;

    /**
     * Returns a new {@code Predicate<T>} that wraps the specified Guava predicate.
     *
     * @param guavaPredicateParam The Guava predicate that is wrapped.  Must not be {@code null}.
     * @param <T>                 The type of the object on which the predicate will be applied.
     * @return The wrapping predicate.
     * @throws IllegalArgumentException if the {@code guavaPredicateParam} is {@code null}.
     */
    public static <T> Predicate<T> wrapGuavaPredicate(com.google.common.base.Predicate<T> guavaPredicateParam) {
        return new GuavaPredicateWrapper<T>(guavaPredicateParam);
    }

    private GuavaPredicateWrapper(final com.google.common.base.Predicate<T> guavaPredicateParam) {
        Reqs.parameterCondition(notNull(), guavaPredicateParam, "The guava predicate parameter must not be null.");
        guavaPredicate = guavaPredicateParam;
    }

    public boolean apply(final T inputParam) {
        return guavaPredicate.apply(inputParam);
    }
}
