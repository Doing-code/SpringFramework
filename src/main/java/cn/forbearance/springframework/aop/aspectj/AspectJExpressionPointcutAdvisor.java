package cn.forbearance.springframework.aop.aspectj;

import cn.forbearance.springframework.aop.Pointcut;
import cn.forbearance.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * 对 切面、拦截方法、拦截表达式进行包装
 *
 * @author cristina
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    /**
     * 切面
     */
    private AspectJExpressionPointcut pointcut;

    /**
     * 拦截方法
     */
    private Advice advice;

    /**
     * 表达式
     */
    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
}
