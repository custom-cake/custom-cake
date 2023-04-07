package com.cake.customcakebackend.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@EnableSwagger2
@Configuration
class SwaggerConfiguration {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2).apiInfo(swaggerInfo()).select()
            .apis(RequestHandlerSelectors.basePackage("com.cake.customcakebackend.adapter.in.web"))
            .paths(PathSelectors.any())  // ant("/user/**")
            .build()
            .useDefaultResponseMessages(true)  // 200 이외의 response code 사용 여부
    }

    private fun swaggerInfo(): ApiInfo {
        /*
        API 문서화 및 테스트를 가능케 해주는 Springfox
        return ApiInfoBuilder()
                .title("Api Documentation")
                .description("Api Documentation")
                .version("1.0")
                .license("Apache License Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .build();
        아레와 같이 문서에 대한 문서명, 추가설명, 버전, license 명, license url 등을 설정 하여 API에 대한 정보를 나타내 줍니다.
        */
        return ApiInfoBuilder().title("Custom Cake API Documentation")
            .version("3.0")
            .description("앱 개발시 사용되는 서버 API에 대한 연동 문서입니다").build()
    }
}