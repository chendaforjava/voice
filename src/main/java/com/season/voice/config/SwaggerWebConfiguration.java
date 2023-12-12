package com.season.voice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;


@Configuration
public class SwaggerWebConfiguration implements WebMvcConfigurer {

    @Resource
    private SwaggerProperties swaggerProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String swaggerPrefix = StringUtils.isEmpty(swaggerProperties.getPrefix()) ? "" : swaggerProperties.getPrefix();
        /** swagger-ui 地址 */
        registry.addResourceHandler(swaggerPrefix + "/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
    }
}