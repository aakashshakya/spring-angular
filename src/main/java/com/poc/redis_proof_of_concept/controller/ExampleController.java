package com.poc.redis_proof_of_concept.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class ExampleController {
    List<String> names = new ArrayList<>();

    @GetMapping("get-data")
    public List<String> getNames() throws InterruptedException {
        return names;
    }

    @GetMapping("set-data")
    public void setName(@RequestParam("name") String name) throws Exception {
        if (name.equalsIgnoreCase("error")) {
            Thread.sleep(5000);
            throw new Exception("Exception occurred");
        }
        System.out.println(name);
        Thread.sleep(10000);
        names.add(name);
    }
}
