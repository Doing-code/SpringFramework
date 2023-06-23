package cn.forbearance.springframework.core.io.convert;

/**
 * 类型转换工厂
 *
 * @author cristina
 */
public interface ConverterFactory<S, R> {

    /**
     * 让转换器从S转换到目标类型T，其中T也是R的一个实例。
     *
     * @param targetType
     * @param <T>
     * @return
     */
    <T extends R> Converter<S, T> getConverter(Class<T> targetType);
}
