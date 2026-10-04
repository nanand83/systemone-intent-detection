package com.example.intentdetection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.intentdetection.service.IntentDetectionService;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication {

    @Autowired 
    private IntentDetectionService intentDetectionService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("🚀 App Launcher started successfully!");
            
            if (args.length == 0) {
                System.out.println("No input provided.");
                return;
            }

            String fullUtterance = String.join(" ", args);
            System.out.println("Utterance: " + fullUtterance);
            
            System.out.println(intentDetectionService.detectIntent(fullUtterance));
        };
    }
}
