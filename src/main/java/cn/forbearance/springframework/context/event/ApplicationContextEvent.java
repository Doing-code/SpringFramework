package cn.forbearance.springframework.context.event;

import cn.forbearance.springframework.context.ApplicationContext;
import cn.forbearance.springframework.context.ApplicationEvent;

/**
 * 定义事件的抽象类，事件包括关闭、刷新，以及自定义事件实现，都需要继承自 ApplicationContextEvent
 *
 * @author cristina
 */
public class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
