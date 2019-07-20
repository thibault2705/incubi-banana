package com.banana.center.employee.endpoint;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.banana.center.employee.model.Employee;
import com.banana.center.employee.model.Employees;
import com.banana.center.employee.repository.EmployeeRepository;
import com.banana.center.employee.service.LogService;
import com.banana.center.enumeration.Status;

@Path("employee")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class EmployeeEndpoint {

    private EmployeeRepository employeeRepository;

    public EmployeeEndpoint(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("")
    public Response addNewEmployee(
    		@FormParam("account") String account) {
    	Employee employee = new Employee(account, Status.ACTIVE);
    	LogService.logWithDelayTime(employee);
    	employeeRepository.add(employee);

        return Response.accepted(employeeRepository.list().size()).build();
    }

    @GET
    @Path("{account}")
    public Response checkEmployee(
    		@PathParam("account") String account) {

        final Employees matchingEmployees = employeeRepository.list().filterByAccount(account);

        if (null == account || matchingEmployees.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
            			   .entity("Could not process this request ")
            			   .build();
        }

        return Response.accepted(matchingEmployees.first()).build();
    }

    @POST
    @Path("{account}/checkin")
    public Response checkin(
    		@PathParam("account") String account,
    		@FormParam("localtime") String localtime) {

        final Employees matchingEmployees = employeeRepository.list().filterByAccount(account);

        if (null == account
        		|| matchingEmployees.isEmpty()
        		|| matchingEmployees.first().getStatus() != Status.ACTIVE) {
            return Response.status(Response.Status.BAD_REQUEST)
            			   .entity("Could not process this request ")
            			   .build();
        }

        matchingEmployees.first().checkin();
        return Response.accepted(matchingEmployees.first()).build();
    }

    @POST
    @Path("{account}/checkout")
    public Response checkout(
    		@PathParam("account") String account,
    		@FormParam("localtime") String localtime) {

        final Employees matchingEmployees = employeeRepository.list().filterByAccount(account);

        if (null == account
        		|| matchingEmployees.isEmpty()
        		|| matchingEmployees.first().getStatus() != Status.CHECKED_IN
        		&& matchingEmployees.first().getStatus() != Status.CHECKED_OUT) {
            return Response.status(Response.Status.BAD_REQUEST)
            			   .entity("Could not process this request")
            			   .build();
        }

        matchingEmployees.first().checkout();

        return Response.accepted(matchingEmployees.first()).build();
    }

    @POST
    @Path("{account}/inactive")
    public Response inactiveEmployee(
    		@PathParam("account") String account) {

        final Employees matchingEmployees = employeeRepository.list().filterByAccount(account);

        if (null == account
        		|| matchingEmployees.isEmpty()
        		|| matchingEmployees.first().getStatus() == Status.INACTIVE) {
            return Response.status(Response.Status.BAD_REQUEST)
            			   .entity("Could not process this request")
            			   .build();
        }

        matchingEmployees.first().inactive();

        return Response.accepted(matchingEmployees.first()).build();
    }

    @POST
    @Path("{account}/active")
    public Response activeEmployee(
    		@PathParam("account") String account) {

    	final Employees matchingEmployees = employeeRepository.list().filterByAccount(account);

        if (null == account
        		|| matchingEmployees.isEmpty()
        		|| matchingEmployees.first().getStatus() != Status.INACTIVE) {
            return Response.status(Response.Status.BAD_REQUEST)
            			   .entity("Could not process this request")
            			   .build();
        }

        matchingEmployees.first().active();

        return Response.accepted(matchingEmployees.first()).build();
    }

    @GET
    @Path("")
    public Response getAllEmployees(
    		@QueryParam("account") String account,
    		@QueryParam("localtime") String localtime,
    		@QueryParam("id") String id,
    		@QueryParam("status") String status) {

    	Employees list = employeeRepository.list();

        if (null != id) {
            return Response.accepted(list.filterById(id).filterOutInactive()).build();
        }
        if (null != account) {
            list = list.filterByAccount(account);
        }
        if (null != localtime) {
            list = list.filterByLocaltime(localtime);
        }
        if (null != status) {
            list = list.filterByStatus(status);
        }

        return Response.accepted(list.filterOutInactive()).build();
    }

}
