package com.banana.center.employee.repository;

import com.banana.center.employee.model.Employee;
import com.banana.center.employee.model.Employees;

public class EmployeeRepository {
	private final Employees employees;
	
    public EmployeeRepository() {
    	employees = new Employees();
    }
    
    public Employees list() {
        return employees;
    }

    public void add(Employee Employee) {
    	employees.add(Employee);
    }
}
