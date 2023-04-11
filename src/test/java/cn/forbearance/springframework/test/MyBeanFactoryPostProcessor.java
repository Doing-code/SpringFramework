package cn.forbearance.springframework.test;

import cn.forbearance.springframework.beans.PropertyValue;
import cn.forbearance.springframework.beans.PropertyValues;
import cn.forbearance.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.forbearance.springframework.beans.factory.config.BeanDefinition;
import cn.forbearance.springframework.beans.factory.config.BeanFactoryPostProcessor;
import cn.hutool.core.bean.BeanException;

/**
 * @author cristina
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "字节跳动"));
    }
}
