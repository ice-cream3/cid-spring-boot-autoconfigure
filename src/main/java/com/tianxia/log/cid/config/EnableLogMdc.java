package com.tianxia.log.cid.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @ClassName: EnableParam
 * @Description:
 * @Author: ice
 * @Date: 2023/7/10 12:37
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ParaImportSelector.class)
public @interface EnableLogMdc {
}
