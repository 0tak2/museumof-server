package com.museumof.server.core.user.domain

import com.museumof.server.core.user.port.`in`.GetUserBySubUsecase
import org.springframework.stereotype.Service

@Service
class GetUserService(
    private val userReader: UserReader
) : GetUserBySubUsecase {
    override fun getUserBySub(sub: String): User? = userReader.getUserBySub(sub)
}
