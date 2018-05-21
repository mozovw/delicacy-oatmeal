package com.delicacy.oatmeal.test;

/**
 * 秒表
 */
public class Stopwatch {
 
    private long startTime = 0;
    private long stopTime = 0;
    private boolean running = false;

    private long getMilliseconds(){return System.currentTimeMillis();}


    public void start() {
        this.startTime = getMilliseconds();
        this.running = true;
    }
 
    public void stop() {
        this.stopTime = getMilliseconds();
        this.running = false;
    }
 
    public long getElapsedTime() {
        long elapsed;
        if (running) {
            elapsed = (getMilliseconds() - startTime);
        } else {
            elapsed = (stopTime - startTime);
        }
        return elapsed;
    }
 
    public long getElapsedTimeSecs() {
        long elapsed;
        if (running) {
            elapsed = ((getMilliseconds() - startTime) / 1000);
        } else {
            elapsed = ((stopTime - startTime) / 1000);
        }
        return elapsed;
    }

}