package com.zibilal.externalfilelogger;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by bilalmuhammad on 9/22/14.
 */
public class Logger {
    private ArrayBlockingQueue<Message> messages;

    public Logger(int size){
        messages = new ArrayBlockingQueue<Message>(size);
    }

    public int size(){
        return messages.size();
    }

    public void set(Message message) throws InterruptedException{
        messages.put(message);
    }

    public Message get() throws InterruptedException{
        return messages.take();
    }
}
