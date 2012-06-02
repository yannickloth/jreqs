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

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Unit tests for {@link biz.littlej.jreqs.predicates.PredicateOperations}.
 *
 * @author Yannick LOTH
 * @since 0.1.0
 */
public class PredicateOperationsTest {
    @Test
    public void testAnd() {
        {
            final Object o = new Object();
            final Predicate p = PredicateOperations.and(Predicates.alwaysTrue(), Predicates.alwaysTrue());
            assertTrue("Operation AND with 2 AlwaysTrue predicate must always evaluate to true.", p.apply(o));
           // assertFalse("Operation AND predicate must always evaluate to false when applied on null.", p.apply(null));
        }
    }
}
