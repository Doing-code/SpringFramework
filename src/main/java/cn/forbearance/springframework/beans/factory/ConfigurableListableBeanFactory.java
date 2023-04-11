package cn.forbearance.springframework.beans.factory;

import cn.forbearance.springframework.beans.BeansException;
import cn.forbearance.springframework.beans.factory.config.AutowireCapableBeanFactory;
import cn.forbearance.springframework.beans.factory.config.BeanDefinition;
import cn.forbearance.springframework.beans.factory.config.BeanPostProcessor;
import cn.forbearance.springframework.beans.factory.config.ConfigurableBeanFactory;
import cn.hutool.core.bean.BeanException;

/**
 * Configuration interface to be implemented by most listable bean factories.
 * In addition to {@link ConfigurableBeanFactory}, it provides facilities to
 * analyze and modify bean definitions, and to pre-instantiate singletons
 * <p>
 * 配置接口将由大多数可列出的bean工厂实现, 除了 ConfigurableBeanFactory
 * <p>
 * 提供了分析和修改bean定义以及预实例化单例的功能
 *
 * @author cristina
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    /**
     * 获取 Bean 定义
     *
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 预处理单实例 Bean
     *
     * @throws BeanException
     */
    void preInstantiateSingletons() throws BeanException;

    /**
     * 添加 BeanPostProcessor 实现类，统一处理
     *
     * @param beanPostProcessor
     */
    @Override
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
