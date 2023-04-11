package cn.forbearance.springframework.beans.factory.config;

import cn.forbearance.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.hutool.core.bean.BeanException;

/**
 * 允许自定义修改 BeanDefinition 属性信息
 *
 * @author cristina
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的 BeanDefinition 加载后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *
     * @param beanFactory
     * @throws BeanException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException;
}
