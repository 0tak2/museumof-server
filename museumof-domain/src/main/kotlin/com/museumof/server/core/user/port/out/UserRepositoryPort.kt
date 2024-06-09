package com.museumof.server.core.user.port.out

import com.museumof.server.core.user.domain.User

interface UserRepositoryPort {
    fun getUserBySub(oidcSub: String) : User?
    fun saveUser(user: User): User
}
