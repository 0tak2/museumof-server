package com.museumof.server.storage.db.museum

import com.museumof.server.core.museum.domain.Museum
import com.museumof.server.core.museum.port.out.MuseumRepositoryPort
import com.museumof.server.storage.db.user.UserEntity
import org.springframework.stereotype.Component

@Component
class MuseumRepository(
    private val museumJpaRepository: MuseumJpaRepository
) : MuseumRepositoryPort {
    override fun getMuseumByOwnerId(ownerId: Long): Set<Museum> {
        return museumJpaRepository.getByOwnerId(ownerId)
            .map { entityToDomain(it) }.toSet()
    }

    override fun addMuseum(museum: Museum): Museum {
        val savedEntity = museumJpaRepository.save(domainToEntity(museum))
        println("0takdebug " + savedEntity.owner)
        return entityToDomain(savedEntity)
    }

    override fun getMuseumById(id: Long): Museum {
        TODO("Not yet implemented")
    }

    override fun editMuseum(museum: Museum): Museum {
        TODO("Not yet implemented")
    }

    override fun deleteMuseum(id: Long) {
        TODO("Not yet implemented")
    }

    fun entityToDomain(entity: MuseumEntity): Museum {
        return Museum(
            entity.id,
            entity.museumName!!,
            entity.owner!!.id!!,
            entity.description!!,
            entity.hidden!!,
            entity.active!!
        )
    }

    fun domainToEntity(museum: Museum): MuseumEntity {
        return MuseumEntity(
            museum.id,
            museum.museumName,
            UserEntity(id = museum.id),
            museum.museumDescription,
            museum.isHidden,
            museum.isActive
        )
    }
}
