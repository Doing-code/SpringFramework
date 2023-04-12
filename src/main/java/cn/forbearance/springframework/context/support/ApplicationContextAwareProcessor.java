package cn.forbearance.springframework.context.support;

import cn.forbearance.springframework.beans.factory.config.BeanPostProcessor;
import cn.forbearance.springframework.context.ApplicationContext;
import cn.forbearance.springframework.context.ApplicationContextAware;
import cn.hutool.core.bean.BeanException;

/**
 * @author cristina
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext context;

    public ApplicationContextAwareProcessor(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(context);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }
}
