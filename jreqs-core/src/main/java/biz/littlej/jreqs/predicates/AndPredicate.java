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

import static biz.littlej.jreqs.predicates.Predicates.notNull;

/**
 * Returns a predicate that evaluates to {@code true} if all specified predicates also do for the same input, like the logical AND.
 *
 * @author Yannick LOTH
 * @since 0.1.0
 */
public final class AndPredicate<T> implements Predicate<T>, Serializable {
    private static final long serialVersionUID = 0;
    private final Predicate<T> firstMemberPredicate;
    private final Predicate<T> secondeMemberPredicate;

    public AndPredicate(final Predicate<T> firstMemberPredicateParam, final Predicate<T> secondMemberPredicateParam) {
        Reqs.parameterCondition(notNull(), firstMemberPredicateParam, "First member predicate object parameter must not be null.");
        Reqs.parameterCondition(notNull(), secondMemberPredicateParam, "Second member predicate object parameter must not be null.");
        firstMemberPredicate = firstMemberPredicateParam;
        secondeMemberPredicate = secondMemberPredicateParam;
    }

    public boolean apply(final T inputParam) {
        return firstMemberPredicate.apply(inputParam) && secondeMemberPredicate.apply(inputParam);
    }
}