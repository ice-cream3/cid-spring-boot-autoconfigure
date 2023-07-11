package com.tianxia.log.cid.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @ClassName: ParaImportSelector
 * @Description:
 * @Author: ice
 * @Date: 2023/7/10 12:37
 */
public class ParaImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.tianxia.log.cid.config.WebMvcConfig"};
    }
}
