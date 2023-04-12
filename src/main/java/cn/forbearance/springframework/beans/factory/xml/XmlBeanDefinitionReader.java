package cn.forbearance.springframework.beans.factory.xml;

import cn.forbearance.springframework.beans.BeansException;
import cn.forbearance.springframework.beans.PropertyValue;
import cn.forbearance.springframework.beans.factory.config.BeanDefinition;
import cn.forbearance.springframework.beans.factory.config.BeanReference;
import cn.forbearance.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import cn.forbearance.springframework.beans.factory.support.BeanDefinitionRegistry;
import cn.forbearance.springframework.core.io.Resource;
import cn.forbearance.springframework.core.io.ResourceLoader;
import cn.hutool.core.bean.BeanException;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

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
        } catch (IOException | ClassNotFoundException e) {
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

    protected void doLoadBeanDefinitions(InputStream is) throws ClassNotFoundException {
        Document document = XmlUtil.readXML(is);
        Element element = document.getDocumentElement();
        NodeList childNodes = element.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            // 判断元素
            if (!(item instanceof Element)) continue;
            // 判断对象
            if (!"bean".equals(item.getNodeName())) continue;

            // 解析标签
            Element bean = (Element) item;
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");

            // 增加对 init-method、destroy-method 标签的读取
            String initMethodName = bean.getAttribute("init-method");
            String destroyMethodName = bean.getAttribute("destroy-method");

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

            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                Node propertyItem = bean.getChildNodes().item(j);
                if (!(propertyItem instanceof Element)) continue;
                if (!"property".equals(propertyItem.getNodeName())) continue;

                // 解析标签 property
                Element property = (Element) propertyItem;
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
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
}
