package com.redweber.backendservice.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "enquiries")
public class Enquiry {
    @Id
    private Long id;
    private String productName;
    private String customerName;
    private String email;
    private String phoneNo;
    private int quantity;
}
