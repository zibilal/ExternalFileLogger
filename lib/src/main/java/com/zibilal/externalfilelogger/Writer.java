package com.zibilal.externalfilelogger;

/**
 * Created by bilalmuhammad on 9/22/14.
 */
public class Writer implements Runnable {
    private Logger logger;
    private Storage storage;

    public Writer(Storage storage, Logger logger) {
        this.logger=logger;
        this.storage=storage;
    }

    @Override
    public void run() {
        try {
            while(true) {
                Message message = logger.get();
                if(message != null) {
                    storage.save(message);
                }
            }
        } catch (InterruptedException e) {
        }
    }
}
