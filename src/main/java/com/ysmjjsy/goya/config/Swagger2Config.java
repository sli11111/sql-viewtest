package com.ysmjjsy.goya.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author goya
 * @create 2022-03-18 21:08
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class Swagger2Config {
    //  http://localhost:8080/swagger-ui.html 原路径
    //  http://localhost:8080/doc.html 原路径
    //配置swagger2核心配置
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2) //指定api类型位swagger2
                .apiInfo(apiInfo())            //用于定义api文档汇总信息
                .select().apis(RequestHandlerSelectors
                        .basePackage("com.ysmjjsy")) //指定生成文档的controller
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("mybatis_demo_api") //文档标题
                .contact(new Contact("goya", //作者
                        "www.ysmjjsy.com","773949603@qq.com")) //联系人
                .description("ysmjjsy 的项目api接口")//详细信息
                .version("1.0.0")//文档版本号
                .termsOfServiceUrl("www.ysmjjsy.com")//网站地址
                .build();
    }
}
