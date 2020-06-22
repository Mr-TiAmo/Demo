package com.li.spring.scan;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @program: springboot-demo
 * @description:
 * @author: 栗翱
 * @create: 2020-06-16 00:06
 **/
public class MyScanFilter implements TypeFilter {
    /**
     * @param metadataReader        读取到的当前正在读取类的信息
     * @param metadataReaderFactory 可以获取其他类信息的工厂
     * @return
     * @throws IOException
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        //获取当前类注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        //获取当前正在扫描的类的信息，比如类型是什么，实现什么接口
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        System.out.println(">>>>>>>>>>classMetadata" + classMetadata.getClassName());
        //获取当前类的资源信息，类的路径
        Resource resource = metadataReader.getResource();

        //匹配规则
        if (classMetadata.getClassName().contains("Controller")) {
            return true;
        } else {
            return false;
        }
    }
}
