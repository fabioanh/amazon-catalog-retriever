package dev.fanh.amazoncatalogretriever

import com.google.gson.JsonParser
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class RetrieveSpeciesFunctionTest {

    @MockK
    lateinit var speciesRepository: SpeciesRepository

    @InjectMockKs
    lateinit var function: RetrieveSpeciesFunction

    @Test
    fun apply_regularSpeciesId_successfulResponse() {
        // given
        val speciesId = "123"
        val sampleSpecies =
            Species(version = "V1", id = speciesId, kingdom = "kingdom", scientificName = "scientific name")
        val expectedResult =
            """
                {
                "version": "V1",
                "id": "$speciesId",
                "kingdom": "kingdom",
                "scientificName": "scientific name"
                }
            """.trimIndent()

        every { speciesRepository.findById(speciesId) }.returns(Optional.of(sampleSpecies))

        // when
        val result = function.apply(speciesId)

        // then
        verify { speciesRepository.findById(speciesId) }
        Assertions.assertEquals(JsonParser.parseString(expectedResult), JsonParser.parseString(result.body))
        Assertions.assertEquals(200, result.statusCode)
    }

    @Test
    fun apply_invalidSpeciesId_emptyResponse() {
        // given
        val speciesId = "123"

        every { speciesRepository.findById(speciesId) }.returns(Optional.empty())

        // when
        val result = function.apply(speciesId)

        // then
        verify { speciesRepository.findById(speciesId) }
        Assertions.assertEquals(404, result.statusCode)
    }
}