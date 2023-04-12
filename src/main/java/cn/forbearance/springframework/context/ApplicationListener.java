package cn.forbearance.springframework.context;

import java.util.EventListener;

/**
 * @author cristina
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * Handle on application event
     * @param event
     */
    void onApplicationEvent(E event);
}
