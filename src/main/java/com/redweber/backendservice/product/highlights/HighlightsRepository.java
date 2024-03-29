package com.redweber.backendservice.product.highlights;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HighlightsRepository extends MongoRepository<Highlights,Long> {
}
