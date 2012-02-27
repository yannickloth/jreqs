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

import static biz.littlej.jreqs.Reqs.parameterCondition;
/**
 * @author Yannick LOTH
 * @since 0.1.0
 */
public final class PredicateOperations {
    public static <T> Predicate<T> not(final Predicate<T> originalPredicate) {
        parameterCondition(not(ObjectPredicates.NULL), originalPredicate, "Original predicate object parameter must not be null.");
        return new Predicate<T>() {
            public boolean apply(final T inputParam) {
                return !originalPredicate.apply(inputParam);
            }
        };
    }

    public static <T> Predicate<T> and(final Predicate<T> firstMemberPredicateParam, final Predicate<T> secondMemberPredicateParam) {
        return new AndPredicate<T>(firstMemberPredicateParam, secondMemberPredicateParam);
    }

    public static <T> Predicate<T> nand(final Predicate<T> firstMemberPredicateParam, final Predicate<T> secondMemberPredicateParam) {
        return not(new AndPredicate<T>(firstMemberPredicateParam, secondMemberPredicateParam));
    }

    public static <T> Predicate<T> or(final Predicate<T> firstMemberPredicateParam, final Predicate<T> secondMemberPredicateParam) {
        return new OrPredicate<T>(firstMemberPredicateParam, secondMemberPredicateParam);
    }

    public static <T> Predicate<T> nor(final Predicate<T> firstMemberPredicateParam, final Predicate<T> secondMemberPredicateParam) {
        return not(new OrPredicate<T>(firstMemberPredicateParam, secondMemberPredicateParam));
    }

    public static <T> Predicate<T> xor(final Predicate<T> firstMemberPredicateParam, final Predicate<T> secondMemberPredicateParam) {
        return new XorPredicate<T>(firstMemberPredicateParam, secondMemberPredicateParam);
    }
}
