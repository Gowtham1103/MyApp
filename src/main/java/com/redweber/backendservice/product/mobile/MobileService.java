package com.redweber.backendservice.product.mobile;

import com.redweber.backendservice.product.computer.DatabaseSequence;
import com.redweber.backendservice.product.computer.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class MobileService {
    @Autowired
    private MongoOperations mongoOperations;
@Autowired
private MobileRepository mobileRepository;

    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
    public Mobile addProduct(Mobile products) {

        Mobile productsTemp = new Mobile();
        System.out.println(productsTemp);
        productsTemp.setId(generateSequence(Mobile.SEQUENCE_NAME));
        productsTemp.setDescription(products.getDescription());
        productsTemp.setImage_URL(products.getImage_URL());
        productsTemp.setTitle(products.getTitle());
        productsTemp.setPrice(products.getPrice());
        productsTemp.setQuantity(products.getQuantity());
        productsTemp.setStatus(products.getStatus());
        return mobileRepository.save(productsTemp);
    }

    public List<Mobile> getMobile() {
        return mobileRepository.findAll();
    }

    public Mobile getMobileById(Long productId) {
        return mobileRepository.findById(productId).get();
    }

    public ResponseEntity<String> deleteMobileById(Long productId) {
        mobileRepository.deleteById(productId);
        return ResponseEntity.ok("Data deleted successfully !");
    }

    public String updateMobileById(Long productId, Mobile data) {
        Mobile existingData = mobileRepository.findById(productId).orElse(null);
        if (existingData == null) {
            return "Product not found";
        }

        if (Objects.nonNull(data.getTitle()) && !"".equalsIgnoreCase(data.getTitle())) {
            existingData.setTitle(data.getTitle());
        }
        if (Objects.nonNull(data.getDescription()) && !"".equalsIgnoreCase(data.getDescription())) {
            existingData.setDescription(data.getDescription());
        }
        if (Objects.nonNull(data.getQuantity())) {
            existingData.setQuantity(data.getQuantity());
        }
        if (Objects.nonNull(data.getPrice())) {
            existingData.setPrice(data.getPrice());
        }
        if (Objects.nonNull(data.getStatus()) && !"".equalsIgnoreCase(data.getStatus())) {
            existingData.setStatus(data.getStatus());
    }
        mobileRepository.save(existingData);
        return "Data Updated Successfully!";
    }
}
