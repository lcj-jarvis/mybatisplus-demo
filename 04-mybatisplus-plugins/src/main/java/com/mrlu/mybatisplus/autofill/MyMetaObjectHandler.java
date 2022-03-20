package com.mrlu.mybatisplus.autofill;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 0:04
 */
public class MyMetaObjectHandler implements MetaObjectHandler {

    /*
     * 严格模式填充策略,默认有值不覆盖,如果提供的值为null也不填充
     *
     * @param metaObject metaObject meta object parameter
     * @param fieldName  java bean property name
     * @param fieldVal   java bean property value of Supplier
     * @return this
     * @since 3.3.0
     */
    /*default MetaObjectHandler strictFillStrategy(MetaObject metaObject, String fieldName, Supplier<?> fieldVal) {
        if (metaObject.getValue(fieldName) == null) {
            Object obj = fieldVal.get();
            if (Objects.nonNull(obj)) {
                metaObject.setValue(fieldName, obj);
            }
        }
        return this;
    }*/

    @Override
    public void insertFill(MetaObject metaObject) {
        //如果有值就不覆盖，没值就设置为1
        this.strictFillStrategy(metaObject,"logicFlag",()-> 1);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
       //第二个参数为填充的字段，第三个为填充的属性值，填充的字段的类型
       this.strictUpdateFill(metaObject,"name",()->"aabb",String.class);
    }
}
