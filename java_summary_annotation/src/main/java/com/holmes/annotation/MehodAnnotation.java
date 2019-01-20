package com.holmes.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface MehodAnnotation {

    public enum Status {
        READY, RUNNING, BLOCKING, FINISHED
    }

    String name();

    int level();

    Status status() default Status.READY;
}
