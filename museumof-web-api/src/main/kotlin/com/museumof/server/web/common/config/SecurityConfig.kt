package com.museumof.server.web.common.config

import com.museumof.server.core.user.port.`in`.GetUserBySubUsecase
import com.museumof.server.core.user.port.`in`.RegisterUserUsecase
import com.museumof.server.web.common.security.openid.CustomOidcUserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.config.annotation.web.invoke

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val environment: Environment,
    private val getUserBySubUsecase: GetUserBySubUsecase,
    private val registerUserUsecase: RegisterUserUsecase,
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        if (environment.activeProfiles.contains("test")) {
            // To make testing possible even without an oauth2 provider
            http {
                authorizeHttpRequests {
                    authorize(anyRequest, authenticated)
                }
            }
            return http.build()
        }

        http {
            authorizeHttpRequests {
                authorize(anyRequest, authenticated)
            }
            oauth2Login {
                userInfoEndpoint {
                    oidcUserService = customOidcUserService()
                }
            }
        }

        return http.build()
    }

    private fun customOidcUserService(): CustomOidcUserService = CustomOidcUserService(
        getUserBySubUsecase = getUserBySubUsecase,
        registerUserUsecase = registerUserUsecase,
    )
}
