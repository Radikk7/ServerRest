package com.example.serverrest;

import com.example.serverrest.controller.UserController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@SpringBootApplication
public class ServerRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerRestApplication.class, args);
    }


 //  @Value("${upload.path}")
 //  private String url;

 //  @Override
 //  public void addResourceHandlers(ResourceHandlerRegistry registry) {
 //      registry.addResourceHandler("/img/**").addResourceLocations("file:///"+ url+"/");
 //  }


}
