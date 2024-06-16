package com.museumof.server.web.museum

import com.museumof.server.core.museum.domain.Museum
import com.museumof.server.core.museum.port.`in`.AddMuseumCommand
import com.museumof.server.core.museum.port.`in`.MuseumUsecases
import com.museumof.server.web.common.support.AuthUser
import com.museumof.server.web.museum.request.AddMuseumRequest
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/museums", produces = [MediaType.APPLICATION_JSON_VALUE])
class MuseumController(private val museumUsecases: MuseumUsecases) {
    @PostMapping
    fun addMuseum(authUser: AuthUser, @RequestBody request: AddMuseumRequest): Museum {
        return museumUsecases.addMuseum(
            AddMuseumCommand(
                request.name,
                authUser.domainUserId,
                request.description,
                request.isHidden
            )
        )
    }
}
