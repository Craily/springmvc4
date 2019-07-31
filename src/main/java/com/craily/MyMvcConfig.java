package com.craily;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("com.craily")
public class MyMvcConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		// TODO 配置视图渲染器
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/classes/view/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO 配置静态资源
		/**
		 * 指的是文件放置的目录，addResourceHandler指的是对外暴露的访问路径
		 */
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
	}
	
	/**
	 * 配置拦截器的Bean
	 */
	@Bean
	public DemoInterceptor demoInterceptor() {
		return new DemoInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO 注册拦截器
		registry.addInterceptor(demoInterceptor());
	}
	
	
}
