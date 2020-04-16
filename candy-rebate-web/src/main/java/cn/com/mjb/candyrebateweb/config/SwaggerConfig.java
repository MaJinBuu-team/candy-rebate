package cn.com.mjb.candyrebateweb.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger核心配置文件
 *
 * @author buu
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    private static String port;

    @Value("${server.port}")
    public void setPort(String portNumber) {
        port = portNumber;
    }

    public static String CONTROLLER_URL = "cn.com.mjb.candyrebateweb.controller";    //Swagger扫描的接口路径
    public static String SWAGGER_TITLE = "API文档-candy-rebate-web";                //Swagger接口文档标题
    public static String SWAGGER_DESCRIPTION = "糖果返利文档";                //Swagger接口文档描述
    public static String SWAGGER_VERSION = "1.0.0";                         //Swagger接口文档版本
    public String SWAGGER_URL = "http:121.89.195.134:" + port;    //Swagger项目服务的URL

    //验证的页面http://127.0.0.1:8080/swagger-ui.html
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(CONTROLLER_URL))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(SWAGGER_TITLE)
                .description(SWAGGER_DESCRIPTION)
                .termsOfServiceUrl(SWAGGER_URL)
                .version(SWAGGER_VERSION)
                .build();
    }
}
