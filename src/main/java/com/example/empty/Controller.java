package com.example.empty;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequestMapping("/empty")
@RestController
class Controller {

    private final BusinessLogicService businessLogicService;

    private static int COUNTER = 0;

    @Autowired Controller(BusinessLogicService businessLogicService) {
        this.businessLogicService = businessLogicService;
    }

    @GetMapping(value = "1", produces = "text/plain")
    CompletableFuture<String> endpoint1() {
        log.info("Request no {} processing", COUNTER++);
        return businessLogicService.doBusinessLogic(COUNTER).thenApply(String::valueOf);
    }

    @GetMapping(value = "2", produces = "text/plain")
    String endpoint2() {
        return endpoint1().thenApply(String::valueOf).join();
    }

}
