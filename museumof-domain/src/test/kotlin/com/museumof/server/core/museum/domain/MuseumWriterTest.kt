package com.museumof.server.core.museum.domain

import com.museumof.server.core.museum.port.`in`.EditMuseumCommand
import com.museumof.server.core.museum.port.out.MuseumRepositoryPort
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.eq

@ExtendWith(MockitoExtension::class)
class MuseumWriterTest {
    @Mock
    lateinit var museumRepository: MuseumRepositoryPort

    @InjectMocks
    lateinit var museumWriter: MuseumWriter

    @Test
    fun `뮤지엄 엔티티 변경 커맨드 중에서 NULL이 아닌 필드만 업데이트 되어야 한다`() {
        val updateCommand = EditMuseumCommand(id = 1L, museumName = "변경된 이름")
        val originalEntity = Museum(1L, "변경 전 이름", 100L, "테스트입니다.", false, true)

        `when`(museumRepository.getMuseumById(eq(1L))).thenReturn(originalEntity)
        `when`(museumRepository.editMuseum(any())).thenAnswer{i -> i.getArguments()[0]}

        val updatedEntity = museumWriter.editMuseum(updateCommand)

        assertThat(updatedEntity).isEqualTo(Museum(1L, "변경된 이름", 100L, "테스트입니다.", false, true))
    }
}
