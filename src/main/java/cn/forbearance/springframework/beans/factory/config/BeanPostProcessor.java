package cn.forbearance.springframework.beans.factory.config;

import cn.hutool.core.bean.BeanException;

/**
 * 提供修改新实例化 Bean 对象的扩展点
 *
 * @author cristina
 */
public interface BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化方法之前执行
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeanException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException;

    /**
     * 在 Bean 对象执行初始化方法之后执行
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeanException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException;
}
