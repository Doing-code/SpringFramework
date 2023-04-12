package cn.forbearance.springframework.beans.factory.support;

import cn.forbearance.springframework.beans.BeansException;
import cn.forbearance.springframework.beans.factory.FactoryBean;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册 FactoryBean
 *
 * @author cristina
 */
public abstract class FactoryBeanRegisterSupport extends DefaultSingletonBeanRegistry {

    /**
     * 向缓存存放单例 Bean
     */
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<String, Object>();

    protected Object getCacheObjectForFactoryBean(String beanName) {
        Object obj = this.factoryBeanObjectCache.get(beanName);
        return obj != NULL_OBJECT ? obj : null;
    }

    protected Object getObjectFromFactoryBean(FactoryBean factoryBean, String beanName) {
        if (factoryBean.isSingleton()) {
            Object obj = this.factoryBeanObjectCache.get(beanName);
            if (null == obj) {
                obj = doGetObjectFromFactoryBean(factoryBean, beanName);
                this.factoryBeanObjectCache.put(beanName, (Objects.nonNull(obj) ? obj : NULL_OBJECT));
            }
            return obj != NULL_OBJECT ? obj : null;
        }
        return doGetObjectFromFactoryBean(factoryBean, beanName);
    }

    /**
     * FactoryBean#getObject()
     *
     * @param factoryBean
     * @param beanName
     * @return
     */
    private Object doGetObjectFromFactoryBean(final FactoryBean factoryBean, final String beanName) {
        try {
            return factoryBean.getObject();
        } catch (Exception e) {
            throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", e);
        }
    }
}
