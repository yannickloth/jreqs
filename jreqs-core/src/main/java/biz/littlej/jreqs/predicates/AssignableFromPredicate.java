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
 * Checks that all input classes are assignable from the input class.
 *
 * @author Yannick LOTH
 * @since 0.1.0
 */
public final class AssignableFromPredicate implements Predicate<Class<?>>, Serializable {
    private static final long serialVersionUID = 0;
    private final Class<?> clazz;

    public static AssignableFromPredicate getInstance(final Class<?> classParam) {
        final AssignableFromPredicate p = PredicateCache.getPredicate(classParam, AssignableFromPredicate.class);
        if (p != null) {
            return p;
        }
        final AssignableFromPredicate predicate = new AssignableFromPredicate(classParam);
        PredicateCache.registerNewPredicate(predicate.clazz, predicate);
        return predicate;
    }

    private AssignableFromPredicate(final Class<?> classParam) {
        Reqs.parameterCondition(Predicates.notNull(), classParam, "Class parameter must not be null.");
        clazz = classParam;
    }

    public boolean apply(final Class<?> inputParam) {
        Reqs.parameterCondition(Predicates.notNull(), inputParam, "Class input parameter must not be null.");
        return clazz.isAssignableFrom(inputParam);
    }
}
