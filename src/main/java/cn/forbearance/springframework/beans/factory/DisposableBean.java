package cn.forbearance.springframework.beans.factory;

/**
 * @author cristina
 */
public interface DisposableBean {

    /**
     * Bean 销毁操作
     *
     * @throws Exception
     */
    void destroy() throws Exception;
}
