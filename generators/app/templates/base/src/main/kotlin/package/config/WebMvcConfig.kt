package <%=packageName%>.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

const val X_PROJECT = "x-project"

@Configuration
class WebMvcConfig: WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
            .allowedOrigins("*")
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(ProjectLoggingInterceptor())
    }

    class ProjectLoggingInterceptor: HandlerInterceptorAdapter() {

        companion object {
            private val LOGGER: Logger = LoggerFactory.getLogger(ProjectLoggingInterceptor::class.java)
        }

        override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
            // Log request
            val projectLog = Optional.ofNullable(request.getHeader(X_PROJECT))
                .map{ " for project $it" }
                .orElse("")
            val queryParamLog = Optional.ofNullable(request.queryString)
                .map{ "?$it" }
                .orElse("")
            LOGGER.info("Received request to {} {}{}{}", request.method, request.requestURI, queryParamLog, projectLog)
            return true
        }
    }
}