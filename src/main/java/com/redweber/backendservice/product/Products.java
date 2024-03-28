package com.redweber.backendservice.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Products {
    @Transient
    public static final String SEQUENCE_NAME = "product_sequence";
    @Id
    private Long id;
    private String image_URL;
    private String title;
    private int quantity;
    private String description;
    private int price;
    private String status;

}
