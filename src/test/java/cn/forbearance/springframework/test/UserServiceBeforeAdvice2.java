package cn.forbearance.springframework.test;

import cn.forbearance.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author cristina
 */
public class UserServiceBeforeAdvice2 implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法2：" + method.getName());
    }
}
