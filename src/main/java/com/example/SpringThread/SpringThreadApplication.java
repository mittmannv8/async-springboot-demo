package com.example.SpringThread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class SpringThreadApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringThreadApplication.class, args);
    }

    @Bean("singletonTaskPoolExecutor")
    public TaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("Async-");

        // Workaround to prevent multiple execution of the task
        // allowing only one worker and not allowing tasks
        // be added to the pool queue. This way will throws
        // a RejectedExecutionException for a second task
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(1);
        executor.setQueueCapacity(0);
        executor.setWaitForTasksToCompleteOnShutdown(true);

        return executor;
    }
}
