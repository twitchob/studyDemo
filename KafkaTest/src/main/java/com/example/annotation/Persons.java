package com.example.annotation;

import java.lang.annotation.*;

@Target(ElementType.ANNOTATION_TYPE)

public @interface Persons {
    //属性
    Person [] value();

}

