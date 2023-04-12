package cn.forbearance.springframework.test;

import cn.forbearance.springframework.context.event.ApplicationContextEvent;

/**
 * 自定义事件
 * @author cristina
 */
public class MyEvent extends ApplicationContextEvent {

    private Long id;

    private String info;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MyEvent(Object source, Long id, String info) {
        super(source);
        this.id = id;
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
