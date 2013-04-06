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
 * Checks that the input object is an instance of the specified class.
 *
 * @author Yannick LOTH
 * @since 0.1.0
 */
public final class InstanceOfPredicate implements Predicate<Object>, Serializable {
    private static final long serialVersionUID = 0;
    private final Class<?> clazz;

    public static InstanceOfPredicate getInstance(final Class<?> classParam) {
        final InstanceOfPredicate p = PredicateCache.getPredicate(classParam, InstanceOfPredicate.class);
        if (p != null) {
            return p;
        }
        final InstanceOfPredicate predicate = new InstanceOfPredicate(classParam);
        PredicateCache.registerNewPredicate(predicate.clazz, predicate);
        return predicate;
    }

    private InstanceOfPredicate(final Class<?> classParam) {
        Reqs.parameterCondition(Predicates.notNull(), classParam, "Class parameter must not be null.");
        clazz = classParam;
    }

    public boolean apply(final Object inputParam) {
        Reqs.parameterCondition(Predicates.notNull(), inputParam, "Input object parameter must not be null.");
        return clazz.isInstance(inputParam);
    }
}
