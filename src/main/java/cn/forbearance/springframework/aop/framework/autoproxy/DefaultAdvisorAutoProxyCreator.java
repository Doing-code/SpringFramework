package cn.forbearance.springframework.aop.framework.autoproxy;

import cn.forbearance.springframework.aop.*;
import cn.forbearance.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import cn.forbearance.springframework.aop.framework.ProxyFactory;
import cn.forbearance.springframework.beans.BeansException;
import cn.forbearance.springframework.beans.PropertyValues;
import cn.forbearance.springframework.beans.factory.BeanFactory;
import cn.forbearance.springframework.beans.factory.BeanFactoryAware;
import cn.forbearance.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import cn.forbearance.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.hutool.core.bean.BeanException;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;

/**
 * @author cristina
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeanException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeanException {
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return null;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }

    /**
     * 把创建 AOP 代理的操作从 postProcessBeforeInstantiation 移动到
     * postProcessAfterInitialization中去。
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeanException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
        if (isInfrastructureClass(bean.getClass())) return null;

        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(bean.getClass())) continue;

            AdvisedSupport advisedSupport = new AdvisedSupport();
            TargetSource targetSource = new TargetSource(bean);
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);

            return new ProxyFactory(advisedSupport).getProxy();
        }
        return bean;
    }
}
