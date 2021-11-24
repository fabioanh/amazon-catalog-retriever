package dev.fanh.amazoncatalogretriever

import org.springframework.data.repository.CrudRepository

interface SpeciesRepository : CrudRepository<Species, String> {
}
