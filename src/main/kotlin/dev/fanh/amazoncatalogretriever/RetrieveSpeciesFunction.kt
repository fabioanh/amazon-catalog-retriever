package dev.fanh.amazoncatalogretriever

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import com.google.gson.Gson
import org.springframework.stereotype.Component
import java.util.function.Function

@Component
class RetrieveSpeciesFunction(private val speciesRepository: SpeciesRepository) :
    Function<String, APIGatewayProxyResponseEvent> {
    override fun apply(speciesId: String): APIGatewayProxyResponseEvent {
        val species = speciesRepository.findById(speciesId)
        return species.map { APIGatewayProxyResponseEvent().withStatusCode(200).withBody(Gson().toJson(it)) }
            .orElseGet { APIGatewayProxyResponseEvent().withStatusCode(404) }
    }
}