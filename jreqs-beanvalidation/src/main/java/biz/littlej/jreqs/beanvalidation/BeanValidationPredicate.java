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
package biz.littlej.jreqs.beanvalidation;


import biz.littlej.jreqs.predicates.Predicate;
import biz.littlej.jreqs.predicates.PredicateCache;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import static biz.littlej.jreqs.Reqs.parameterCondition;
import static biz.littlej.jreqs.Reqs.postCondition;
import static biz.littlej.jreqs.predicates.Predicates.notNull;

/**
 * A predicate that applies bean validation to its input beans and evaluates to {@code true} if the input bean is valid.
 * Validation is done using JSR303 bean validation framework.
 *
 * @param <T> The type parameter.
 */
public class BeanValidationPredicate<T> implements Predicate<T> {
    /**
     * The validator factory that is used by this predicate instance.
     */
    private final ValidatorFactory validatorFactory;
    /**
     * Will contain the constraint violations if some are found while applying the predicate to an object.  As the
     * {@code apply} method only returns a boolean, this allows the user to retrieve the constraints that are violated.
     */
    private final Map<T, Set<ConstraintViolation<T>>> violations = new WeakHashMap<T, Set<ConstraintViolation<T>>>();

    /**
     * Static factory method.
     *
     * @param <T> The type parameter.
     * @return A new instance with the default validator factory.
     */
    public static <T> BeanValidationPredicate<T> getInstance() {
        return getInstance(null);
    }

    /**
     * Static factory method.
     *
     * @param <T>                   The type parameter.
     * @param validatorFactoryParam The validator factory.  If the specified validator factory is {@code null},
     *                              the default validator factory is used.
     * @return A new instance with the specified validator factory.
     */
    public static synchronized <T> BeanValidationPredicate<T> getInstance(final ValidatorFactory validatorFactoryParam) {
        final BeanValidationPredicate<T> p = PredicateCache.getPredicate(validatorFactoryParam, BeanValidationPredicate.class);
        if (p != null) {
            return p;
        }
        final BeanValidationPredicate<T> predicate = new BeanValidationPredicate<T>(validatorFactoryParam);
        PredicateCache.registerNewPredicate(predicate.validatorFactory, predicate);
        return predicate;
    }


    /**
     * Default constructor.
     * Calls {@code this(null);}. Thus, the default validator factory will be used to validate beans.
     */
    protected BeanValidationPredicate() {
        this(null);
    }

    /**
     * Constructor.
     *
     * @param validatorFactoryParam The validator factory that must be used.
     *                              If {@code null}, {@code Validation.buildDefaultValidatorFactory()} is used to get
     *                              the default validator factory.
     */
    protected BeanValidationPredicate(final ValidatorFactory validatorFactoryParam) {
        if (validatorFactoryParam == null) {
            validatorFactory = Validation.buildDefaultValidatorFactory();
        } else {
            validatorFactory = validatorFactoryParam;
        }
        postCondition(notNull(), validatorFactory, "Validator factory must not be null after predicate construction.");
    }

    public boolean apply(final T inputParam) {
        final Validator validator = validatorFactory.getValidator();
        //synchronize here to make sure that the constraint violations really match the input parameter at the time of validation.
        synchronized (inputParam) {
            final Set<ConstraintViolation<T>> constraintViolations = validator.validate(inputParam);
            synchronized (violations) {
                if (constraintViolations.isEmpty()) {
                    if (violations.containsKey(inputParam)) {
                        violations.remove(inputParam);
                    }
                    return true;
                } else {
                    violations.put(inputParam, constraintViolations);
                    return false;
                }
            }
        }
    }

    /**
     * Returns the constraint violations set for the specified bean.
     *
     * @param beanParam The bean.  Must not be {@code null}.
     * @return The {@code Set<ConstraintViolation<T>>} for the specified bean.  Returns {@code null} if no constraint
     *         violations exist for the specified bean.
     */
    public Set<ConstraintViolation<T>> getConstraintViolationsForBean(final T beanParam) {
        parameterCondition(notNull(), beanParam, "Bean parameter must not be null to get the constraint violations set.");
        return Collections.unmodifiableSet(violations.get(beanParam));
    }
}
