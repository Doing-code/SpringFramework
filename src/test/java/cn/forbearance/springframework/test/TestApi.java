package cn.forbearance.springframework.test;

import cn.forbearance.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.forbearance.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import cn.forbearance.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

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

    @Test
    public void test_initDestroy() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.query();
    }

    @Test
    public void testAware() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.query();

//        System.out.println("factory: " + userService.getBeanFactory());
//        System.out.println("application: " + userService.getApplicationContext());
    }

    @Test
    public void testScope() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        UserService userService1 = applicationContext.getBean("userService", UserService.class);
//        UserService userService2 = applicationContext.getBean("userService", UserService.class);
        userService1.query();

//        System.out.println(userService1 == userService2);
    }

    @Test
    public void testEventListener() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publisher(new MyEvent(applicationContext, 123L, "success"));
        applicationContext.registerShutdownHook();
    }

}
