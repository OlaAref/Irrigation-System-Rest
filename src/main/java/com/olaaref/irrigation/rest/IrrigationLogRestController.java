package com.olaaref.irrigation.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olaaref.irrigation.entity.IrrigationLog;
import com.olaaref.irrigation.exception.PlotNotFoundException;
import com.olaaref.irrigation.service.IrrigationLogService;


@RestController
@RequestMapping("/logs")
public class IrrigationLogRestController {

	@Autowired
	private IrrigationLogService logService;
	
	@GetMapping("/")
	public List<IrrigationLog> getAllLogs(){
		return logService.getAllLogs();
	}
	
	@GetMapping("/plot/{id}")
	public List<IrrigationLog> getByPlot(@PathVariable("id") Integer id) throws PlotNotFoundException{
		return logService.findByPlot(id);
	}

}
