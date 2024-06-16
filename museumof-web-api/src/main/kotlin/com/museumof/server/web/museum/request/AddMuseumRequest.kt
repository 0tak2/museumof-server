package com.museumof.server.web.museum.request

data class AddMuseumRequest(
    val name: String,
    val description: String,
    val isHidden: Boolean
) {
}
