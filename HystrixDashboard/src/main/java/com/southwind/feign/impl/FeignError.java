package com.southwind.feign.impl;

import com.southwind.entity.Student;
import com.southwind.feign.FeignProviderClient;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class FeignError implements FeignProviderClient {
    @Override
    public Collection<Student> findAll() {
        return null;
    }

    @Override
    public String index() {
        return "服务器器维护中......";
    }

    @Override
    public Student findById(long id) {
        return null;
    }

    @Override
    public void save(Student student) {

    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void deleteById(long id) {

    }
}
