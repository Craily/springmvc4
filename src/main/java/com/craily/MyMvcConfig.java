package com.craily;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.craily.messageconverter.MyMessageConverter;

@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan("com.craily")
public class MyMvcConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		// TODO 配置视图渲染器
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/classes/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO 配置静态资源
		/**
		 * 指的是文件放置的目录，addResourceHandler指的是对外暴露的访问路径
		 * Spring MVC 4.1.5.RELEASE 此处配置无法使用忽略版本号的webjars，即无法使用webjars-locator配置
		 * 因为4.1.5.RELEASE不包含WebJarsResourceResolver
		 */
		registry.addResourceHandler("webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/").resourceChain(false).addResolver(new WebJarsResourceResolver()).addResolver(new PathResourceResolver());
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

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO 页面转向简化写法，用词写法就不需要在controller层中写跳转方法
		registry.addViewController("/index").setViewName("index");
		registry.addViewController("/toUpload").setViewName("upload");
		registry.addViewController("/converter").setViewName("converter");
		registry.addViewController("/sse").setViewName("sse");
		registry.addViewController("/async").setViewName("async");
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		// TODO 防止忽略http://localhost:8080/springmvc4/index/xx.yy . 后面的yy
		configurer.setUseSuffixPatternMatch(false);
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setMaxUploadSize(1000000);
		return commonsMultipartResolver;
	}
	
	@Bean
	public MyMessageConverter converter() {
		return new MyMessageConverter();
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO 仅添加一个自定义的HttpMessageConverter，不覆盖默认注册的HttpMessageConverter。
		converters.add(converter());
	}

//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		// TODO 重载会覆盖掉Spring MVC默认注册的多个HttpMessageConverter
//		super.configureMessageConverters(converters);
//	}

	
}
