package cn.forbearance.springframework.beans.factory;

/**
 * @author cristina
 */
public interface InitializingBean {

    /**
     * Bean 属性填充后调用
     *
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
