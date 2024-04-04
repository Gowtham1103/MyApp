package com.redweber.backendservice.services;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnquiryRepository extends MongoRepository<Enquiry,Long> {
}
