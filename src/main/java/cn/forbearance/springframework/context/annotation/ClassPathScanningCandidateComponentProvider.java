package cn.forbearance.springframework.context.annotation;

import cn.forbearance.springframework.beans.factory.config.BeanDefinition;
import cn.forbearance.springframework.stereotype.Component;
import cn.hutool.core.util.ClassUtil;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 对象扫描装配
 *
 * @author cristina
 */
public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponent(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }
}
