package com.redweber.backendservice.product.highlights;
import com.redweber.backendservice.product.computer.DatabaseSequence;
import com.redweber.backendservice.product.computer.Products;
import com.redweber.backendservice.product.mobile.Mobile;
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
public class HighlightsService {
    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private HighlightsRepository highlightsRepository;

    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }


    public List<Highlights> getProducts() {
        return highlightsRepository.findAll();
    }

    public Highlights getProductById(Long productId) {
        return highlightsRepository.findById(productId).get();
    }

    public ResponseEntity<String> deleteById(Long productId) {
        highlightsRepository.deleteById(productId);
        return ResponseEntity.ok("Data deleted successfully !");
    }

    public String updateProductById(Long productId, Highlights data) {
        Highlights existingData = highlightsRepository.findById(productId).orElse(null);
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
        if (Objects.nonNull(data.getImage_URL()) && !"".equalsIgnoreCase(data.getImage_URL())) {
            existingData.setImage_URL(data.getImage_URL());
        }

        highlightsRepository.save(existingData);
        return "Data Updated Successfully!";
    }

    public Highlights addHighlight(Highlights products) {
        Highlights userDb = new Highlights();
        userDb.setId(generateSequence(Highlights.SEQUENCE_NAME));
        userDb.setDescription(products.getDescription());
        userDb.setImage_URL(products.getImage_URL());
        userDb.setTitle(products.getTitle());
        userDb.setPrice(products.getPrice());
        userDb.setQuantity(products.getQuantity());
        userDb.setStatus(products.getStatus());
        return highlightsRepository.save(userDb);
    }
}
