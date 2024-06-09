package com.museumof.server.core.user.domain

import java.time.ZonedDateTime

data class User(
    val id: Long? = null,
    val username: String,
    val name: String,
    val email: String,
    val oidcSub: String,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime?= null,
    val active: Boolean,
)
