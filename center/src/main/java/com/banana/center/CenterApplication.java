package com.banana.center;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.banana.center.employee.endpoint.EmployeeEndpoint;
import com.banana.center.employee.repository.EmployeeRepository;
import com.banana.center.exception.CustomExceptionMapper;

public class CenterApplication extends ResourceConfig {
    public CenterApplication() {
        this(new EmployeeEndpoint(new EmployeeRepository()));
    }

    public CenterApplication(EmployeeEndpoint employeeEndpoint) {
        register(employeeEndpoint);
        register(RequestContextFilter.class);
        register(JacksonJaxbJsonProvider.class);
        register(CustomExceptionMapper.class);
    }
}
