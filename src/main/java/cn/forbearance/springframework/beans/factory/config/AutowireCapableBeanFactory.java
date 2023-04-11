package cn.forbearance.springframework.beans.factory.config;

import cn.forbearance.springframework.beans.factory.BeanFactory;
import cn.hutool.core.bean.BeanException;

/**
 * Extension of the {@link BeanFactory}
 * interface to be implemented by bean factories that are capable of
 * autowiring, provided that they want to expose this functionality for
 * existing bean instances.
 *
 * @author cristina
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * BeanPostProcessor 初始化方法之前执行
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeanException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeanException;

    /**
     * BeanPostProcessor 初始化方法之后执行
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeanException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeanException;
}
