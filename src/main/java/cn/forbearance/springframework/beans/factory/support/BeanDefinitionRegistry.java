package cn.forbearance.springframework.beans.factory.support;

import cn.forbearance.springframework.beans.BeansException;
import cn.forbearance.springframework.beans.factory.config.BeanDefinition;

/**
 * @author cristina
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册 BeanDefinition
     *
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 获取 BeanDefinition
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 盘带是否包含指定名称的 BeanDefinition
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 返回注册的所有 Bean 的名称
     * @return
     */
    String[] getBeanDefinitionNames();
}
