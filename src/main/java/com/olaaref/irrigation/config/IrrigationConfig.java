package com.olaaref.irrigation.config;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import com.olaaref.irrigation.entity.IrrigationLog;
import com.olaaref.irrigation.entity.Plot;
import com.olaaref.irrigation.service.IrrigationLogService;
import com.olaaref.irrigation.service.PlotService;


@Component
public class IrrigationConfig {
	static final Logger LOGGER = Logger.getLogger(IrrigationConfig.class.getName());
	
	@Autowired
	private ThreadPoolTaskScheduler taskScheduler;
	
	@Autowired
	private PlotService plotService;
	
	@Autowired
	private IrrigationLogService logService;


	private void executeIrrigation(Plot plot) throws InterruptedException {
		taskScheduler.scheduleWithFixedDelay(new SensorRunnable(plot), plot.getDelay()*1000);
	}
	
	@PostConstruct
	public void automateIrrigationSystem() {
		
		List<Plot> plots = plotService.getAllPlots();
		
		plots.forEach((plot) -> {
			try {
				executeIrrigation(plot);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
	}
	
	private void sensorIrrigatePlot(Plot plot) throws InterruptedException {
		
		sendRequestToSensor(plot);
		changeSlotToOpen(plot);
		sleepForIrrigationDuration(plot);
		chengeSlotToClose(plot);
		saveLogsOfPlot(plot);
	}
	
	public String sendRequestToSensor(Plot plot) {
		 String msg = plotService.sendRequest(plot);
		 LOGGER.info(msg);
		 return msg;
	}

	public String chengeSlotToClose(Plot plot) {
		String closeMsg = plotService.closeSlot();
		plotService.updateSlotStatus(plot.getId(), false);
		LOGGER.info(closeMsg);
		return closeMsg;
	}

	public String changeSlotToOpen(Plot plot) {
		String openMsg = plotService.openSlot();
		plotService.updateSlotStatus(plot.getId(), true);
		LOGGER.info(openMsg);
		return openMsg;
	}
	
	public void sleepForIrrigationDuration(Plot plot) throws InterruptedException {
		long durationInMilli = plot.getCrop().getIrrigationDuration() * 1000;
		Thread.sleep(durationInMilli);
	}

	
	public String saveLogsOfPlot(Plot plot) {
		IrrigationLog log = new IrrigationLog(plot, true);
		IrrigationLog savedLog = logService.saveLog(log);
		String msg = String.format("The Plot %d has been irrigated successfully at %s.", savedLog.getPlot().getId(), savedLog.getIrrigatedDate().toString());
		LOGGER.info(msg);
		return msg;
	}
	
	
	
	class SensorRunnable implements Runnable {
		
		
		private Plot plot;
		
		public SensorRunnable(Plot plot) {
			this.plot = plot;
		}

		

		@Override
		public void run() {
			
			try {
				sensorIrrigatePlot(plot);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
