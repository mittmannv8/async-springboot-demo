package com.example.SpringThread.controller;

import java.util.concurrent.RejectedExecutionException;

import com.example.SpringThread.model.Response;
import com.example.SpringThread.service.AsyncService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @Autowired
    AsyncService asyncService;

    @GetMapping("/async")
    public ResponseEntity<Response> process(@RequestParam("workers") int workers,
            @RequestParam("ids") int ids) {

        String threadName = Thread.currentThread().getName();
        System.out.println("Controller\tstart\t" + threadName);

        Response response = new Response();
        response.setStatus("STARTED");

        try {
            asyncService.process(workers, ids);
        } catch (RejectedExecutionException | InterruptedException e) {
            System.out.println("Controller\tERROR\t" + e);
            response.setStatus("FAILED");
        }

        System.out.println("Controller\tend\t" + threadName);
        return ResponseEntity.ok(response);
    }
}
