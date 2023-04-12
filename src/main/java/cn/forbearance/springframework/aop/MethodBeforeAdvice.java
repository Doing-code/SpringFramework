package cn.forbearance.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author cristina
 */
public interface MethodBeforeAdvice extends BeforeAdvice {

    /**
     * 前置通知
     *
     * @param method
     * @param args
     * @param target
     * @throws Throwable
     */
    void before(Method method, Object[] args, Object target) throws Throwable;
}
