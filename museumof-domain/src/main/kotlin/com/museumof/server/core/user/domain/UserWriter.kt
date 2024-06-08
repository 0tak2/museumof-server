package com.museumof.server.core.user.domain

import com.museumof.server.core.user.port.out.UserRepositoryPort
import org.springframework.stereotype.Component

@Component
class UserWriter(
    private val userRepository: UserRepositoryPort
) {
    fun saveUser(newUser: User): User = userRepository.saveUser(newUser)
}
