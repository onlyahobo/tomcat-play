package com.example.empty;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

import static java.time.Duration.ofMinutes;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Slf4j
@Configuration
class ThreadPoolImpl {

    private final RejectedExecutionHandler handler = (runnable, executor) -> log.warn("My executor task {}", runnable);

    private final ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("My_Thread-%d").build();

    @Bean("myExecutor")
    Executor executor(MeterRegistry registry) {
        var executor = new ThreadPoolExecutor(
            500,
            533,
            ofMinutes(3).toMillis(), MILLISECONDS,
            new LinkedBlockingQueue<>(10),
            factory,
            handler
        );

        return ExecutorServiceMetrics.monitor(registry, executor, "MyExecutor");
    }

}
