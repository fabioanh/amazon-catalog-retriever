package dev.fanh.amazoncatalogretriever

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.function.Function

@SpringBootApplication
@EnableDynamoDBRepositories
class AmazonCatalogRetrieverApplication

fun main(args: Array<String>) {
	runApplication<AmazonCatalogRetrieverApplication>(*args)
}