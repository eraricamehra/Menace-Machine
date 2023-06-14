package com.psa.app.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.GzipResourceResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

//@EnableWebMvc
@ComponentScan(basePackages = {"com.psa.app"})
@Configuration
public class AppConfig implements WebMvcConfigurer {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
	
//	@Bean
//	public ViewResolver internalResourceViewResolver() {
//	    InternalResourceViewResolver bean = new InternalResourceViewResolver();
////	    bean.setViewClass(JstlView.class);
//	    bean.setPrefix("/templates/");
//	    bean.setSuffix(".html");
////	    bean.setSuffix(".js");
////	    bean.setSuffix(".css");
//	    return bean;
//	}
	
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//	    registry
//	      .addResourceHandler("/js/**")
//	      .addResourceLocations("/js/")
//	      .setCachePeriod(3600)
//	      .resourceChain(true)
////	      .addResolver(new GzipResourceResolver())
//	      .addResolver(new PathResourceResolver());
//	}
}