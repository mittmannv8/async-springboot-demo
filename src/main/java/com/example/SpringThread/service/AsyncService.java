package com.example.SpringThread.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Async("singletonTaskPoolExecutor")
    public void process(int workers, int ids) throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        System.out.println("|_AsyncService\tstart\t" + threadName);

        ExecutorService pool = Executors.newFixedThreadPool(workers);

        List<SlowService> tasks = new ArrayList<>();

        for (int i = 1; i <= ids; i++) {
            tasks.add(new SlowService(i));
            System.out.println("|_AsyncService\tadd task(" + i + ")");
        }

        pool.invokeAll(tasks);
        System.out.println("|_AsyncService\tend\t" + threadName);
    };
}
