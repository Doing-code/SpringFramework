package cn.forbearance.springframework.context;

import cn.forbearance.springframework.beans.factory.HierarchicalBeanFactory;
import cn.forbearance.springframework.beans.factory.ListableBeanFactory;
import cn.forbearance.springframework.core.io.ResourceLoader;

/**
 * Central interface<p>
 * Central interface to provide configuration for an application.
 * This is read-only while the application is running, but may be
 * reloaded if the implementation supports this
 *
 * @author cristina
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
