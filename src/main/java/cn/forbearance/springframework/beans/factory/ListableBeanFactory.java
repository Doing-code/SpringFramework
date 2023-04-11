package cn.forbearance.springframework.beans.factory;

import cn.hutool.core.bean.BeanException;

import java.util.Map;

/**
 * Extension of the {@link BeanFactory} interface to be implemented by bean factories
 * that can enumerate all their bean instances, rather than attempting bean lookup
 * by name one by one as requested by clients. BeanFactory implementations that
 * preload all their bean definitions (such as XML-based factories) may implement
 * this interface
 * <p>
 * BeanFactory 的扩展接口
 *
 * @author cristina
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回 Bean 实例
     *
     * @param type
     * @param <T>
     * @return
     * @throws BeanException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException;

    /**
     * 返回注册的所有 Bean 名称
     *
     * @return
     */
    String[] getBeanDefinitionNames();
}
