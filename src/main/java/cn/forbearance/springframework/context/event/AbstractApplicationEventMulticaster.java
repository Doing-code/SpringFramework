package cn.forbearance.springframework.context.event;

import cn.forbearance.springframework.beans.BeansException;
import cn.forbearance.springframework.beans.factory.BeanFactory;
import cn.forbearance.springframework.beans.factory.BeanFactoryAware;
import cn.forbearance.springframework.context.ApplicationEvent;
import cn.forbearance.springframework.context.ApplicationListener;
import cn.forbearance.springframework.utils.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author cristina
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
        LinkedList<ApplicationListener> allListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            if (supportEvent(listener, event)) {
                allListeners.add(listener);
            }
        }
        return allListeners;
    }

    /**
     * Determine whether the given listener supports the given event.
     * <p>
     * 监听器是否对该事件感兴趣
     *
     * @param applicationListener
     * @param event
     * @return
     */
    protected boolean supportEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();

        // 按照 CglibSubclassingInstantiationStrategy、SimpleInstantiationStrategy 不同的实例化类型，需要判断后获取目标 Class
        Class<?> targetCLass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        Type genericInterface = targetCLass.getGenericInterfaces()[0];

        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }
        /*
         * 判断此 eventClassName 对象所表示的类或接口与指定的 event.getClass() 参数所表示的类或接口是否相同
         * 或是否是其超类或超接口
         *
         * isAssignableFrom() 是用来判断子类和父类的关系的，或者实现类和接口的关系
         *
         * 默认所有类的最终父类都是 Object，如果 A.isAssignableFrom(B) 证明 B 可以转换为 A，也就是 A 可以由 B 转换而来。
         * */
        return eventClassName.isAssignableFrom(event.getClass());
    }
}
