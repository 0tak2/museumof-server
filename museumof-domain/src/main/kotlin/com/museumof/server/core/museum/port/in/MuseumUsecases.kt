package com.museumof.server.core.museum.port.`in`

import com.museumof.server.core.museum.domain.Museum

interface MuseumUsecases {
    fun getMuseumById(id: Long): Museum
    fun getMuseumsByOwnerId(loginUserId: Long?, ownerId: Long): Set<Museum>
    fun addMuseum(command: AddMuseumCommand): Museum
    fun editMuseum(command: EditMuseumCommand): Museum
    fun deleteMuseum(id: Long)
}
