package com.museumof.server.storage.db.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserJpaRepository : JpaRepository<UserEntity, Long> {
    fun findByOidcSub(oidcSub: String): UserEntity?
}
