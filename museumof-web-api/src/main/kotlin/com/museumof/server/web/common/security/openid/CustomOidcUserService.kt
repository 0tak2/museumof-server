package com.museumof.server.web.common.security.openid

import com.museumof.server.core.user.domain.User
import com.museumof.server.core.user.port.`in`.GetUserBySubUsecase
import com.museumof.server.core.user.port.`in`.RegisterUserCommand
import com.museumof.server.core.user.port.`in`.RegisterUserUsecase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService
import org.springframework.security.oauth2.core.oidc.OidcUserInfo
import org.springframework.security.oauth2.core.oidc.user.OidcUser

class CustomOidcUserService(
    private val registerUserUsecase: RegisterUserUsecase,
    private val getUserBySubUsecase: GetUserBySubUsecase,
) : OidcUserService() {
    val log: Logger = LoggerFactory.getLogger(CustomOidcUserService::class.java)

    override fun loadUser(userRequest: OidcUserRequest?): OidcUser {
        val oidcUser: OidcUser = super.loadUser(userRequest)
        val userInfo: OidcUserInfo = oidcUser.userInfo
        log.debug("OidcUser: {}", userInfo)

        var userFromDb: User? = getUserBySubUsecase.getUserBySub(userInfo.subject)
        userFromDb ?: run {
            log.info("first login. try to register. OidcUser: {}", oidcUser)
            userFromDb = registerUserUsecase.registerUser(
                RegisterUserCommand(
                    username = userInfo.preferredUsername,
                    name = userInfo.fullName,
                    email = userInfo.email,
                    oidcSub = userInfo.subject
                )
            )
        }

        return ExtendedOidcUser.extendOf(
            oidcUser,
            domainUserId = userFromDb?.id!!,
            domainFullName = userFromDb?.name!!,
            oidcSub = userFromDb?.oidcSub!!,
            domainCreatedAt = userFromDb?.createdAt!!,
            domainUpdatedAt = userFromDb?.updatedAt,
            domainActive = userFromDb?.active!!
        )
    }
}
