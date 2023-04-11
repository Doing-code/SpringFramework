package cn.forbearance.springframework.beans.factory.support;

import cn.forbearance.springframework.beans.PropertyValue;
import cn.forbearance.springframework.beans.PropertyValues;
import cn.forbearance.springframework.beans.factory.config.AutowireCapableBeanFactory;
import cn.forbearance.springframework.beans.factory.config.BeanDefinition;
import cn.forbearance.springframework.beans.BeansException;
import cn.forbearance.springframework.beans.factory.config.BeanPostProcessor;
import cn.forbearance.springframework.beans.factory.config.BeanReference;
import cn.hutool.core.bean.BeanException;
import cn.hutool.core.bean.BeanUtil;

import java.lang.reflect.Constructor;

/**
 * 实现 Bean 的实例化操作, 并放到单例对象的缓存中.
 *
 * @author cristina
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean;
        try {
            // TODO 有参构造的对象怎么创建?，参考SpringBean容器源码的实现方式，BeanFactory添加一个 Object getBean(String name, Object... args)，JDK（DeclaredConstructor） 或 Cglib（基于ASM）
            bean = createBeanInstance(beanDefinition, beanName, args);
            // 填充属性 TODO 还未处理循环依赖
            applyPropertyValues(beanName, bean, beanDefinition);
            // 执行 Bean 初始化方法和 BeanPostProcessor 的前置和后置方法
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length != args.length) {
                continue;
            }
            constructorToUse = ctor;
            break;
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    /**
     * 属性填充
     * TODO 还未解决循环依赖
     *
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        try {
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference) {
                    value = getBean(((BeanReference) value).getBeanName());
                }
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values: " + beanName);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 执行 BeanPostProcessor 前置
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        // 执行初始化方法
        invokeInitMethods(beanName,wrappedBean, beanDefinition);
        // 执行 BeanPostProcessors 后置
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) {}

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeanException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeanException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }
}
