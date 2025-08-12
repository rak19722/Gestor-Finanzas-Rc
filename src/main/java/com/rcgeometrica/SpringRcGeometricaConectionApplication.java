package com.rcgeometrica;

import com.rcgeometrica.repository.IobrasRepository;
import com.rcgeometrica.repository.IgastosRepository;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class SpringRcGeometricaConectionApplication {

    public static void main(String[] args) {
        System.out.println("Hola mundo");
        SpringApplication.run(SpringRcGeometricaConectionApplication.class, args);
    }

    @Autowired
    private IobrasRepository obrasRepository;

    @Autowired
    private IgastosRepository gastosRepository;




}
