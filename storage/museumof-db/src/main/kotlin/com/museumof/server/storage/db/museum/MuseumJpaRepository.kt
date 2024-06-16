package com.museumof.server.storage.db.museum

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MuseumJpaRepository: JpaRepository<MuseumEntity, Int> {
    fun getByOwnerId(ownerId: Long): Set<MuseumEntity>
}
