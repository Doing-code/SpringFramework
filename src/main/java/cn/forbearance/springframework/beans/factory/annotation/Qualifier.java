package cn.forbearance.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * 一般配合 @Autowired 使用
 *
 * @author cristina
 */
@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Qualifier {

    String value() default "";
}
