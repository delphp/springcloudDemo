package com.southwind.controller;

import com.southwind.entity.Student;
import com.southwind.feign.FeignProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.Collection;

@RestController
@RequestMapping("/feign")
public class FeignHandler {
    @Autowired
    private FeignProviderClient feignProviderClient;
    @GetMapping("/findAll")
    public Collection<Student> findAll(){
        return feignProviderClient.findAll();
    }
    @GetMapping("/index")
    public String index(){
        return feignProviderClient.index();
    }

    @GetMapping("/findById/{id}")
    public Student findById(@PathVariable("id") long id){return feignProviderClient.findById(id);}
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id){
    feignProviderClient.deleteById(id);}
}
