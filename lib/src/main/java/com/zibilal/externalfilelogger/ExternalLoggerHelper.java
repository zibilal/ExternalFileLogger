package com.zibilal.externalfilelogger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bilalmuhammad on 9/22/14.
 */
public class ExternalLoggerHelper {
    private Storage storage;
    private Logger logger;

    private int capacity;

    private Writer writer;

    public ExternalLoggerHelper(Storage storage, int capacity) {
        this.storage=storage;
        this.capacity=capacity;
        logger = new Logger(capacity);
        writer = new Writer(storage, logger);

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(writer);
        executor.shutdown();
    }

    public int getCapacity(){
        return capacity;
    }

    public Storage getStorage(){
        return storage;
    }

    public void log(String str) {
        try {
            Message message = new Message();
            message.setMessage(str);
            logger.set(message);
        } catch (InterruptedException e) {
        }
    }
}
