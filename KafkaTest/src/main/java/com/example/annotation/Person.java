package com.example.annotation;

import java.lang.annotation.*;


/**
 * @author zzype
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Person {
    String role() default "coder";

}