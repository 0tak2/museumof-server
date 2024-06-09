package com.museumof.server.web.common.security.openid

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.oidc.OidcIdToken
import org.springframework.security.oauth2.core.oidc.OidcUserInfo
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import java.time.ZonedDateTime

class ExtendedOidcUser(
    authorities: MutableCollection<out GrantedAuthority>?,
    idToken: OidcIdToken?,
    userInfo: OidcUserInfo?,
    val domainUserId: Long,
    val domainFullName: String,
    val oidcSub: String,
    val domainCreatedAt: ZonedDateTime,
    val domainUpdatedAt: ZonedDateTime? = null,
    val domainActive: Boolean,
) : DefaultOidcUser(authorities, idToken, userInfo) {
    companion object {
        fun extendOf(
            oidcUser: OidcUser,
            domainUserId: Long,
            domainFullName: String,
            oidcSub: String,
            domainCreatedAt: ZonedDateTime,
            domainUpdatedAt: ZonedDateTime? = null,
            domainActive: Boolean
        ): ExtendedOidcUser = ExtendedOidcUser(
            oidcUser.authorities,
            oidcUser.idToken,
            oidcUser.userInfo,
            domainUserId,
            domainFullName,
            oidcSub,
            domainCreatedAt,
            domainUpdatedAt,
            domainActive,
        )
    }
}
