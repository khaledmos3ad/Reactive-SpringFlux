package com.khaledeng.reactive.model;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "user")
public class MyUser {
    @Id
    private String id;

    @NotBlank
    private String username;

    @NotBlank
    private String email;
    
    @NotBlank
    private String mobile;
    
    
}