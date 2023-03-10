package com.blogs.config;

import com.blogs.interceptor.LoginInterceptor;
import com.blogs.interceptor.TestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {




    @Bean
    public LoginInterceptor getLoginInterceptor () {
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        return new LoginInterceptor();
    }

    @Bean
    public TestInterceptor getTestInterceptor(){
        TestInterceptor testInterceptor = new TestInterceptor();
        return new TestInterceptor();
    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLoginInterceptor())
                //所有请求都被拦截包括静态资源
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");

        //test注解-拦截器
        registry.addInterceptor(getTestInterceptor()).addPathPatterns("/**");
    }

}
