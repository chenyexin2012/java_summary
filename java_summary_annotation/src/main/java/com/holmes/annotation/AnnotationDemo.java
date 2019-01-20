package com.holmes.annotation;

/**
 * @Description:
 * @Author: holmes
 * @Version: 1.0.0
*/
@ClassAnnotation("annotationDemo")
public class AnnotationDemo {

    @FieldAnnotation("field")
    private String field;

    @MehodAnnotation(name = "myAnnotation", level = 1)
    @SingleAttributeAnnotation("value")
    public void method() {
    }
}
