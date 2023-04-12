package cn.forbearance.springframework.beans.factory;

import cn.hutool.core.bean.BeanException;

/**
 * 实现此接口，能够感知到所属的 BeanFactory
 *
 * @author cristina
 */
public interface BeanFactoryAware extends Aware {

    /**
     * #
     *
     * @param beanFactory
     * @throws BeanException
     */
    void setBeanFactory(BeanFactory beanFactory) throws BeanException;
}
