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
import biz.littlej.jreqs.util.Objects;

import java.io.Serializable;

/**
 * Checks if an input object is equal to the specified object, using {@link Objects#equals}.
 *
 * @author Yannick LOTH
 * @since 0.1.0
 */
public final class EqualToPredicate<T> implements Predicate<T>, Serializable {
    private static final long serialVersionUID = 0;
    private final Object o;

    public EqualToPredicate(final Object objectParam) {
        Reqs.parameterCondition(Predicates.notNull(), objectParam, "Object parameter must not be null.");
        o = objectParam;
    }

    public boolean apply(final T inputParam) {
        return Objects.equals(o, inputParam);
    }
}
