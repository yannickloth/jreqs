package biz.littlej.jreqs.beanvalidation;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

public class DomainBean {
    @NotBlank
    private String someString;
    @Min(10)
    private int someInt;

    public String getSomeString() {
        return someString;
    }

    public void setSomeString(final String someStringParam) {
        someString = someStringParam;
    }

    public int getSomeInt() {
        return someInt;
    }

    public void setSomeInt(final int someIntParam) {
        someInt = someIntParam;
    }
}
