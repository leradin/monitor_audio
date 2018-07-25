package com.lenin.monitor_audio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class Script {
	private final static Logger logger = Logger.getLogger(App.class.getName());
	
	/**
	 * 
	 * @param name
	 * @param arguments
	 */
	public static void run(String name,String... arguments){

		Process process;
		try {
			if(arguments.length > 0){
				arguments[0] = arguments[0]+"_"+System.currentTimeMillis();
				logger.info("Executing sh  "+name + " "+arguments[0]);
				process = Runtime.getRuntime().exec(name + " "+arguments[0]);
			}else{
				logger.info("Executing sh  "+name);
				process = Runtime.getRuntime().exec(name);
			}
			process.waitFor();
			
			BufferedReader reader = 
			         new BufferedReader(new InputStreamReader(process.getInputStream()));

			    String line = "";			
			    while ((line = reader.readLine())!= null) {
			    	logger.info(line + "\n");
			    }
		} catch (IOException e) {
			logger.error(e.getClass() +" : "+e.getMessage());
		} catch (InterruptedException e) {
			logger.error(e.getClass() +" : "+e.getMessage());
		}
	}
}
