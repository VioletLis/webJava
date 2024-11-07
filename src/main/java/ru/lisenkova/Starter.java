package ru.lisenkova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static ru.lisenkova.DBCreationScript.createDB;

@SpringBootApplication(scanBasePackages = "ru.lisenkova")
public class Starter {
    public static void main(String[] args) {
        SpringApplication.run(Starter.class,args);
        createDB();
    }
}