package com.example.SpringThread.service;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class SlowService implements Callable<String> {

    private int id;

    public SlowService(int id) {
        this.id = id;
    }

    public String call() {
        String threadName = Thread.currentThread().getName();

        try {
            System.out.println(" |-> SlowService\tstart\tprocessing(->" + this.id + ") - " + threadName);

            TimeUnit.MILLISECONDS.sleep(50);

            System.out.println(" |-> SlowService\tstop\tprocessing(<-" + this.id + ") - " + threadName);
        } catch (InterruptedException e) {
            System.out.println(" |-> SlowService\tERROR\tprocessing(" + this.id + ") - " + threadName);
        }

        return "";

    }

}
