package cn.forbearance.springframework.core.io;

/**
 * 资源加载器
 *
 * @author cristina
 */
public interface ResourceLoader {

    /**
     *  Pseudo URL prefix for loading from the class path: "classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 获取资源
     *
     * @param location location 地址
     * @return
     */
    Resource getResource(String location);
}
