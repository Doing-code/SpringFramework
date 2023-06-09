package cn.forbearance.springframework.util;

import java.util.Objects;

/**
 * @author cristina
 */
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Exception e) {
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

    public static boolean isCglibProxyClass(Class<?> clazz) {
        return Objects.nonNull(clazz) && isCglibProxyClassName(clazz.getName());
    }

    public static boolean isCglibProxyClassName(String className) {
        return Objects.nonNull(className) && className.contains("$$");
    }
}
