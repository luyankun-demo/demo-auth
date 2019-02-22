package com.demo.auth.ws.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

/**
 * 自定义Spring注解扫描Bean名（spring bean id）生成器，默认为类名首字母小写，自定义为类全名
 */
public class CustomAnnotationBeanNameGenerator extends AnnotationBeanNameGenerator {

    @Override
    protected final String buildDefaultBeanName(BeanDefinition definition) {
        return definition.getBeanClassName();
    }

}
