package cn.forbearance.springframework.beans.factory;

/**
 * FactoryBean
 *
 * @author cristina
 */
public interface FactoryBean<T> {

    /**
     * 获取对象
     *
     * @return
     * @throws Exception
     */
    T getObject() throws Exception;

    /**
     * 对象类型
     *
     * @return
     */
    Class<?> getObjectType();

    /**
     * 是否单例
     *
     * @return
     */
    boolean isSingleton();
}
