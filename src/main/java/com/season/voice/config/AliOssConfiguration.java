package com.season.voice.config;

import com.season.voice.service.AliyunOssService;
import com.season.voice.service.impl.AliyunOssImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 阿里OSS配置
 *
 * @author chenda
 */
@Configuration
@EnableConfigurationProperties(value = OssProperties.class)
@ConditionalOnProperty(prefix = OssProperties.PREFIX, name = "enable", havingValue = "true")
public class AliOssConfiguration {

	@Bean
	public AliyunOssService AliOssTemplate(OssProperties ossProperties) {
		return new AliyunOssImpl(ossProperties);
	}
}
