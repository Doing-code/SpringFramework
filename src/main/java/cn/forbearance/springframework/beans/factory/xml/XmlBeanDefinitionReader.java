package cn.forbearance.springframework.beans.factory.xml;

import cn.forbearance.springframework.beans.BeansException;
import cn.forbearance.springframework.beans.PropertyValue;
import cn.forbearance.springframework.beans.factory.config.BeanDefinition;
import cn.forbearance.springframework.beans.factory.config.BeanReference;
import cn.forbearance.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import cn.forbearance.springframework.beans.factory.support.BeanDefinitionRegistry;
import cn.forbearance.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import cn.forbearance.springframework.core.io.Resource;
import cn.forbearance.springframework.core.io.ResourceLoader;
import cn.hutool.core.bean.BeanException;
import cn.hutool.core.util.StrUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 解析 XML 处理 Bean 注册
 * <p>
 * 通过解析 XML 自动注册
 *
 * @author cristina
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream();) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException | DocumentException e) {
            throw new BeanException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        Resource resource = getResourceLoader().getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... location) throws BeansException {
        for (String resource : location) {
            loadBeanDefinitions(resource);
        }
    }

    protected void doLoadBeanDefinitions(InputStream is) throws ClassNotFoundException, DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(is);
        Element root = document.getRootElement();

        // 包扫描
        Element componentScan = root.element("component-scan");
        if (null != componentScan) {
            String scanPath = componentScan.attributeValue("base-package");
            if (StrUtil.isEmpty(scanPath)) throw new BeansException("The value of base-package attribute can not be empty or null");
            scanPackage(scanPath);
        }

        List<Element> beanList = root.elements("bean");
        for (Element bean : beanList) {
            String id = bean.attributeValue("id");
            String name = bean.attributeValue("name");
            String className = bean.attributeValue("class");

            // 增加对 init-method、destroy-method 标签的读取
            String initMethodName = bean.attributeValue("init-method");
            String destroyMethodName = bean.attributeValue("destroy-method");

            String beanScope = bean.attributeValue("scope");

            // 获取 Class, 方便获取类名称
            Class<?> clazz = Class.forName(className);
            // 优先级 id > name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            // 定义 Bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setInitMethodName(initMethodName);
            beanDefinition.setDestroyMethodName(destroyMethodName);

            if (StrUtil.isNotEmpty(beanScope)) beanDefinition.setScope(beanScope);

            List<Element> propertyList = bean.elements("property");
            for (Element property : propertyList) {
                String attrName = property.attributeValue("name");
                String attrValue = property.attributeValue("value");
                String attrRef = property.attributeValue("ref");
                // 获取属性值：引用对象、值
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                // 封装属性信息添加到 Bean 定义中
                beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(attrName, value));
            }
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            // 注册 BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }

    private void scanPackage(String scanPath) {
        String[] basePackages = StrUtil.splitToArray(scanPath, ',');
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getRegistry());
        scanner.doScan(basePackages);
    }
}
