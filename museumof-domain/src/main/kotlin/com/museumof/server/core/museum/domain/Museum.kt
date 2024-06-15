package com.museumof.server.core.museum.domain

data class Museum(
    val id: Long? = null,
    var museumName: String,
    val ownerId: Long,
    var museumDescription: String,
    var isHidden: Boolean,
    var isActive: Boolean,
) {
}
