package com.redweber.backendservice.product.mobile;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileRepository extends MongoRepository<Mobile,Long> {
}
