package com.museumof.server.core.museum

import com.museumof.server.core.museum.domain.Museum
import com.museumof.server.core.museum.domain.MuseumReader
import com.museumof.server.core.museum.domain.MuseumService
import com.museumof.server.core.museum.domain.MuseumWriter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.kotlin.eq
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class MuseumServiceTest() {
    @Mock
    lateinit var museumReader: MuseumReader
    @Mock
    lateinit var museumWriter: MuseumWriter
    @InjectMocks
    lateinit var museumService: MuseumService

    @Test
    fun `로그인 아이디와 조회 아이디가 다른 경우에 비공개된 뮤지엄은 조회에 실패해야 한다`() {
        val publicMuseum = Museum(
            1L, "테스트 뮤지엄", 100L,
            "테스트 뮤지엄입니다.", false, false
        )
        val privateMuseum = Museum(
            2L, "테스트 뮤지엄", 100L,
            "테스트 뮤지엄입니다.", true, false
        )

        `when`(museumReader.getMuseumsByOwnerId(eq(100L))).thenReturn(setOf(publicMuseum, privateMuseum))

        val retrievedValue = museumService.getMuseumsByOwnerId(200L, 100L)
        assertThat(retrievedValue).isEqualTo(setOf(publicMuseum))
    }

    @Test
    fun `로그인 아이디와 조회 아이디가 같은 경우 모든 뮤지엄의 조회에 성공해야 한다`() {
        val publicMuseum = Museum(
            1L, "테스트 뮤지엄", 100L,
            "테스트 뮤지엄입니다.", false, false
        )
        val privateMuseum = Museum(
            2L, "테스트 뮤지엄", 100L,
            "테스트 뮤지엄입니다.", true, false
        )

        `when`(museumReader.getMuseumsByOwnerId(eq(100L))).thenReturn(setOf(publicMuseum, privateMuseum))

        val retrievedValue = museumService.getMuseumsByOwnerId(100L, 100L)
        assertThat(retrievedValue).isEqualTo(setOf(publicMuseum, privateMuseum))
    }
}
