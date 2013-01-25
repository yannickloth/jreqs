package biz.littlej.jreqs.beanvalidation;

import biz.littlej.jreqs.predicates.Predicate;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.Assert.*;

public class BeanValidationPredicateTest {
    @Test
    public void shouldBeValid() {
        final DomainBean domainBean = new DomainBean();
        domainBean.setSomeString("lalalala");
        domainBean.setSomeInt(56);
        final Predicate<DomainBean> beanValidationPredicate = BeanValidationPredicate.getInstance();
        assertTrue("bean should be valid", beanValidationPredicate.apply(domainBean));
    }

    @Test
    public void shouldBeInvalidIntVariable() {
        final DomainBean domainBean = new DomainBean();
        domainBean.setSomeString("lalalala");
        domainBean.setSomeInt(0);
        final Predicate<DomainBean> beanValidationPredicate = BeanValidationPredicate.getInstance();
        assertFalse("DomainBean should be invalid", beanValidationPredicate.apply(domainBean));
        final Set<ConstraintViolation<DomainBean>> violations =
                ((BeanValidationPredicate<DomainBean>) beanValidationPredicate).getConstraintViolationsForBean(domainBean);
        assertEquals("There should be 1 violation.", 1, violations.size());
    }

    @Test
    public void shouldBeInvalidStringVariable() {
        final DomainBean domainBean = new DomainBean();
        domainBean.setSomeString("  ");
        domainBean.setSomeInt(10);
        final Predicate<DomainBean> beanValidationPredicate = BeanValidationPredicate.getInstance();
        assertFalse("DomainBean should be invalid", beanValidationPredicate.apply(domainBean));
        final Set<ConstraintViolation<DomainBean>> violations =
                ((BeanValidationPredicate<DomainBean>) beanValidationPredicate).getConstraintViolationsForBean(domainBean);
        assertEquals("There should be 1 violation.", 1, violations.size());
    }

    @Test
    public void shouldBeInvalidStringAndIntVariables() {
        final DomainBean domainBean = new DomainBean();
        domainBean.setSomeString("  ");
        domainBean.setSomeInt(10);
        final Predicate<DomainBean> beanValidationPredicate = BeanValidationPredicate.getInstance();
        assertFalse("DomainBean should be invalid", beanValidationPredicate.apply(domainBean));
        final Set<ConstraintViolation<DomainBean>> violations =
                ((BeanValidationPredicate<DomainBean>) beanValidationPredicate).getConstraintViolationsForBean(domainBean);
        assertEquals("There should be 2 violations.", 1, violations.size());
    }
}
