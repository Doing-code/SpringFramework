package cn.forbearance.springframework.aop;

/**
 * Pointcut 获取 JoinPoint
 * Advice 决定 JoinPoint 执行什么操作
 *
 * @author cristina
 */
public interface PointcutAdvisor extends Advisor {

    /**
     * 获取切入点
     *
     * @return
     */
    Pointcut getPointcut();
}
