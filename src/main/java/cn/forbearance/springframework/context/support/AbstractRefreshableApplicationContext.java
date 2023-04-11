package cn.forbearance.springframework.context.support;

import cn.forbearance.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.forbearance.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.hutool.core.bean.BeanException;

/**
 * ApplicationContext 的实现
 * <p>
 * 这样的上下文将由一组用于加载bean定义的配置位置驱动
 *
 * @author cristina
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeanException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    /**
     * Load bean definitions into the given bean factory, typically through
     * delegating to one or more bean definition readers
     *
     * @param beanFactory
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
