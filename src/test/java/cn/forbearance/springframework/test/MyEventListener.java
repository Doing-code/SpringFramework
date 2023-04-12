package cn.forbearance.springframework.test;

import cn.forbearance.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @author cristina
 */
public class MyEventListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent event) {
        System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getInfo());
    }
}
