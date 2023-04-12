package cn.forbearance.springframework.context.support;

import cn.forbearance.springframework.beans.BeansException;
import cn.forbearance.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.forbearance.springframework.beans.factory.config.BeanFactoryPostProcessor;
import cn.forbearance.springframework.beans.factory.config.BeanPostProcessor;
import cn.forbearance.springframework.context.ConfigurableApplicationContext;
import cn.forbearance.springframework.core.io.DefaultResourceLoader;
import cn.hutool.core.bean.BeanException;

import java.util.Map;

/**
 * 继承 DefaultResourceLoader 主要是用于处理 spring.xml 配置资源的加载.
 * 完成对 Bean 的加载、注册、实例化、预处理等操作
 *
 * @author cristina
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeanException {
        // 1、创建 BeanFactory 并加载 BeanDefinition
        refreshBeanFactory();

        // 2、获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3、在 Bean 实例化之前执行 BeanFactoryPostProcessor (Invoke factory processors registered as beans in the context.)
        invokeBeanFactoryPostProcessors(beanFactory);

        // 4、BeanPostProcessor 提前于其他 Bean 对象实例化之前执行注册操作
        registryBeanPostProcessors(beanFactory);

        // 5、提前实例化单例 Bean
        beanFactory.preInstantiateSingletons();
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }

    /**
     * 子类扩展<p>
     * Subclasses must implement this method to perform the actual configuration load.
     * The method is invoked by {@link #refresh()} before any other initialization work.
     * <p>A subclass will either create a new bean factory and hold a reference to it,
     * or return a single BeanFactory instance that it holds. In the latter case, it will
     * usually throw an IllegalStateException if refreshing the context more than once.
     *
     * @throws BeanException
     */
    protected abstract void refreshBeanFactory() throws BeanException;

    /**
     * 子类扩展<p>
     * Subclasses must return their internal bean factory here. They should implement the
     * lookup efficiently, so that it can be called repeatedly without a performance penalty.
     * <p>Note: Subclasses should check whether the context is still active before
     * returning the internal bean factory. The internal factory should generally be
     * considered unavailable once the context has been closed.
     *
     * @return
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * Instantiate and invoke all registered BeanFactoryPostProcessor beans,
     * respecting explicit order if given.
     *
     * @param beanFactory
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor bp : beanFactoryPostProcessorMap.values()) {
            bp.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * Instantiate and register all BeanPostProcessor beans,
     * respecting explicit order if given.
     *
     * @param beanFactory
     */
    private void registryBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }
}
