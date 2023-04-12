package cn.forbearance.springframework.beans.factory.support;

import cn.forbearance.springframework.beans.BeansException;
import cn.forbearance.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.forbearance.springframework.beans.factory.config.BeanDefinition;
import cn.hutool.core.bean.BeanException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * - 继承自 AbstractAutowireCapableBeanFactory 类具备了 AbstractBeanFactory
 * 和 BeanFactory 的功能实现, 获取 Bean, 创建 Bean 实例 等等.
 * <p>
 * - 实现自 BeanDefinitionRegistry 接口具备注册 Bean 定义功能;
 * <p>
 * - 接口定义了注册, 抽象类dinginess了获取, 都集中在 beanDefinitionMap 里;
 * <p>
 * - 注册 Bean 定义和获取 Bean 定义可以同时使用
 *
 * @author cristina
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) throw new BeansException("No bean named '" + beanName + "' is defined");
        return beanDefinition;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    public void preInstantiateSingletons() throws BeanException {
        beanDefinitionMap.keySet().forEach(this::getBean);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((k, v) -> {
            Class beanClass = v.getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                result.put(k, (T) getBean(k));
            }
        });
        return result;
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        ArrayList<String> beanNames = new ArrayList<>();
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            Class beanClass = entry.getValue().getBeanClass();
            if (requiredType.isAssignableFrom(beanClass)) {
                beanNames.add(entry.getKey());
            }
            if (beanNames.size() == 1) {
                return getBean(beanNames.get(0), requiredType);
            }
        }
        throw new BeansException(requiredType + "expected single bean but found " + beanNames.size() + ": " + beanNames);
    }
}
