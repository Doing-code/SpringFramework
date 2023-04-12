package cn.forbearance.springframework.beans.factory;

/**
 * @author cristina
 */
public interface BeanNameAware extends Aware {

    /**
     * #
     *
     * @param beanName
     */
    void setBeanName(String beanName);
}
