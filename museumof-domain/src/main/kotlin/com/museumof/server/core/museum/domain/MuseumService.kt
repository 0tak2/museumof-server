package com.museumof.server.core.museum.domain

import com.museumof.server.core.museum.port.`in`.AddMuseumCommand
import com.museumof.server.core.museum.port.`in`.EditMuseumCommand
import com.museumof.server.core.museum.port.`in`.MuseumUsecases
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MuseumService(
    private val museumReader: MuseumReader,
    private val museumWriter: MuseumWriter
) : MuseumUsecases {
    override fun getMuseumById(id: Long): Museum {
        return museumReader.getMuseumById(id)
    }

    @Transactional
    override fun getMuseumsByOwnerId(loginUserId: Long?, ownerId: Long): Set<Museum> {
        val museums = museumReader.getMuseumsByOwnerId(ownerId)

        if (loginUserId == ownerId) {
            return museums
        }

        return museums.filter { !it.isHidden }.toSet()
    }

    @Transactional
    override fun addMuseum(command: AddMuseumCommand): Museum {
        return museumWriter.addMuseum(command)
    }

    @Transactional
    override fun editMuseum(command: EditMuseumCommand): Museum {
        return museumWriter.editMuseum(command)
    }

    @Transactional
    override fun deleteMuseum(id: Long) {
        museumWriter.deleteMuseum(id)
    }
}
