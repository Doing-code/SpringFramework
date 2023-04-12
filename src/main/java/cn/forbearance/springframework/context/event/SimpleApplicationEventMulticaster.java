package cn.forbearance.springframework.context.event;

import cn.forbearance.springframework.beans.factory.BeanFactory;
import cn.forbearance.springframework.context.ApplicationEvent;
import cn.forbearance.springframework.context.ApplicationListener;

/**
 * @author cristina
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
