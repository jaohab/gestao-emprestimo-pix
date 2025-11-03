package com.java.gestaoemprestimopix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class GestaoEmprestimoPixApplication {


    public static void main(String[] args) {

        SpringApplication.run(GestaoEmprestimoPixApplication.class, args);
    }
}