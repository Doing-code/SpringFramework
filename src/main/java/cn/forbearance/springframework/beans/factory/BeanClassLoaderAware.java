package cn.forbearance.springframework.beans.factory;

/**
 * 感知所属的 ClassLoader
 * @author cristina
 */
public interface BeanClassLoaderAware extends Aware {

    /**
     * #
     * @param classLoader
     */
    void setBeanClassLoader(ClassLoader classLoader);
}
