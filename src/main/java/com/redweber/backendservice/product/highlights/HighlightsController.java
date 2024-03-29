package com.redweber.backendservice.product.highlights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/highlight")
public class HighlightsController {
    @Autowired
    private HighlightsService highlightsService;
    @PostMapping("/post-products")
    public Highlights addHighlight(@RequestBody Highlights products){
        return highlightsService.addHighlight(products);
    }
    @GetMapping("/get-products")
    public List<Highlights> getProducts(){
        return highlightsService.getProducts();
    }
    @GetMapping("/get-product/{id}")
    public Highlights getProductById(@PathVariable("id") Long productId){
        return highlightsService.getProductById(productId);
    }
    @DeleteMapping("delete-product/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long productId){
        return highlightsService.deleteById(productId);
    }
    @PutMapping("update-product/{id}")
    public String  updateProductById(@PathVariable("id") Long productId , @RequestBody Highlights data){
        return highlightsService.updateProductById(productId,data);
    }
}
