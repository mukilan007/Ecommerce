package com.util;

import java.lang.annotation.*;
import java.util.Map;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.LOCAL_VARIABLE, ElementType.FIELD, ElementType.METHOD})
public @interface insertCateory {
    String cateoryname = null;
    Map<Class<? extends Annotation>, Annotation> map = null;
    boolean checkCateory() default false;
}
