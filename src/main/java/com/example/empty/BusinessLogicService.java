package com.example.empty;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static java.lang.Thread.sleep;

@Slf4j
@Service
class BusinessLogicService {

    private final Executor executor;

    @Autowired BusinessLogicService(Executor myExecutor) {
        this.executor = myExecutor;
    }

    public CompletableFuture<Integer> doBusinessLogic(Integer counter) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                log.info("Sleeping in {}...", Thread.currentThread().getName());
                sleep(Duration.ofSeconds(3).toMillis());
                log.info("Finished sleeping in {}...", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return counter;
        }, executor);
    }

}
