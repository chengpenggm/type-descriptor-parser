package com.github.hcsp.descriptorparser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数组类型的描述符，如输入[[Ljava/lang/Object;
 * 得到的name是java.lang.Object[][]
 * dimension为2（二维数组）
 * rawType代表无数组的原始类型
 */
public class ArrayDescriptor implements TypeDescriptor {
    // [ -> 1
    // [[ -> 2
    private String name;
    private String descriptor;
    private int dimension;
    private TypeDescriptor rawType;

    // [[Ljava/lang/Object;
    public ArrayDescriptor(String descriptor) {
        this.descriptor = descriptor;
        Pattern pattern = Pattern.compile("(\\[+)(.*)");
        Matcher matcher = pattern.matcher(descriptor);
        if (matcher.find()) {
            dimension = matcher.group(1).length();
            rawType = TypeDescriptor.parse(matcher.group(2));
            StringBuilder typeName = new StringBuilder(rawType.getName());
            while (dimension > 0) {
                typeName.append("[]");
                dimension--;
            }
            name = typeName.toString();
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public int getDimension() {
        return dimension;
    }

    public TypeDescriptor getRawType() {
        return rawType;
    }

    @Override
    public String getDescriptor() {
        return descriptor;
    }
}
