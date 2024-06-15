package com.museumof.server.core.museum.port.`in`

data class EditMuseumCommand(
    val id: Long,
    val museumName: String ?= null,
    val museumDescription: String ?= null,
    val isHidden: Boolean ?= null,
) {
}
