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
package biz.littlej.jreqs.util;

import biz.littlej.jreqs.Reqs;
import biz.littlej.jreqs.predicates.Predicates;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Kind of backports some methods of the {@code java.util.Objects} class provided in the JDK 7.
 *
 * @author Yannick LOTH
 * @since 0.1.0
 */
public final class Objects {

    /**
     * Returns 0 if the arguments are identical and {@code c.compare(a, b)}
     * otherwise. Consequently, if both arguments are {@code null} 0 is
     * returned.
     * <p/>
     * <p/>
     * Note that if one of the arguments is {@code null}, a
     * {@code NullPointerException} may or may not be thrown depending on what
     * ordering policy, if any, the {@link java.util.Comparator Comparator} chooses to
     * have for {@code null} values.
     *
     * @param <T>             the type of the objects being compared
     * @param firstParam      an object
     * @param secondParam     an object to be compared with {@code a}
     * @param comparatorParam the {@code Comparator} to compare the first two arguments
     * @return 0 if the arguments are identical and {@code c.compare(a, b)}
     *         otherwise.
     * @see Comparable
     * @see java.util.Comparator
     */
    public static <T> int compare(final T firstParam, final T secondParam,
                                  final Comparator<? super T> comparatorParam) {
        return firstParam == secondParam ? 0 : comparatorParam.compare(firstParam, secondParam);
    }

    /**
     * Returns {@code true} if the arguments are deeply equal to each other and
     * {@code false} otherwise.
     * <p/>
     * Two {@code null} values are deeply equal. If both arguments are arrays,
     * the algorithm in {@link java.util.Arrays#deepEquals(Object[], Object[])
     * Arrays.deepEquals} is used to determine equality. Otherwise, equality is
     * determined by using the {@link Object#equals equals} method of the first
     * argument.
     *
     * @param firstParam  an object
     * @param secondParam an object to be compared with {@code a} for deep equality
     * @return {@code true} if the arguments are deeply equal to each other and
     *         {@code false} otherwise
     * @see java.util.Arrays#deepEquals(Object[], Object[])
     * @see Objects#equals(Object, Object)
     */
    public static boolean deepEquals(final Object firstParam, final Object secondParam) {
        if (firstParam == secondParam) {
            return true;
        }
        if (firstParam == null || secondParam == null) {
            return false;
        }
        return Arrays.deepEquals((Object[]) firstParam, (Object[]) secondParam);
    }

    /**
     * Returns {@code true} if the arguments are equal to each other and
     * {@code false} otherwise. Consequently, if both arguments are {@code null}
     * , {@code true} is returned and if exactly one argument is {@code null},
     * {@code false} is returned. Otherwise, equality is determined by using the
     * {@link Object#equals equals} method of the first argument.
     *
     * @param firstParam  an object
     * @param secondParam an object to be compared with {@code a} for equality
     * @return {@code true} if the arguments are equal to each other and
     *         {@code false} otherwise
     * @see Object#equals(Object)
     */
    public static boolean equals(final Object firstParam, final Object secondParam) {
        return firstParam == secondParam || firstParam != null && firstParam.equals(secondParam);
    }

    /**
     * Generates a hash code for a sequence of input values. The hash code is
     * generated as if all the input values were placed into an array, and that
     * array were hashed by calling {@link java.util.Arrays#hashCode(Object[])}.
     * <p/>
     * <p/>
     * This method is useful for implementing {@link Object#hashCode()} on
     * objects containing multiple fields. For example, if an object that has
     * three fields, {@code x}, {@code y}, and {@code z}, one could write:
     * <p/>
     * <blockquote>
     * <p/>
     * <pre>
     * &#064;Override
     * public int hashCode() {
     *     return Objects.hash(x, y, z);
     * }
     * </pre>
     * <p/>
     * </blockquote>
     * <p/>
     * <b>Warning: When a single object reference is supplied, the returned
     * value does not equal the hash code of that object reference.</b> This
     * value can be computed by calling {@link #hashCode(Object)}.
     *
     * @param objectsParam the values to be hashed
     * @return a hash value of the sequence of input values
     * @see java.util.Arrays#hashCode(Object[])
     * @see java.util.List#hashCode
     */
    public static int hash(final Object... objectsParam) {
        return Arrays.hashCode(objectsParam);
    }

    /**
     * Returns the hash code of a non-{@code null} argument and 0 for a
     * {@code null} argument.
     *
     * @param objectParam an object
     * @return the hash code of a non-{@code null} argument and 0 for a
     *         {@code null} argument
     * @see Object#hashCode
     */
    public static int hashCode(final Object objectParam) {
        return objectParam != null ? objectParam.hashCode() : 0;
    }

    /**
     * Returns the result of calling {@code toString} for a non-{@code null}
     * argument and {@code "null"} for a {@code null} argument.
     *
     * @param objectParam an object
     * @return the result of calling {@code toString} for a non-{@code null}
     *         argument and {@code "null"} for a {@code null} argument
     * @see Object#toString
     * @see String#valueOf(Object)
     */
    public static String toString(final Object objectParam) {
        return toString(objectParam, "null");
    }

    /**
     * Returns the result of calling {@code toString} on the first argument if
     * the first argument is not {@code null} and returns the second argument
     * otherwise.
     *
     * @param objectParam      an object
     * @param nullDefaultParam string to return if the first argument is {@code null}
     * @return the result of calling {@code toString} on the first argument if
     *         it is not {@code null} and the second argument otherwise.
     * @see Objects#toString(Object)
     */
    public static String toString(final Object objectParam, final String nullDefaultParam) {
        Reqs.parameterCondition(Predicates.notNull(), objectParam, "Object parameter on which toString() will be applied must not be null.");
        return objectParam != null ? objectParam.toString() : nullDefaultParam;
    }

    private Objects() {
        throw new IllegalStateException("This object must not be instanciated.");
    }
}
