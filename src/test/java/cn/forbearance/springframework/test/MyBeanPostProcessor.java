package cn.forbearance.springframework.test;

import cn.forbearance.springframework.beans.factory.config.BeanPostProcessor;
import cn.hutool.core.bean.BeanException;

/**
 * @author cristina
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }
}
