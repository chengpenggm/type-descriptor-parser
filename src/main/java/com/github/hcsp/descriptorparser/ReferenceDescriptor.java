package com.github.hcsp.descriptorparser;

/**
 * 代表引用类型的描述符号
 */
public class ReferenceDescriptor implements TypeDescriptor {
    /**
     * 全限定类名，如java.lang.Object
     */
    private String fqcn;
    private String descriptor;

    /**
     * Ljava/lang/Object -> java.lang.Object
     */
    public ReferenceDescriptor(String descriptor) {
        this.descriptor = descriptor;
        this.fqcn = descriptor.replaceAll("/", ".").replace(";", "").substring(1);
    }

    @Override
    public String getName() {
        return fqcn;
    }

    @Override
    public String getDescriptor() {
        return descriptor;
    }
}
