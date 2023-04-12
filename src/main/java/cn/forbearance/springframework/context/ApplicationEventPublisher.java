package cn.forbearance.springframework.context;

/**
 * 事件发布者定义 interface
 *
 * @author cristina
 */
public interface ApplicationEventPublisher {

    /**
     * 事件发布
     *
     * @param event
     */
    void publisher(ApplicationEvent event);
}
