package com.museumof.server.storage.db.user

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue
    val id: Long? = null,

    @Column(nullable = false)
    val username: String? = null,

    @Column(nullable = false)
    val name: String? = null,

    @Column(nullable = false, unique = true)
    val email: String? = null,

    @Column(nullable = false, unique = true)
    val oidcSub: String? = null,

    @Column(nullable = false)
    val createdAt: ZonedDateTime? = null,

    @Column
    val updatedAt: ZonedDateTime? = null,

    @Column(nullable = false)
    val active: Boolean? = null,
) {
}
