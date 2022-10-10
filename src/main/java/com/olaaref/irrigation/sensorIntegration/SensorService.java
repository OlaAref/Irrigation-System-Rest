package com.olaaref.irrigation.sensorIntegration;

import java.util.logging.Logger;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.olaaref.irrigation.exception.SensorNotAvailableException;

@Service
public class SensorService {
	static final Logger LOGGER = Logger.getLogger(SensorService.class.getName());
	
	@Retryable(value = {SensorNotAvailableException.class}, maxAttempts = 3, backoff = @Backoff(delay = 2000))
	public String sendRequest(Integer plotId) throws SensorNotAvailableException {
		
		if(Math.random() < .60) {
			LOGGER.warning("Retry to connect to sensor again ..");
			throw new SensorNotAvailableException("Sensor is not availabe, please try again later.");
		}
		return "irrigate request arrived to sensor from Plot " + plotId;
	}
	
//	@Recover
//	public String sendRequestFallback(SensorNotAvailableException e, Integer plotId) throws SensorNotAvailableException {
//		LOGGER.warning("Calling Sensor failed.");
//		return "Calling Sensor failed.";
//	}


	public String openSlot() {
		return "Slot is opened up.";
	}


	public String closeSlot() {
		return "Slot is closed.";
	}

}
