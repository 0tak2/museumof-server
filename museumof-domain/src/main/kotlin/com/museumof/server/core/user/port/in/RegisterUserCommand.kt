package com.museumof.server.core.user.port.`in`

data class RegisterUserCommand(
    val username: String,
    val name: String,
    val email: String,
    val oidcSub: String,
)
