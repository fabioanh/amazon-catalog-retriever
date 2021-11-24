package dev.fanh.amazoncatalogretriever

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable

@DynamoDBTable(tableName = "species")
data class Species(
    val version: String,
    @DynamoDBHashKey
    val id: String,
    val kingdom: String,
    val behaviour: String? = null,
    @DynamoDBAttribute(attributeName = "commonNames")
    val commonNames: List<LanguagedValue>? = null,
    val description: String? = null,
    @DynamoDBAttribute(attributeName = "endangeredStatus")
    val endangeredStatus: List<LocalisedValue>? = null,
    val feeding: String? = null,
    val fullDescription: String? = null,
    val habitat: String? = null,
    val imageURLs: List<String>? = null,
    val lifecycle: String? = null,
    val lifeForm: String? = null,
    val migration: String? = null,
    val reproduction: String? = null,
    val scientificName: String
)

data class LanguagedValue(val value: String, val isoLanguage: String)

data class LocalisedValue(val value: String, val locale: String)