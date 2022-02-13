package com.halcyon.online_store.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:E:/images/");
        //addResoureHandler：指的是对外暴露的访问路径。addResourceLocations：指的是内部文件放置的目录。设置后，在浏览器窗口输入http://localhost:8008/images
        // 文件名，就可以访问到本地地址为E:/IdeaProjects/my-server-save-images/文件名 的文件了。
        super.addResourceHandlers(registry);
    }

}

