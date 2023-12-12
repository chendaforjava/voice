package com.season.voice;

import com.season.voice.annotation.EnableCustomSwagger2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCustomSwagger2
@MapperScan(basePackages = "com.season.voice.mapper")
public class VoiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoiceApplication.class, args);
	}

}
