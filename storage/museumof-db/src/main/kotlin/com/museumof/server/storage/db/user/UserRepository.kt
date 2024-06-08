package com.museumof.server.storage.db.user

import com.museumof.server.core.user.domain.User
import com.museumof.server.core.user.port.out.UserRepositoryPort
import org.springframework.stereotype.Component

@Component
class UserRepository(
    private val jpaRepository: UserJpaRepository
) : UserRepositoryPort {
    override fun saveUser(user: User): User {
        val saved: UserEntity = jpaRepository.save(domainToEntity(user))

        return entityToDomain(saved)!!
    }

    override fun getUserBySub(oidcSub: String): User? = entityToDomain(jpaRepository.findByOidcSub(oidcSub))

    fun domainToEntity (domain: User): UserEntity = UserEntity(
        id = domain.id,
        username = domain.username,
        name = domain.name,
        email = domain.email,
        oidcSub = domain.oidcSub,
        createdAt = domain.createdAt,
        updatedAt = domain.updatedAt,
        active = domain.active,
    )

    fun entityToDomain(entity: UserEntity?): User? = entity?.let {
        User(
            id = it.id,
            username = it.username!!,
            name = it.name!!,
            email = it.email!!,
            oidcSub = it.oidcSub!!,
            createdAt = it.createdAt!!,
            updatedAt = it.updatedAt,
            active = false
        )
    }
}
