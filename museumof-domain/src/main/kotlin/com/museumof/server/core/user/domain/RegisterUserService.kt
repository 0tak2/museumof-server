package com.museumof.server.core.user.domain

import com.museumof.server.core.user.port.`in`.RegisterUserCommand
import com.museumof.server.core.user.port.`in`.RegisterUserUsecase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

@Service
class RegisterUserService(
    private val userWriter: UserWriter
) : RegisterUserUsecase {
    @Transactional
    override fun registerUser(userCommand: RegisterUserCommand): User = userWriter.saveUser(
        User(username = userCommand.username,
            name = userCommand.name,
            email = userCommand.email,
            createdAt = ZonedDateTime.now(),
            oidcSub = userCommand.oidcSub,
            active = true)
    )
}
