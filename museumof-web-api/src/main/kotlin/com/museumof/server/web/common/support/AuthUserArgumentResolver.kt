package com.museumof.server.web.common.support

import com.museumof.server.web.common.security.openid.ExtendedOidcUser
import org.springframework.core.MethodParameter
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class AuthUserArgumentResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean =
        parameter.parameterType == AuthUser::class.java

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        return if (authentication.principal is ExtendedOidcUser) {
            val oidcUser: ExtendedOidcUser = authentication.principal as ExtendedOidcUser
            val authUser = AuthUser(
                oidcUser = oidcUser,
                oidcSub = oidcUser.oidcSub,
                domainUserId = oidcUser.domainUserId,
                domainFullName = oidcUser.domainFullName,
                domainCreatedAt = oidcUser.domainCreatedAt,
                domainUpdatedAt = oidcUser.domainUpdatedAt,
                domainActive = oidcUser.domainActive,
            )
            authUser
        } else {
            null
        }
    }
}

