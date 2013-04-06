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
import java.util.Calendar;

/**
 * Some predicates pertaining to date or time.
 *
 * @author Yannick LOTH
 * @since 0.1.0
 */
public enum DateTimePredicates implements Predicate<Calendar>, Serializable {
    /**
     * Evaluates to {@code true} if the specified input {@code Calendar} is in the past.
     */
    PAST {
        public boolean apply(final Calendar inputParam) {
            Reqs.parameterCondition(Predicates.notNull(), inputParam, "Calendar input parameter must not be null.");
            return inputParam.before(Calendar.getInstance());
        }
    },
    /**
     * Evaluates to {@code true} if the specified input {@code Calendar} is in the future.
     */
    FUTURE {
        public boolean apply(final Calendar inputParam) {
            Reqs.parameterCondition(Predicates.notNull(), inputParam, "Calendar input parameter must not be null.");
            return inputParam.after(Calendar.getInstance());
        }
    }
}