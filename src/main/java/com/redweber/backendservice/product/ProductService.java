package com.redweber.backendservice.product;
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
public class ProductService {
    @Autowired
    private MongoOperations mongoOperations;
    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
    @Autowired
    private ProductRepository productRepository;
    public Products addProduct(Products products) {
        Products productsTemp = new Products();
        productsTemp.setId(generateSequence(Products.SEQUENCE_NAME));
        productsTemp.setDescription(products.getDescription());
        productsTemp.setImage_URL(products.getImage_URL());
        productsTemp.setTitle(products.getTitle());
        productsTemp.setPrice(products.getPrice());
        productsTemp.setQuantity(products.getQuantity());
        productsTemp.setStatus(products.getStatus());
        return productRepository.save(productsTemp);
    }
    public List<Products> getProducts() {
        return productRepository.findAll();
    }

    public Products getProductById(Long productId) {
        return productRepository.findById(productId).get();
    }

    public ResponseEntity<String> deleteById(Long productId) {
        productRepository.deleteById(productId);
        return ResponseEntity.ok("Data deleted successfully !");
    }
    public String updateProductById(Long productId, Products data) {
        Products existingData = productRepository.findById(productId).orElse(null);
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

        productRepository.save(existingData);
        return "Data Updated Successfully!";
    }


}
