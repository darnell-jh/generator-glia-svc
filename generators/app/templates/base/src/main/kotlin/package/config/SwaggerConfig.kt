package <%=packageName%>.config

import com.dhenry.glia.config.SwaggerBaseConfig
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig: SwaggerBaseConfig() {

    override fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .description("Awesome Glia Microservice API")
            .title("Awesome Glia Microservice API")
            .version("v1")
            .contact(Contact("Darnell", "www.example.com", "lild12@gmail.com"))
            .build()
    }

}