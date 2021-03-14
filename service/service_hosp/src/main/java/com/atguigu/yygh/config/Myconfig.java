package com.atguigu.yygh.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan("com.atguigu.yygh")
@MapperScan(basePackages = "com.atguigu.yygh.mapper")
public class Myconfig {
        @Bean
        public PaginationInterceptor paginationInterceptor(){
            return new PaginationInterceptor();
        }

    }
