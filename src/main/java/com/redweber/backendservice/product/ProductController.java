package com.redweber.backendservice.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/post-products")
    public Products addProduct(@RequestBody Products products){
        return productService.addProduct(products);
    }
    @GetMapping("/get-products")
   public List <Products> getProducts(){
        return productService.getProducts();
    }
    @GetMapping("/get-product/{id}")
    public Products getProductById(@PathVariable("id") Long productId){
        return productService.getProductById(productId);
    }
    @DeleteMapping("delete-product/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long productId){
        return productService.deleteById(productId);
    }
    @PutMapping("update-product/{id}")
    public String  updateProductById(@PathVariable("id") Long productId , @RequestBody Products data){
        return productService.updateProductById(productId,data);
    }
}
