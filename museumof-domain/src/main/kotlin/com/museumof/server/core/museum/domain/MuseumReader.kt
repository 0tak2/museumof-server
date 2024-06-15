package com.museumof.server.core.museum.domain

import com.museumof.server.core.museum.port.out.MuseumRepositoryPort
import org.springframework.stereotype.Component

@Component
class MuseumReader(private val museumRepository: MuseumRepositoryPort) {
    fun getMuseumById(museumId: Long): Museum {
        return museumRepository.getMuseumById(museumId)
    }

    fun getMuseumsByOwnerId(ownerId: Long): Set<Museum> {
        return museumRepository.getMuseumByOwnerId(ownerId)
    }
}
