package cn.forbearance.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * 用集合包装 PropertyValue，属性可能会有多个
 *
 * @author cristina
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValues = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv) {
        this.propertyValues.add(pv);
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValues.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue pv : this.propertyValues) {
            if (!pv.getName().equals(propertyName)) {
                continue;
            }
            return pv;
        }
        return null;
    }
}
