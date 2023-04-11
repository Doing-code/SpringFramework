package cn.forbearance.springframework.beans.factory;

import cn.forbearance.springframework.beans.BeansException;

/**
 * Bean 的注册, 注册 Bean 的定义信息
 *
 * @author cristina
 */
public interface BeanFactory {

    /**
     * 获取 bean 实例
     *
     * @param name
     * @return
     * @throws BeansException
     */
    Object getBean(String name) throws BeansException;

    /**
     * 获取有参构造 bean 实例
     *
     * @param name
     * @param args
     * @return
     * @throws BeansException
     */
    Object getBean(String name, Object... args) throws BeansException;

    /**
     * 返回指定类型 Bean
     *
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
