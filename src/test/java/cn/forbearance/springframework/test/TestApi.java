package cn.forbearance.springframework.test;

import cn.forbearance.springframework.beans.PropertyValue;
import cn.forbearance.springframework.beans.PropertyValues;
import cn.forbearance.springframework.beans.factory.config.BeanDefinition;
import cn.forbearance.springframework.beans.factory.config.BeanReference;
import cn.forbearance.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.forbearance.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import cn.forbearance.springframework.context.support.ClassPathXmlApplicationContext;
import cn.forbearance.springframework.core.io.DefaultResourceLoader;
import cn.forbearance.springframework.core.io.Resource;
import cn.hutool.core.io.IoUtil;
import org.junit.Test;

import java.io.IOException;

/**
 * @author cristina
 */
public class TestApi {

    @Test
    public void test() throws IOException {
        /*DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        beanFactory.registerBeanDefinition("userService", new BeanDefinition(UserService.class, propertyValues));

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.query();
        System.out.println(userService);*/
    }

    @Test
    public void test_path() throws IOException {
       /* DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        String content = IoUtil.readUtf8(resource.getInputStream());
        System.out.println(content);*/
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        UserService userService = beanFactory.getBean("userService", UserService.class);
        userService.query();
    }

    @Test
    public void test_application() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.query();
    }

}
