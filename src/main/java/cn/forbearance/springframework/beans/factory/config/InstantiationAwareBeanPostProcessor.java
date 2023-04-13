package cn.forbearance.springframework.beans.factory.config;

import cn.forbearance.springframework.beans.BeansException;
import cn.forbearance.springframework.beans.PropertyValues;
import cn.hutool.core.bean.BeanException;

/**
 * 在目标bean实例化之前应用这个BeanPostProcessor。
 * 返回的bean对象可以是代替目标bean使用的代理，有效地抑制了目标bean的默认实例化。
 *
 * @author cristina
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 在 Bean 对象初始化方法之前执行
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeanException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeanException;

    /**
     * #
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException;

    /**
     * Bean 实例化之后，属性赋值之前执行
     *
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException;

    /**
     * 获取一个引用，以便尽早访问指定的bean， 通常用于解析循环引用
     * <p>
     * 在 Spring 中由 SmartInstantiationAwareBeanPostProcessor#getEarlyBeanReference 提供
     *
     * @param bean
     * @param beanName
     * @return
     */
    default Object getEarlyBeanReference(Object bean, String beanName) {
        return bean;
    }
}
