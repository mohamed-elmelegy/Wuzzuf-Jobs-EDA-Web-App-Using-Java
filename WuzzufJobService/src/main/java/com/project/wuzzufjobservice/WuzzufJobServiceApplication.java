package com.project.wuzzufjobservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class WuzzufJobServiceApplication {

    public static void main(String[] args) throws URISyntaxException, IOException {
//        Desktop.getDesktop().browse(new URI("http://localhost:8080/api/all_data"));
        SpringApplication.run(WuzzufJobServiceApplication.class, args);
    }
}
