package com.museumof.server.core.user.port.`in`

import com.museumof.server.core.user.domain.User

interface GetUserBySubUsecase {
    fun getUserBySub(sub: String) : User?
}
