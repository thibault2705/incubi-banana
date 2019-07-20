package com.banana.center.employee.service;

import com.banana.center.employee.model.Employee;

public class LogService {
	
	public static void logWithDelayTime(Employee employee) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println(employee.toString());
	}

}
