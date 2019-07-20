package com.banana.center.employee.model;

import static java.util.stream.Collectors.toList;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.banana.center.enumeration.Status;

@XmlRootElement
public class Employees {

    @XmlElement(name = "result")
    private final ConcurrentLinkedQueue<Employee> employees;
    private AtomicInteger counter = new AtomicInteger();
    
    public Employees() {
    	employees = new ConcurrentLinkedQueue<>();
    }
    
    private Employees(ConcurrentLinkedQueue<Employee> employees) {
        this.employees = employees;
    }

    public Employee first() {
        return employees.peek();
    }

    public void add(Employee book) {
        book.setId(counter.getAndIncrement());
        employees.add(book);
    }

    public int size() {
        return employees.size();
    }
    
    public Employees filterById(String id) {
    	return new Employees(new ConcurrentLinkedQueue<>(
    			employees.stream()
    			.filter(x -> x.getId() == Integer.valueOf(id))
    			.collect(toList())));
    }

    public Employees filterByAccount(String account) {
        return new Employees(new ConcurrentLinkedQueue<>(
        		employees.stream()
        		.filter(x -> x.getAccount().contains(account))
        		.collect(toList())));
    }

    public Employees filterByLocaltime(String localtime) {
        return new Employees(new ConcurrentLinkedQueue<>(
        		employees.stream()
        		.filter(x -> x.getLocaltime().toUpperCase()
        				.contains(localtime.toUpperCase()))
        		.collect(toList())));
    }

    public Employees filterByStatus(String status) {
        return new Employees(new ConcurrentLinkedQueue<>(
        		employees.stream()
        		.filter(x -> x.anyStatus()
        				.contains(Status.fromValue(Integer.valueOf(status))))
        		.collect(toList())));
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public String getRequestTime() {
        return DateTimeFormatter.ISO_LOCAL_TIME.format(LocalTime.now());
    }

    public Employees filterOutInactive() {
        return new Employees(new ConcurrentLinkedQueue<>(
        		employees.stream()
        		.filter(x -> x.getStatus() != Status.INACTIVE)
        		.collect(toList())));
    }
}
