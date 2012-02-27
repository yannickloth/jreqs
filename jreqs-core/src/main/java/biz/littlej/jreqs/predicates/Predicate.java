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

/**
 * Determines a boolean value for a given input.
 *
 * @author Yannick LOTH
 * @since 0.1.0
 */
public interface Predicate<T> {
    /**
     * Applies this predicate to the {@code inputParam} and returns {@code true} of {@code false}.
     * <ul>
     * <li>The evaluation of a predicate shoud not cause any side effect.</li>
     * <li>The result of {@code apply} should be consistent with (do not confuse with "equal to") {@link java.lang.Object#equals
     * Object.equals}: {@code a.equals(b)} implies that {@code predicate.apply(a) == predicate.apply(b))} and vice-versa.</li>
     * </ul>
     *
     * @throws NullPointerException if {@code inputParam} is {@code null} and this predicate does not accept a {@code null} parameter.
     */
    boolean apply(T inputParam);
}
