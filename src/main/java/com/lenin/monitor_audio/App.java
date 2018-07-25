package com.lenin.monitor_audio;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.zeromq.ZMQ;

import com.lenin.monitor_audio.config.PropertiesRead;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	/**
    	 * Thread for listener begin
    	 */
        Subscriber init = new Subscriber(
        		PropertiesRead.getIpAddressServer(), 
        		PropertiesRead.getPortServerInit(), 
        		PropertiesRead.getTopicServerInit(),
        		ZMQ.SUB);
        
        /**
    	 * Thread for listener finish
    	 */
        Subscriber finish = new Subscriber(PropertiesRead.getIpAddressServer(),
        		PropertiesRead.getPortServerFinish(), 
        		PropertiesRead.getTopicServerFinish(),
        		ZMQ.SUB);
        
        /**
    	 * Threads management two threads
    	 */
        ExecutorService threadsManagement = Executors.newFixedThreadPool(2);
        threadsManagement.execute(init);
        threadsManagement.execute(finish);
        
        threadsManagement.shutdown();
    }
}
