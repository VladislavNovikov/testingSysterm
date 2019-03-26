package com.testingSystem.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {"com.testingSystem"})
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("login");

        registry.addViewController("/admin/add-or-update-topic").setViewName("admin/forTopic/add-or-update-topic");
        registry.addViewController("/admin/add-or-update-user").setViewName("admin/forUser/add-or-update-user");
        registry.addViewController("/admin/statistics").setViewName("admin/forStatistic/statistics");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("/resources/images/");
        registry.addResourceHandler("/scripts/**").addResourceLocations("/resources/scripts/");
    }
}
