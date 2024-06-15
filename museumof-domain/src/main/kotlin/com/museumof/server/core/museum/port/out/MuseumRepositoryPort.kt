package com.museumof.server.core.museum.port.out

import com.museumof.server.core.museum.domain.Museum

interface MuseumRepositoryPort {
    fun getMuseumByOwnerId(ownerId: Long): Set<Museum>
    fun addMuseum(museum: Museum): Museum
    fun getMuseumById(id: Long): Museum
    fun editMuseum(museum: Museum): Museum
    fun deleteMuseum(id: Long)
}
