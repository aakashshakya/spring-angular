package com.poc.redis_proof_of_concept.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private int id;
    private String name;
    private String email;
    private String contactNumber;
    private boolean status;
}