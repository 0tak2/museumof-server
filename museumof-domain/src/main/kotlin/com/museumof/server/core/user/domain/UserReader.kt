package com.museumof.server.core.user.domain

import com.museumof.server.core.user.port.out.UserRepositoryPort
import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userRepository: UserRepositoryPort
) {
    fun getUserBySub(sub: String): User? = userRepository.getUserBySub(sub)
}
