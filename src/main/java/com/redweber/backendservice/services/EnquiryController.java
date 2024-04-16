package com.redweber.backendservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("https://dinezzzzz.github.io/copy/")
public class EnquiryController {
    @Autowired
    private EnquiryService enquiryService;

    @PostMapping("post-enquiry")
    public ResponseEntity <?> postEnquiry(@RequestBody Enquiry data){
        enquiryService.postEnquiry(data);
        return ResponseEntity.ok("Enquiry send successfully !");
    }
    @GetMapping("get-enquiry")
    public List <Enquiry> getEnquiry(){
        return enquiryService.getEnquiry();
    }
    @GetMapping("get-enquiry/{id}")
    public Enquiry getEnquiryById(@PathVariable("id") Long productId){
        return enquiryService.getEnquiryById(productId);
    }

}
