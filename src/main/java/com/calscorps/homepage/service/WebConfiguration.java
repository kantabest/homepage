package com.calscorps.homepage.service;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/views/index/KR");
        registry.addRedirectViewController("/views", "/views/index/KR");
        registry.addRedirectViewController("/views", "/views/index/KR");
    }

    @Override
    // 연결된 다운로드 파일은 네트워크 드라이브로 연결되어 있어 해당 폴더를 리소스 핸들러에 추가한다.
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/link/sdc112/**")                
                .addResourceLocations("file:///C:\\inetpub\\wwwroot\\EMAX_ERP\\Ftp_File\\SDC112/");         
                // .addResourceLocations("file:///x:/");                 
                
    }


    // @Value("${server.port}")
    // private int serverPort;
   
    // private int httpPort = 80;

    
    // @Bean
    // public ServletWebServerFactory servletContainer() {

    //     TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory(){
    //         @Override
    //         protected void postProcessContext(Context context) {
    //             SecurityConstraint securityConstraint = new SecurityConstraint();
    //             securityConstraint.setUserConstraint("CONFIDENTIAL");
    //             SecurityCollection collection = new SecurityCollection();
    //             collection.addPattern("/*");
    //             securityConstraint.addCollection(collection);
    //             context.addConstraint(securityConstraint);
    //         }
    //     };
    //     tomcat.addAdditionalTomcatConnectors(createSslConnector());
    //     return tomcat;
    // }

    // private Connector createSslConnector() {
    //     Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
    //     connector.setScheme("http");
    //     connector.setSecure(false);
    //     connector.setPort(httpPort);
    //     connector.setRedirectPort(serverPort);
    //     return connector;
    // }

    
}
