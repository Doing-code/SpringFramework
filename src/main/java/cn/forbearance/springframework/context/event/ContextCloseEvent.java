package cn.forbearance.springframework.context.event;

/**
 * 事件关闭
 *
 * @author cristina
 */
public class ContextCloseEvent extends ApplicationContextEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextCloseEvent(Object source) {
        super(source);
    }
}
