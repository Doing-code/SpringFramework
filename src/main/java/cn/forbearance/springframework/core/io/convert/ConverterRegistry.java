package cn.forbearance.springframework.core.io.convert;

/**
 * 类型转换注册
 * @author cristina
 */
public interface ConverterRegistry {

    void addConverter(Converter<?, ?> converter);
}
