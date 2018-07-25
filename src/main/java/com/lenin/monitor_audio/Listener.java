package com.lenin.monitor_audio;

import org.apache.log4j.Logger;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class Listener {
	
	private final static Logger logger = Logger.getLogger(App.class.getName());
	private Socket subscriber;
	private static final Context CONTEXT = ZMQ.context(1);
	private static int socketCounter = 0;
	private int socketType = 0;
	
	/**
	 * 
	 * @param subscriber
	 * @param port
	 * @param topic
	 */
	public Listener(int socketType) {
		super();
		subscriber = CONTEXT.socket(socketType);
		socketCounter++;
		logger.info("Create socket #"+socketCounter);
	}
	
	/**
	 * 
	 * @return socketType(int)
	 */
	public int getSocketType() {
		return socketType;
	}
	
	/**
	 * 
	 * @param socketType
	 */
	public void setSocketType(int socketType) {
		this.socketType = socketType;
	}

	/**
	 * 
	 * @return Socket
	 */
	public Socket getSubscriber() {
		return subscriber;
	}
	
	/**
	 * 
	 * @param subscriber
	 */
	public void setSubscriber(Socket subscriber) {
		this.subscriber = subscriber;
	}
	
	@Override
	public String toString() {
		return "Listener [subscriber=" + subscriber + "]";
	}

}
