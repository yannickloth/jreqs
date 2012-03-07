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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@link Predicates}.
 *
 * @author Yannick LOTH
 * @since 0.1.0
 */
public class PredicatesTest {
    @Test
    public void testAlwaysFalse() {
        {
            final Object o = new Object();
            assertFalse("AlwaysFalse predicate must always evaluate to false.", Predicates.alwaysFalse().apply(o));
        }
        assertFalse("AlwaysFalse predicate must always evaluate to false.", Predicates.alwaysFalse().apply(null));
    }

    @Test
    public void testAlwaysTrue() {
        {
            final Object o = new Object();
            assertTrue("AlwaysTrue predicate must always evaluate to true.", Predicates.alwaysTrue().apply(o));
        }
        assertTrue("AlwaysTrue predicate must always evaluate to true.", Predicates.alwaysTrue().apply(null));
    }

    @Test
    public void testZero() {
        {
            final int zero = 0;
            assertTrue("0 must evaluate to true.", Predicates.zero().apply(zero));
        }
        {
            final BigInteger zeroBigInteger = BigInteger.ZERO;
            assertTrue("zero BigInteger must evaluate to true.", Predicates.zero().apply(zeroBigInteger));
        }
        {
            final BigDecimal zeroBigDecimal = BigDecimal.ZERO;
            assertTrue("zero BigDecimal must evaluate to true.", Predicates.zero().apply(zeroBigDecimal));
        }
        {
            final int one = 1;
            assertFalse("1 must evaluate to false.", Predicates.zero().apply(one));
        }
        {
            final BigInteger oneBigInteger = BigInteger.ONE;
            assertFalse("1 BigInteger must evaluate to false.", Predicates.zero().apply(oneBigInteger));
        }
        {
            final BigDecimal oneBigDecimal = BigDecimal.ONE;
            assertFalse("1 BigDecimal must evaluate to false.", Predicates.zero().apply(oneBigDecimal));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroWithNullInput() {
        {
            Predicates.zero().apply(null);
        }
    }

    @Test
    public void testIsNullWithNullInput() {
        {
            assertTrue("Null input should evaluate to true.", Predicates.isNull().apply(null));
        }
    }

    @Test
    public void testIsNullWithNotNullInput() {
        {
            assertFalse("Not null input should evaluate to false.", Predicates.isNull().apply(new Object()));
        }
    }

    @Test
    public void testNotNullWithNotNullInput() {
        {
            assertTrue("Not null input should evaluate to true.", Predicates.notNull().apply(new Object()));
        }
    }

    @Test
    public void testNotNullWithNullInput() {
        {
            assertFalse("Null input should evaluate to false.", Predicates.notNull().apply(null));
        }
    }

    @Test
    public void testFuture() {
        {
            final Calendar time = Calendar.getInstance();
            time.add(Calendar.HOUR, 1);
            assertTrue("Next hour should evaluate to true.", Predicates.future().apply(time));
        }
        {
            final Calendar time = Calendar.getInstance();
            time.add(Calendar.HOUR, -1);
            assertFalse("Previous hour should evaluate to false.", Predicates.future().apply(time));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFutureWithNullInput() {
        Predicates.future().apply(null);
    }

    @Test
    public void testPast() {
        {
            final Calendar time = Calendar.getInstance();
            time.add(Calendar.HOUR, -1);
            assertTrue("Previous hour should evaluate to true.", Predicates.past().apply(time));
        }
        {
            final Calendar time = Calendar.getInstance();
            time.add(Calendar.HOUR, 1);
            assertFalse("Next hour should evaluate to false.", Predicates.past().apply(time));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPastWithNullInput() {
        Predicates.past().apply(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstanceOfWithNullInput() {
        Predicates.instanceOf(Exception.class).apply(null);
    }

    @Test
    public void testInstanceOf() {
        {
            final Calendar time = Calendar.getInstance();
            assertTrue("Calendar object should evaluate to true.", Predicates.instanceOf(Calendar.class).apply(time));
        }
        {
            assertFalse("Date object should evaluate to false.", Predicates.instanceOf(Calendar.class).apply(new Date()));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAllInstanceOfWithNullInput() {
        Predicates.allInstanceOf(Exception.class).apply(null);
    }

    @Test
    public void testAllInstanceOf() {
        {
            final List<?> list = new ArrayList();
            assertTrue("Empty list should evaluate to true.", Predicates.allInstanceOf(Calendar.class).apply(list));
        }
        {
            final List<Calendar> list = new ArrayList<Calendar>();
            list.add(Calendar.getInstance());
            list.add(Calendar.getInstance());
            assertTrue("List of Calendar should evaluate to true.", Predicates.allInstanceOf(Calendar.class).apply(list));
        }
        {
            final List<Date> list = new ArrayList<Date>();
            list.add(new Date());
            list.add(new Date());
            assertFalse("List of Date should evaluate to false.", Predicates.allInstanceOf(Calendar.class).apply(list));
        }
        {
            final List list = new ArrayList();
            list.add(new Date());
            list.add(Calendar.getInstance());
            assertFalse("List of Calendar and Date should evaluate to false.", Predicates.allInstanceOf(Calendar.class).apply(list));
        }
        {
            // Same as previous, but with reversed order, to make sure that order does not matter in evaluation.
            final List list = new ArrayList();
            list.add(Calendar.getInstance());
            list.add(new Date());
            assertFalse("List of Calendar and Date should evaluate to false.", Predicates.allInstanceOf(Calendar.class).apply(list));
        }
    }

    @Test
    public void testBlankString() {
        {
            assertTrue("Empty String should evaluate to true.", Predicates.blankString().apply(""));
        }
        {
            assertTrue("Null should evaluate to true.", Predicates.blankString().apply(null));
        }
        {
            assertTrue("String with only spaces should evaluate to true.", Predicates.blankString().apply("   "));
        }
        {
            assertTrue("String with only tabs should evaluate to true.", Predicates.blankString().apply("\t"));
        }
        {
            assertTrue("String with only spaces and tabs should evaluate to true.", Predicates.blankString().apply("   \t"));
        }
        {
            assertFalse("String with content should evaluate to false.", Predicates.blankString().apply("notEmpty"));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyCharSequenceWithNullInput() {
        Predicates.emptyCharSequence().apply(null);
    }

    @Test
    public void testEmptyCharSequence() {
        assertTrue("Empty String should evaluate to true.", Predicates.emptyCharSequence().apply(""));
        assertFalse("String with one space character should evaluate to false.", Predicates.emptyCharSequence().apply(" "));
        assertFalse("String with many spaces character should evaluate to false.", Predicates.emptyCharSequence().apply("    "));
        assertFalse("String with one tab character should evaluate to false.", Predicates.emptyCharSequence().apply("\t"));
        assertFalse("String with some text should evaluate to false.", Predicates.emptyCharSequence().apply("String with some text..."));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyCollectionWithNullInput() {
        Predicates.emptyCollection().apply(null);
    }

    @Test
    public void testEmptyCollection() {
        assertTrue("Empty List should evaluate to true.", Predicates.emptyCollection().apply(new ArrayList()));
        assertFalse("Integer list should evaluate to false.", Predicates.emptyCollection().apply(Arrays.asList(new int[]{1, 2, 3})));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAllIterableElementsWithNullInput() {
        Predicates.allIterableElements(Predicates.instanceOf(String.class)).apply(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAllIterableElementsWithNullPredicate() {
        Predicates.allIterableElements(Predicates.instanceOf(null)).apply(new ArrayList());
    }

    @Test
    public void testAllIterableElements() {
        {
            final List list = new ArrayList();
            list.add("eee");
            list.add(new Object());
            assertFalse("List with one not String object should evaluate to false.", Predicates.allIterableElements(Predicates.instanceOf(String.class)).apply(list));
        }
        {
            final List list = new ArrayList();
            list.add("eee");
            list.add("aaa");
            assertTrue("List with only String objects should evaluate to true.", Predicates.allIterableElements(Predicates.instanceOf(String.class)).apply(list));
        }
        {
            assertTrue("Empty list should evaluate to true.", Predicates.allIterableElements(Predicates.instanceOf(String.class)).apply(new ArrayList()));
        }
        {
            final List list = new ArrayList();
            list.add("eee");
            list.add("aaa");
            assertTrue("List with only String objects should evaluate to true.", Predicates.allIterableElements(Predicates.instanceOf(CharSequence.class)).apply(list));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoIterableElementsWithNullInput() {
        Predicates.noIterableElement(Predicates.instanceOf(String.class)).apply(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoIterableElementsWithNullPredicate() {
        Predicates.noIterableElement(Predicates.instanceOf(null)).apply(new ArrayList());
    }

    @Test
    public void testNoIterableElements() {
        {
            final List list = new ArrayList();
            list.add("eee");
            list.add(new Object());
            assertFalse("List with one String object should evaluate to false.", Predicates.noIterableElement(Predicates.instanceOf(String.class)).apply(list));
        }
        {
            final List list = new ArrayList();
            list.add(new Object());
            list.add(new Object());
            assertTrue("List with only objects should evaluate to true.", Predicates.noIterableElement(Predicates.instanceOf(String.class)).apply(list));
        }
        {
            assertTrue("Empty list should evaluate to true.", Predicates.noIterableElement(Predicates.instanceOf(String.class)).apply(new ArrayList()));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOneIterableElementsWithNullInput() {
        Predicates.oneIterableElement(Predicates.instanceOf(String.class)).apply(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOneIterableElementsWithNullPredicate() {
        Predicates.oneIterableElement(Predicates.instanceOf(null)).apply(new ArrayList());
    }

    @Test
    public void testOneIterableElements() {
        {
            final List list = new ArrayList();
            list.add(new Object());
            list.add(new Object());
            assertFalse("List with 2 not String object should evaluate to false.", Predicates.oneIterableElement(Predicates.instanceOf(String.class)).apply(list));
        }
        {
            final List list = new ArrayList();
            list.add("eee");
            list.add("aaa");
            assertFalse("List with 2 String objects should evaluate to false.", Predicates.oneIterableElement(Predicates.instanceOf(String.class)).apply(list));
        }
        {
            assertFalse("Empty list should evaluate to false.", Predicates.oneIterableElement(Predicates.instanceOf(String.class)).apply(new ArrayList()));
        }
        {
            final List list = new ArrayList();
            list.add("eee");
            list.add(new Object());
            assertTrue("List with one String object should evaluate to true.", Predicates.oneIterableElement(Predicates.instanceOf(CharSequence.class)).apply(list));
        }
    }
}
