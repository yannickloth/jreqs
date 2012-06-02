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
import java.util.regex.Pattern;

/**
 * Checks if the input {@code CharSequence} matches the specified {@link Pattern}.
 *
 * @author Yannick LOTH
 * @since 0.1.0
 */
public final class PatternContainedPredicate implements Predicate<CharSequence>, Serializable {
    private static final long serialVersionUID = 0;
    private final Pattern pattern;

    public static PatternContainedPredicate getInstance(final Pattern patternParam) {
        final PatternContainedPredicate p = PredicateCache.getPredicate(patternParam, PatternContainedPredicate.class);
        if (p != null) {
            return p;
        }
        final PatternContainedPredicate predicate = new PatternContainedPredicate(patternParam);
        PredicateCache.registerNewPredicate(predicate.pattern, predicate);
        return predicate;
    }

    public static PatternContainedPredicate getInstance(final String patternParam) {
        return getInstance(Pattern.compile(patternParam));
    }

    /**
     * @param patternParam Must not be {@code null}.
     */
    private PatternContainedPredicate(final Pattern patternParam) {
        Reqs.parameterCondition(Predicates.notNull(), patternParam, "Pattern parameter must not be null.");
        pattern = patternParam;
    }

    private PatternContainedPredicate(String patternStringParam) {
        this(Pattern.compile(patternStringParam));
    }

    public boolean apply(CharSequence charSequenceParam) {
        Reqs.parameterCondition(Predicates.notNull(), charSequenceParam, "CharSequence input parameter must not be null.");
        return pattern.matcher(charSequenceParam).find();
    }
}
