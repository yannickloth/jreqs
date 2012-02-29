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
package biz.littlej.jreqs;

import org.junit.Test;

import static biz.littlej.jreqs.Reqs.*;
import static biz.littlej.jreqs.predicates.Predicates.alwaysFalse;
import static biz.littlej.jreqs.predicates.Predicates.alwaysTrue;

/**
 * Unit tests for {@link Reqs}.
 *
 * @author Yannick LOTH
 * @since 0.1.0
 */
public class ReqsTest {
    @Test(expected = RequirementException.class)
    public void testConditionAlwaysFalseWithNullInput() {
        condition(alwaysFalse(), null, "AlwaysFalse predicate does not evaluate to true for a null input, as expected.");
    }

    @Test(expected = RequirementException.class)
    public void testConditionAlwaysFalseWithObjectInput() {
        condition(alwaysFalse(), new Object(), "AlwaysFalse predicate does not evaluate to true for an object input, as expected.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParameterConditionAlwaysFalseWithNullInput() {
        parameterCondition(alwaysFalse(), null, "AlwaysFalse predicate does not evaluate to true for a null input, as expected.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParameterConditionAlwaysFalseWithObjectInput() {
        parameterCondition(alwaysFalse(), new Object(), "AlwaysFalse predicate does not evaluate to true for an object input, as expected.");
    }

    @Test(expected = PreConditionException.class)
    public void testPreConditionAlwaysFalseWithNullInput() {
        preCondition(alwaysFalse(), null, "AlwaysFalse predicate does not evaluate to true for a null input, as expected.");
    }

    @Test(expected = PreConditionException.class)
    public void testPreConditionAlwaysFalseWithObjectInput() {
        preCondition(alwaysFalse(), new Object(), "AlwaysFalse predicate does not evaluate to true for an object input, as expected.");
    }

    @Test(expected = PostConditionException.class)
    public void testPostConditionAlwaysFalseWithNullInput() {
        postCondition(alwaysFalse(), null, "AlwaysFalse predicate does not evaluate to true for a null input, as expected.");
    }

    @Test(expected = PostConditionException.class)
    public void testPostConditionAlwaysFalseWithObjectInput() {
        postCondition(alwaysFalse(), new Object(), "AlwaysFalse predicate does not evaluate to true for an object input, as expected.");
    }

    @Test
    public void testConditionAlwaysTrueWithNullInput() {
        condition(alwaysTrue(), null, "AlwaysTrue predicate does evaluate to true for a null input, as expected.");
    }

    @Test
    public void testConditionAlwaysTrueWithObjectInput() {
        condition(alwaysTrue(), new Object(), "AlwaysTrue predicate does evaluate to true for an object input, as expected.");
    }

    @Test
    public void testParameterConditionAlwaysTrueWithNullInput() {
        parameterCondition(alwaysTrue(), null, "AlwaysTrue predicate does evaluate to true for a null input, as expected.");
    }

    @Test
    public void testParameterConditionAlwaysTrueWithObjectInput() {
        parameterCondition(alwaysTrue(), new Object(), "AlwaysTrue predicate does evaluate to true for an object input, as expected.");
    }

    @Test
    public void testPreConditionAlwaysTrueWithNullInput() {
        preCondition(alwaysTrue(), null, "AlwaysTrue predicate does evaluate to true for a null input, as expected.");
    }

    @Test
    public void testPreConditionAlwaysTrueWithObjectInput() {
        preCondition(alwaysTrue(), new Object(), "AlwaysTrue predicate does evaluate to true for an object input, as expected.");
    }

    @Test
    public void testPostConditionAlwaysTrueWithNullInput() {
        postCondition(alwaysTrue(), null, "AlwaysTrue predicate does evaluate to true for a null input, as expected.");
    }

    @Test
    public void testPostConditionAlwaysTrueWithObjectInput() {
        postCondition(alwaysTrue(), new Object(), "AlwaysTrue predicate does evaluate to true for an object input, as expected.");
    }
}
