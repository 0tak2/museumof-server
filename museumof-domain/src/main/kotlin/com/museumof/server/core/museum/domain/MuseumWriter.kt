package com.museumof.server.core.museum.domain

import com.museumof.server.core.museum.port.`in`.AddMuseumCommand
import com.museumof.server.core.museum.port.`in`.EditMuseumCommand
import com.museumof.server.core.museum.port.out.MuseumRepositoryPort
import org.springframework.stereotype.Component

@Component
class MuseumWriter(
    private val museumRepository: MuseumRepositoryPort
) {
    fun addMuseum(addCommand: AddMuseumCommand): Museum {
        val (museumName, ownerId, museumDescription, isHidden) = addCommand

        return museumRepository.addMuseum(Museum(null, museumName, ownerId, museumDescription, isHidden, true))
    }

    fun editMuseum(editCommand: EditMuseumCommand): Museum {
        val (id, museumName, museumDescription, isHidden) = editCommand

        val target = museumRepository.getMuseumById(id)

        target.apply {
            museumName?.let { this.museumName = museumName }
            museumDescription?.let { this.museumDescription = museumDescription }
            isHidden?.let { this.isHidden = isHidden }
        }

        println(target)

        return museumRepository.editMuseum(target)
    }

    fun deleteMuseum(id: Long) {
        return museumRepository.deleteMuseum(id)
    }
}
