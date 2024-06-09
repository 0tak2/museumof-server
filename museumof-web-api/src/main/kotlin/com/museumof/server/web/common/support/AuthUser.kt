package com.museumof.server.web.common.support

import org.springframework.security.oauth2.core.oidc.user.OidcUser
import java.time.ZonedDateTime

data class AuthUser(
    val domainUserId: Long,
    val oidcSub: String,
    val domainCreatedAt: ZonedDateTime,
    val domainUpdatedAt: ZonedDateTime? = null,
    val domainActive: Boolean,
    val domainFullName: String,
    val oidcUser: OidcUser,
)
