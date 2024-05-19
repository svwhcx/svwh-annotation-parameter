package com.svwh.parametercheck.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @description
 * @Author cxk
 * @Date 2024/5/19 17:25
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.svwh.parametercheck")
public class TestConfig {
}
