package cn.forbearance.springframework.context;

import cn.hutool.core.bean.BeanException;

/**
 * 配置应用上下文<p>
 * SPI interface to be implemented by most if not all application contexts.
 * Provides facilities to configure an application context in addition
 * to the application context client methods in the
 * {@link ApplicationContext} interface.
 *
 * @author cristina
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器<p>
     * Load or refresh the persistent representation of the configuration, which
     * might be from Java-based configuration, an XML file, a properties file, a
     * relational database schema, or some other format.
     * <p>As this is a startup method, it should destroy already created singletons
     * if it fails, to avoid dangling resources. In other words, after invocation
     * of this method, either all or no singletons at all should be instantiated.
     *
     * @throws BeanException
     */
    void refresh() throws BeanException;
}
