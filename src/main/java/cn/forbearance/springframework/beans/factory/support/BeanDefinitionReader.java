package cn.forbearance.springframework.beans.factory.support;

import cn.forbearance.springframework.beans.BeansException;
import cn.forbearance.springframework.core.io.Resource;
import cn.forbearance.springframework.core.io.ResourceLoader;

/**
 * @author cristina
 */
public interface BeanDefinitionReader {

    /**
     * bean 注册
     *
     * @return
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 资源加载
     *
     * @return
     */
    ResourceLoader getResourceLoader();

    /**
     * 加载 Bean 定义
     *
     * @param resource
     * @throws BeansException
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    /**
     * 加载 Bean 定义
     *
     * @param resources
     * @throws BeansException
     */
    void loadBeanDefinitions(Resource... resources) throws BeansException;

    /**
     * 加载 Bean 定义
     *
     * @param location
     * @throws BeansException
     */
    void loadBeanDefinitions(String location) throws BeansException;

    /**
     * 加载 Bean 定义
     *
     * @param location
     * @throws BeansException
     */
    void loadBeanDefinitions(String... location) throws BeansException;
}
