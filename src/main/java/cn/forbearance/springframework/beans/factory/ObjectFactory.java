package cn.forbearance.springframework.beans.factory;

import cn.hutool.core.bean.BeanException;

/**
 * 定义一个工厂，该工厂在调用时可以返回Object实例(可能是共享的或独立的)
 *
 * @author cristina
 */
public interface ObjectFactory<T> {

    /**
     * 返回这个工厂管理的对象的实例(可能是共享的，也可能是独立的)。
     *
     * @return
     * @throws BeanException
     */
    T getObject() throws BeanException;
}
