package cn.forbearance.springframework.beans.factory.config;

import cn.forbearance.springframework.beans.factory.HierarchicalBeanFactory;
import cn.forbearance.springframework.beans.factory.PropertyPlaceholderConfigurer;
import cn.forbearance.springframework.util.StringValueResolver;

/**
 * Configuration interface to be implemented by most bean factories. Provides
 * facilities to configure a bean factory, in addition to the bean factory
 * client methods in the {@link cn.forbearance.springframework.beans.factory.BeanFactory}
 * interface
 *
 * @author cristina
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 添加 BeanPostProcessor 实现类，统一处理
     *
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destroySingletons();

    /**
     * 为嵌入的值(如注释属性)添加一个String解析器。
     *
     * @param resolver
     */
    void addEmbeddedValueResolver(StringValueResolver resolver);

    /**
     * Resolve the given embedded value, e.g. an annotation attribute.
     *
     * @param val
     * @return
     */
    String resolverEmbeddedValue(String val);
}
