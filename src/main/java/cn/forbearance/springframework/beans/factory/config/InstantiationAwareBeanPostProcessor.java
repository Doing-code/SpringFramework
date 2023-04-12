package cn.forbearance.springframework.beans.factory.config;

import cn.hutool.core.bean.BeanException;

/**
 * 在目标bean实例化之前应用这个BeanPostProcessor。
 * 返回的bean对象可以是代替目标bean使用的代理，有效地抑制了目标bean的默认实例化。
 *
 * @author cristina
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 在 Bean 对象初始化方法之前执行
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeanException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeanException;
}
