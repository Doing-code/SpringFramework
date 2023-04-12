package cn.forbearance.springframework.aop;

/**
 * @author cristina
 */
public interface ClassFilter {

    /**
     * 类匹配，通过切点找到给定的接口或目标类
     *
     * @param clazz
     * @return
     */
    boolean matches(Class<?> clazz);
}
