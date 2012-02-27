package biz.littlej.jreqs;

import biz.littlej.jreqs.predicates.Predicate;

/**
 * Provides utility methods to check if conditions are verified.
 * The methods in this class use {@link biz.littlej.jreqs.predicates.Predicate predicates} to check if conditions are verified.
 *
 * @author Yannick LOTH
 * @since 0.1.0
 */
public final class Reqs {
    /**
     * Checks that a method parameter verifies a specific condition using the specified predicate.
     *
     * @param predicateParam The predicate that is used to check if the parameter verifies the condition.
     * @param inputParam     The parameter which must verify the condition.
     * @param messageParam   The explanatory message that will be appended to the exception if the condition is not verified.
     * @param <T>            The type of the parameter.
     * @throws IllegalArgumentException if the condition is not verified.
     */
    public static <T> void parameterCondition(final Predicate<T> predicateParam, final T inputParam, final String messageParam) {
        if (!predicateParam.apply(inputParam)) {
            throw new IllegalArgumentException("Parameter requirement not verified: " + messageParam);
        }
    }

    /**
     * Checks that an object verifies a specific condition using the specified predicate.
     *
     * @param predicateParam The predicate that is used to check if the input object verifies the condition.
     * @param inputParam     The input object which must verify the condition.
     * @param messageParam   The explanatory message that will be appended to the exception if the condition is not verified.
     * @param <T>            The type of the parameter.
     * @throws RequirementException if the condition is not verified.
     */
    public static <T> void condition(final Predicate<T> predicateParam, final T inputParam, final String messageParam) {
        if (!predicateParam.apply(inputParam)) {
            throw new RequirementException("Requirement not verified: " + messageParam);
        }
    }

    /**
     * Checks that an object verifies a specific condition using the specified predicate.
     *
     * @param predicateParam The predicate that is used to check if the input object verifies the condition.
     * @param inputParam     The input object which must verify the condition.
     * @param messageParam   The explanatory message that will be appended to the exception if the condition is not verified.
     * @param <T>            The type of the parameter.
     * @throws PreConditionException if the condition is not verified.
     */
    public static <T> void preCondition(final Predicate<T> predicateParam, final T inputParam, final String messageParam) {
        if (!predicateParam.apply(inputParam)) {
            throw new PreConditionException("Pre-condition not verified: " + messageParam);
        }
    }

    /**
     * Checks that an object verifies a specific condition using the specified predicate.
     *
     * @param predicateParam The predicate that is used to check if the input object verifies the condition.
     * @param inputParam     The input object which must verify the condition.
     * @param messageParam   The explanatory message that will be appended to the exception if the condition is not verified.
     * @param <T>            The type of the parameter.
     * @throws PostConditionException if the condition is not verified.
     */
    public static <T> void postCondition(final Predicate<T> predicateParam, final T inputParam, final String messageParam) {
        if (!predicateParam.apply(inputParam)) {
            throw new PostConditionException("Post-condition not verified: " + messageParam);
        }
    }
}
