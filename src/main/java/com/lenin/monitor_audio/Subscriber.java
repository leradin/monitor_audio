package com.lenin.monitor_audio;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.lenin.monitor_audio.config.PropertiesRead;

public class Subscriber extends Listener implements Runnable {
	
	private final static Logger logger = Logger.getLogger(App.class.getName());
	private String ipAddress;
	private String port;
	private String topic;
	
	

	public Subscriber(String ipAdress,String port, String topic,int socketType) {
		super(socketType);
		this.ipAddress = ipAdress;
		this.port = port;
		this.topic = topic;
		super.getSubscriber().connect("tcp://"+this.ipAddress+":"+this.port);
        super.getSubscriber().subscribe(this.topic.getBytes());
	}

	@Override
	public void run() {
		logger.info("Monitor Audio "+topic+"...");
        while (!Thread.currentThread ().isInterrupted ()) {
            // Read envelope with address
            String address = super.getSubscriber().recvStr ();
            // Read message contents
            String contents = super.getSubscriber().recvStr ();
            logger.info(address + " : " + contents);
            if(topic.equals("ADA")){
            	Script.run(PropertiesRead.getScriptInit());
            }else{
            	Script.run(PropertiesRead.getScriptFinish(),getIdExercise(contents));
            }
        }
	}
	

	@Override
	public String toString() {
		return "Subscriber [ipAddress=" + ipAddress + ", port=" + port + ", topic=" + topic + "]";
	}
	
	private String getIdExercise(String contents){
		String id = "";
		try {
			JSONObject object = (JSONObject) new JSONParser().parse(contents);
			 id = String.valueOf(object.get("id"));
			 logger.info("ID : "+id);
		} catch (NullPointerException e) {
			logger.error("NullPointerException "+e.getMessage());
		} catch (ParseException e) {
			logger.error("ParseException "+e.getMessage());
		}
		return id;
	}
	
}
