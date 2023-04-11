package cn.forbearance.springframework.context.support;

import cn.forbearance.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.forbearance.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Convenient base class for {@link cn.forbearance.springframework.context.ApplicationContext}
 * implementations, drawing configuration from XML documents containing bean definitions
 * understood by an {@link cn.forbearance.springframework.beans.factory.xml.XmlBeanDefinitionReader}.
 * <p>
 * 加载解析配置文件的基类
 *
 * @author cristina
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    /**
     * 获取配置信息地址描述
     *
     * @return
     */
    protected abstract String[] getConfigLocations();
}
