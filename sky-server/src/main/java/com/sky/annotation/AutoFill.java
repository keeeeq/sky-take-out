package com.sky.annotation;

import com.sky.enumeration.OperationType;

import javax.lang.model.element.Element;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，标识自动填充的公共字段
 */
//注解加到什么位置
@Target(ElementType.METHOD)
//保留到运行时
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    OperationType value();
}
