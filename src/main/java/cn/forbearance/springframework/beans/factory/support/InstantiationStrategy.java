package cn.forbearance.springframework.beans.factory.support;

import cn.forbearance.springframework.beans.BeansException;
import cn.forbearance.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 在实例化接口 instantiate 方法中添加必要的入参信息, eg beanDefinition、beanName、ctor、args
 * 策略接口，策略调用 [JDK / Cglib]
 *
 * @author cristina
 */
public interface InstantiationStrategy {

    /**
     * 构造函数入参填充
     *
     * @param beanDefinition
     * @param beanName
     * @param ctor           / java.lang.reflect
     * @param args           / 入参信息
     * @return
     * @throws BeansException
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;
}
