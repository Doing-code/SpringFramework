package cn.forbearance.springframework.context;

import cn.forbearance.springframework.beans.factory.Aware;

/**
 * 应用上下文
 *
 * @author cristina
 */
public interface ApplicationContextAware extends Aware {

    /**
     * #
     *
     * @param context
     */
    void setApplicationContext(ApplicationContext context);
}
