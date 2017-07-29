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

import java.io.File;
import java.util.Calendar;
import java.util.Collection;
import java.util.regex.Pattern;

import static biz.littlej.jreqs.predicates.PredicateOperations.not;

/**
 * Static utility methods that provide Predicate instances.
 * All methods returns serializable predicates as long as they're given serializable parameters.
 * <p/>
 * Do a static import of this class and consider its methods as syntactic sugar.
 *
 * @author Yannick LOTH
 * @since 0.1.0
 */
public final class Predicates {
    public static Predicate<Calendar> past() {
        return DateTimePredicates.PAST;
    }

    public static Predicate<Calendar> future() {
        return DateTimePredicates.FUTURE;
    }

    public static <T> Predicate<T> isNull() {
        return (Predicate<T>) ObjectPredicates.NULL;
    }

    public static <T> Predicate<T> notNull() {
        return not((Predicate<T>) ObjectPredicates.NULL);
    }

    public static Predicate<Collection> notEmptyCollection() {
        return not(CollectionPredicates.EMPTY);
    }

    public static Predicate<Collection> emptyCollection() {
        return CollectionPredicates.EMPTY;
    }

    public static <T> Predicate<T> alwaysTrue() {
        return (Predicate<T>) ObjectPredicates.ALWAYS_TRUE;
    }

    public static <T> Predicate<T> alwaysFalse() {
        return not((Predicate<T>) ObjectPredicates.ALWAYS_TRUE);
    }

    public static <T> Predicate<T> equalTo(final Object objectParam) {
        return EqualToPredicate.getInstance(objectParam);
    }

    public static Predicate<Object> instanceOf(final Class<?> classParam) {
        return InstanceOfPredicate.getInstance(classParam);
    }

    public static Predicate<Iterable<?>> allInstanceOf(final Class<?> classParam) {
        return AllInstanceOfPredicate.getInstance(classParam);
    }

    public static Predicate<Class<?>> assignableFrom(final Class<?> classParam) {
        return AssignableFromPredicate.getInstance(classParam);
    }

    public static Predicate<CharSequence> containsPattern(final String patternStringParam) {
        return PatternContainedPredicate.getInstance(patternStringParam);
    }

    public static Predicate<CharSequence> containsPattern(final Pattern patternParam) {
        return PatternContainedPredicate.getInstance(patternParam);
    }

    public static Predicate<CharSequence> emptyCharSequence() {
        return CharSequencePredicates.EMPTY;
    }

    public static Predicate<CharSequence> notEmptyCharSequence() {
        return not(emptyCharSequence());
    }

    public static Predicate<String> blankString() {
        return StringPredicates.BLANK;
    }

    public static Predicate<String> notBlankString() {
        return not(blankString());
    }

    public static Predicate<File> writeableFile() {
        return FilePredicates.CAN_WRITE;
    }

    public static Predicate<File> readableFile() {
        return FilePredicates.CAN_READ;
    }

    public static Predicate<File> executableFile() {
        return FilePredicates.CAN_EXECUTE;
    }

    public static Predicate<File> hiddenFile() {
        return FilePredicates.IS_HIDDEN;
    }

    public static Predicate<File> isDirectory() {
        return FilePredicates.IS_DIRECTORY;
    }

    public static Predicate<File> isFile() {
        return FilePredicates.IS_FILE;
    }

    public static Predicate<File> existingFile() {
        return FilePredicates.EXISTS;
    }

    public static Predicate<Number> negative() {
        return NumberPredicates.NEGATIVE;
    }

    public static Predicate<Number> strictlyNegative() {
        return NumberPredicates.STRICTLY_NEGATIVE;
    }

    public static Predicate<Number> positive() {
        return NumberPredicates.POSITIVE;
    }

    public static Predicate<Number> strictlyPositive() {
        return NumberPredicates.STRICTLY_POSITIVE;
    }

    public static Predicate<Number> zero() {
        return NumberPredicates.ZERO;
    }

    public static Predicate<Iterable<?>> allIterableElements(final Predicate<?> predicateParam) {
        return new IterableElementsPredicate(predicateParam, IterableElementsPredicate.Matching.ALL);
    }

    public static Predicate<Iterable<?>> oneIterableElement(final Predicate<?> predicateParam) {
        return new IterableElementsPredicate(predicateParam, IterableElementsPredicate.Matching.ONE);
    }

    public static Predicate<Iterable<?>> noIterableElement(final Predicate<?> predicateParam) {
        return new IterableElementsPredicate(predicateParam, IterableElementsPredicate.Matching.NONE);
    }

    public static Predicate<Boolean> isTrue() {
        return new Predicate<Boolean>() {
            public boolean apply(final Boolean inputParam) {
                Reqs.parameterCondition(Predicates.notNull(), inputParam, "Input parameter must not be null.");
                return inputParam;
            }
        };
    }

    public static Predicate<Boolean> isFalse() {
        return new Predicate<Boolean>() {
            public boolean apply(final Boolean inputParam) {
                Reqs.parameterCondition(Predicates.notNull(), inputParam, "Input parameter must not be null.");
                return !inputParam;
            }
        };
    }

    public static Predicate<String> isValidUrl() {
        return new Predicate<String>() {
            public boolean apply(final String inputParam) {
                Reqs.parameterCondition(Predicates.notNull(), inputParam, "Input parameter must not be null.");
                return StringPredicates.VALID_URL.apply(inputParam);
            }
        };
    }

    public static Predicate<String> isValidUri() {
        return new Predicate<String>() {
            public boolean apply(final String inputParam) {
                Reqs.parameterCondition(Predicates.notNull(), inputParam, "Input parameter must not be null.");
                return StringPredicates.VALID_URI.apply(inputParam);
            }
        };
    }
}
