package cn.forbearance.springframework.context;

import java.util.EventObject;

/**
 * 具备事件功能的抽象类，后续所有事件的类都需要继承自 ApplicationEvent
 * @author cristina
 */
public abstract class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
