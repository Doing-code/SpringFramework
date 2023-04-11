package cn.forbearance.springframework.beans.factory.config;

/**
 * 获取单例对象
 *
 * @author cristina
 */
public interface SingletonBeanRegistry {

    /**
     * #
     *
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);
}
