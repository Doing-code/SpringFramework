package cn.forbearance.springframework.core.io.convert;

/**
 * 类型转换 interface
 *
 * @author cristina
 */
public interface Converter<S, T> {

    /**
     * 将类型S的源对象转换为目标类型T
     *
     * @param source
     * @return
     */
    T covert(S source);
}
