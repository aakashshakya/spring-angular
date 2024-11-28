package com.poc.redis_proof_of_concept;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class RedisProofOfConceptApplication {

    public static void main(final String[] args) {
        SpringApplication.run(RedisProofOfConceptApplication.class, args);
    }

}
