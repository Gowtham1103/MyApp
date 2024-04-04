package com.redweber.backendservice.services;

import com.redweber.backendservice.product.computer.DatabaseSequence;
import com.redweber.backendservice.product.highlights.Highlights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class EnquiryService {

    @Autowired
    private EnquiryRepository enquiryRepository;
    @Autowired
    private MongoOperations mongoOperations;
    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
    public void postEnquiry(Enquiry data) {
        Enquiry userDb = new Enquiry();
        userDb.setId(generateSequence(Highlights.SEQUENCE_NAME));
        userDb.setCustomerName(data.getCustomerName());
        userDb.setProductName(data.getProductName());
        userDb.setPhoneNo(data.getPhoneNo());
        userDb.setEmail(data.getEmail());
        userDb.setQuantity(data.getQuantity());
        enquiryRepository.save(userDb);
    }

    public List<Enquiry> getEnquiry() {
        return enquiryRepository.findAll();
    }


    public Enquiry getEnquiryById(Long productId) {
        return enquiryRepository.findById(productId).get();
    }
}
