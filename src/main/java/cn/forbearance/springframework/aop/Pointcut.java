package cn.forbearance.springframework.aop;

/**
 * 切点
 * @author cristina
 */
public interface Pointcut {

    /**
     * #
     * @return
     */
    ClassFilter getClassFilter();

    /**
     * #
     * @return
     */
    MethodMatcher getMethodMatcher();
}
