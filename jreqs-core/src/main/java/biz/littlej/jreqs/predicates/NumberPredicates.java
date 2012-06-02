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
import java.math.BigDecimal;
import java.math.BigInteger;

import static biz.littlej.jreqs.predicates.Predicates.notNull;

/**
 * Some predicates pertaining to numbers.
 *
 * @author Yannick LOTH
 * @since 0.1.0
 */
public enum NumberPredicates implements Predicate<Number>, Serializable {
    /**
     * Checks if the specified number is equal to zero.
     */
    ZERO {
        public boolean apply(final Number inputParam) {
            Reqs.parameterCondition(notNull(), inputParam, "Input number parameter must not be null.");
            if (inputParam instanceof BigDecimal) {
                final BigDecimal value = (BigDecimal) inputParam;
                return value.equals(BigDecimal.ZERO);
            } else if (inputParam instanceof BigInteger) {
                final BigInteger value = (BigInteger) inputParam;
                return value.equals(BigInteger.ZERO);
            } else {
                return 0 == inputParam.doubleValue();
            }
        }
    },
    /**
     * Checks if the specified number is strictly negative (<0).
     */
    STRICTLY_NEGATIVE {
        public boolean apply(final Number inputParam) {
            Reqs.parameterCondition(Predicates.notNull(), inputParam, "Input number parameter must not be null.");
            if (inputParam instanceof BigDecimal) {
                final BigDecimal value = (BigDecimal) inputParam;
                return 0 > value.compareTo(BigDecimal.ZERO);
            } else if (inputParam instanceof BigInteger) {
                final BigInteger value = (BigInteger) inputParam;
                return 0 > value.compareTo(BigInteger.ZERO);
            } else {
                return 0 > inputParam.doubleValue();
            }
        }
    },
    /**
     * Checks if the specified number is strictly positive (>0).
     */
    STRICTLY_POSITIVE {
        public boolean apply(final Number inputParam) {
            Reqs.parameterCondition(Predicates.notNull(), inputParam, "Input number parameter must not be null.");
            if (inputParam instanceof BigDecimal) {
                final BigDecimal value = (BigDecimal) inputParam;
                return 0 < value.compareTo(BigDecimal.ZERO);
            } else if (inputParam instanceof BigInteger) {
                final BigInteger value = (BigInteger) inputParam;
                return 0 < value.compareTo(BigInteger.ZERO);
            } else {
                return 0 < inputParam.doubleValue();
            }
        }
    },
    /**
     * Checks if the specified number is negative (<=0).
     */
    NEGATIVE {
        public boolean apply(final Number inputParam) {
            Reqs.parameterCondition(Predicates.notNull(), inputParam, "Input number parameter must not be null.");
            if (inputParam instanceof BigDecimal) {
                final BigDecimal value = (BigDecimal) inputParam;
                return 0 >= value.compareTo(BigDecimal.ZERO);
            } else if (inputParam instanceof BigInteger) {
                final BigInteger value = (BigInteger) inputParam;
                return 0 >= value.compareTo(BigInteger.ZERO);
            } else {
                return 0 >= inputParam.doubleValue();
            }
        }
    },
    /**
     * Checks if the specified number is positive (>=0).
     */
    POSITIVE {
        public boolean apply(final Number inputParam) {
            Reqs.parameterCondition(Predicates.notNull(), inputParam, "Input number parameter must not be null.");
            if (inputParam instanceof BigDecimal) {
                final BigDecimal value = (BigDecimal) inputParam;
                return 0 <= value.compareTo(BigDecimal.ZERO);
            } else if (inputParam instanceof BigInteger) {
                final BigInteger value = (BigInteger) inputParam;
                return 0 <= value.compareTo(BigInteger.ZERO);
            } else {
                return 0 <= inputParam.doubleValue();
            }
        }
    },
    EVEN {
        public boolean apply(Number inputParam) {
            throw new UnsupportedOperationException("even predicate is still not implemented.");
        }
    }, ODD {
        public boolean apply(Number inputParam) {
            return !EVEN.apply(inputParam);//TODO if inputparam is not an integer type, even may return false for numbers that are not integers - do this check!
        }
    }
}