package com.example.empty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*
https://docs.oracle.com/javaee/7/tutorial/servlets013.htm
https://tomcat.apache.org/whichversion.html
*/

@SpringBootApplication
public class EmptyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(EmptyApplication.class, args);
    }

}
