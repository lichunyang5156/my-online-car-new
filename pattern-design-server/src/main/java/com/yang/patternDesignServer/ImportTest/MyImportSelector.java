package com.yang.patternDesignServer.ImportTest;

import com.yang.patternDesignServer.ImportTest.beans.TestBeanA;
import com.yang.patternDesignServer.ImportTest.beans.TestBeanB;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{TestBeanA.class.getName(), TestBeanB.class.getName()};
    }
}
