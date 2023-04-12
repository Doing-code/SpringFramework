package cn.forbearance.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * @author cristina
 */
public interface Advisor {

    /**
     * 获取拦截器
     *
     * @return
     */
    Advice getAdvice();
}
