package com.mossonthetree;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;

public class NamingStrategy extends DefaultGeneratorStrategy {
    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        return String.format("TBL%s", super.getJavaClassName(definition, mode));
    }
}
