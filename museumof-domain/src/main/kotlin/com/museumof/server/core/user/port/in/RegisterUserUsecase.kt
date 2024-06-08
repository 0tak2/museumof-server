package com.museumof.server.core.user.port.`in`

import com.museumof.server.core.user.domain.User

interface RegisterUserUsecase {
    fun registerUser(userCommand: RegisterUserCommand): User
}
