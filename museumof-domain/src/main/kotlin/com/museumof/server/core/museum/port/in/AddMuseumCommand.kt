package com.museumof.server.core.museum.port.`in`

data class AddMuseumCommand(
    val museumName: String,
    val ownerId: Long,
    val museumDescription: String,
    val isHidden: Boolean,
) {
}