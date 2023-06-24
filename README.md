# SpringFramework
手写简化版 Spring 框架，了解 Spring 核心原理，为后续再深入学习 Spring 打下基础；摘取整体框架中的核心逻辑，简化代码实现过程，保留核心功能，例如：IOC、AOP、Bean生命周期、上下文、作用域、资源处理等内容实现。

手写简化版 Spring 是对 IOC 容器的源码分析。

[手撸 Spring 核心类之间的关系](/image/spring_类之间的关系.png)

[Spring 初始化简图](/image/spring_初始化简图.png)

```txt
记录 BeanPostProcessor 和 BeanFactoryPostProcessor 之间的区别，总是容易记混。

BeanPostProcessor：bean后置处理器，bean对象创建赋值之后，初始化前后进行拦截工作的。
BeanFactoryPostProcessor：beanFactory后置处理器，BeanFactory标准初始化之后调用。即所有的bean定义已经加载到beanFactory，但是bean对象的实例还未创建。
    BeanFactoryPostProcessor在 invokeBeanFactoryPostProcessor(beanFacoty) 方法中得到执行。按照 BeanFactoryPostProcessor 类型查找组件并执行 postProcessBeanFactory()

BeanDefinitionRegistryPostProcessor： 是 BeanFactoryPostProcessor 的子接口。其内部定义了 postProcessBeanDefinitionRegistry() 方法。
    在所有bean定义信息将要被加载，bean实例还未创建时进行调用。先于 BeanFactoryPostProcessor 执行。
```

##### 组件注册
- [x] [@Configuration&@Bean](https://github.com/Doing-code/CrazyNote/tree/master/docs/md/spring/develop-spring/2022-04-25-01-@Configuration&@Bean-register-component.md)
- [x] [@ComponentScan](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-04-26-02-%40ComponentScan-auto-or-specify-scanner-component.md)
- [x] [custom-TypeFilter](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-04-28-03-custom-TypeFilter.md)
- [x] [@Scope](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-05-04-04-%40Scope-set-component-scope.md)
- [x] [@Lazy-bean](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-05-05-05-%40Lazy-bean-lazy-loading.md)
- [x] [@Conditional](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-05-06-06-%40Conditional-condition-register-bean.md)
- [x] [@Import](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-05-08-07-%40Import-import-component.md)
- [x] [ImportSelector](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-05-08-08-%40Import-use-ImportSelector.md)
- [x] [ImportBeanDefinitionRegistrar](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-05-08-09-%40Import-use-ImportBeanDefinitionRegistrar.md)
- [x] [FactoryBean](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-05-08-10-use-FactoryBean-register-component.md)

##### 生命周期
- [x] [@Bean指定初始化和销毁方法](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-05-08-11-%40Bean-specify-init%26destroy.md)
- [x] [InitializingBean & DisposableBean](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-05-08-12-InitializingBean%26DisposableBean.md)
- [x] [@PostConstruct&@PreDestroy](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-05-09-13-%40PostConstruct%26%40PreDestroy.md)
- [x] [BeanPostProcessor](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-05-09-14-BeanPostProcessor.md)
- [x] [BeanPostProcessor源码](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-05-10-15-BeanPostProcessor-principle.md)
- [x] [BeanPostProcessor应用](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-05-11-16-BeanPostProcessor-apply-of-spring.md)

##### 组件赋值
- [x] [@Value](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-05-12-17-%40Value-assign.md)
- [x] [@PropertySource](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-05-14-18-%40PropertySource-load-external-config-file.md)

##### 组件注入
- [x] [@Autowired&@Qualifier&@Primary](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-05-15-19-%40Autowired%26%40Qualifier%26%40Primary.md)
- [x] [@Resource&@Inject](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-05-15-20-%40Resource%26%40Inject.md)
- [x] [方法、构造器的自动注入](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-05-15-21-method%26constructor-auto-wired.md)
- [x] [Aware](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-05-15-22-Aware-inject-component%26principle.md)
- [x] [@Profile](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-05-15-23-%40Profile-register-by-environment-bean.md)

##### aop-事务源码
- [x] [@EnableAspectJAutoProxy](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-05-15-24-%40EnableAspectJAutoProxy-principle.md)
- [x] [spring-aop源码分析](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-05-18-26-xml-spring-aop-source.md)
- [x] [@EnableTransactionManagement](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-05-22-27-%40EnableTransactionManagement-principle.md)
- [x] [Spring事务源码分析](https://github.com/Doing-code/CrazyNote/blob/master/docs/md/spring/develop-spring/2022-05-22-28-xml-transaction-principle.md)