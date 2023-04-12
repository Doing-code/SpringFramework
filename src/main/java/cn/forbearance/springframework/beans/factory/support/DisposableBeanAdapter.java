package cn.forbearance.springframework.beans.factory.support;

import cn.forbearance.springframework.beans.factory.DisposableBean;
import cn.forbearance.springframework.beans.factory.InitializingBean;
import cn.forbearance.springframework.beans.factory.config.BeanDefinition;
import cn.hutool.core.bean.BeanException;
import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Method;

/**
 * 销毁方法适配器 （接口和配置）
 * <p>
 * 适配器的作用是统一接口进行销毁
 * <p>
 * 销毁动作是由 AbstractApplicationContext 在注册虚拟机钩子后，虚拟机关闭前执行的操作的动作
 *
 * @author cristina
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String beanName;

    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        // 实现 InitializingBean 接口
        if (bean instanceof DisposableBean)
            ((InitializingBean) bean).afterPropertiesSet();

        // destroy-method 标签 {判断是为了避免二次执行销毁方法}
        if (StrUtil.isNotEmpty(destroyMethodName) &&
                !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (null == destroyMethod) {
                throw new BeanException("Could not find an init method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}
