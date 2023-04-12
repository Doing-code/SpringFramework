package cn.forbearance.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author cristina
 */
public interface MethodMatcher {

    /**
     * 方法匹配，找到表达式范围内匹配到的目标类和方法
     *
     * @param method
     * @param targetClass
     * @return
     */
    boolean matches(Method method, Class<?> targetClass);
}
