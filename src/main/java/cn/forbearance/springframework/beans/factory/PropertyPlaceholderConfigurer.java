package cn.forbearance.springframework.beans.factory;

import cn.forbearance.springframework.beans.BeansException;
import cn.forbearance.springframework.beans.PropertyValue;
import cn.forbearance.springframework.beans.PropertyValues;
import cn.forbearance.springframework.beans.factory.config.BeanDefinition;
import cn.forbearance.springframework.beans.factory.config.BeanFactoryPostProcessor;
import cn.forbearance.springframework.core.io.DefaultResourceLoader;
import cn.forbearance.springframework.core.io.Resource;
import cn.forbearance.springframework.util.StringValueResolver;
import cn.hutool.core.bean.BeanException;

import java.io.IOException;
import java.util.Properties;

/**
 * @author cristina
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    /**
     * 占位符前缀 {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    /**
     * 占位符后缀 {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException {
        try {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);

                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) continue;
                    String buffer = resolverPlaceholder((String) value, properties);
                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), buffer));
                }
            }
            // 添加字符串解析器，供解析 @Value 注解使用
            PlaceholderResolverStringValueResolver resolver = new PlaceholderResolverStringValueResolver(properties);
            beanFactory.addEmbeddedValueResolver(resolver);
        } catch (IOException e) {
            throw new BeansException("Could not load properties", e);
        }
    }

    private String resolverPlaceholder(String value, Properties properties) {
        String val = value;
        StringBuffer buffer = new StringBuffer(val);
        int startIdx = val.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
        int stopIdx = val.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
        if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
            String propKey = val.substring(startIdx + 2, stopIdx);
            String propVal = properties.getProperty(propKey);
            buffer.replace(startIdx, stopIdx + 1, propVal);
        }
        return buffer.toString();
    }

    private class PlaceholderResolverStringValueResolver implements StringValueResolver {

        private final Properties properties;

        public PlaceholderResolverStringValueResolver(Properties properties) {
            this.properties = properties;
        }

        @Override
        public String resolverStringValue(String strVal) {
            return PropertyPlaceholderConfigurer.this.resolverPlaceholder(strVal, properties);
        }
    }
}
