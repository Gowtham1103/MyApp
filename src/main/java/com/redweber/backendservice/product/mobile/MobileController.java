package com.redweber.backendservice.product.mobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mobile")
@CrossOrigin("*")
public class MobileController {

    @Autowired
    private MobileService mobileService;
    @PostMapping("/post-products")
    public Mobile addMobile(@RequestBody Mobile products){
        return mobileService.addProduct(products);
    }
    @GetMapping("/get-products")
    public List<Mobile> getMobile(){
        return mobileService.getMobile();
    }
    @GetMapping("/get-product/{id}")
    public Mobile getMobileById(@PathVariable("id") Long productId){
        return mobileService.getMobileById(productId);
    }
    @DeleteMapping("delete-product/{id}")
    public ResponseEntity<String> deleteMobileById(@PathVariable("id") Long productId){
        return mobileService.deleteMobileById(productId);
    }
    @PutMapping("update-product/{id}")
    public String  updateMobileById(@PathVariable("id") Long productId , @RequestBody Mobile data){
        return mobileService.updateMobileById(productId,data);
    }
}
