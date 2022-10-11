package com.github.sorabh86.uigo.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.github.sorabh86.uigo.constants.Constants;

@Configuration
public class UIGOWebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path uploadPath = Paths.get(Constants.UPLOAD_DIR);
		String absolutePath = uploadPath.toFile().getAbsolutePath();
		
		System.out.println("upload directory: "+Constants.UPLOAD_DIR+" "+absolutePath);
		
		registry.addResourceHandler("/"+Constants.UPLOAD_DIR+"/**")
			.addResourceLocations("file:/"+absolutePath+"/")
			.setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic());
	}
	 
}
