package com.banana.center.employee.repository;

import com.banana.center.employee.model.Employee;
import com.banana.center.employee.model.Employees;
import com.banana.center.enumeration.Status;

public class EmployeeRepository {
	private final Employees employees;
	
    public EmployeeRepository() {
    	employees = new Employees();
    	employees.add(new Employee("incubi", Status.ACTIVE));
    }
    
    public Employees list() {
        return employees;
    }

    public void add(Employee Employee) {
    	employees.add(Employee);
    }
}
