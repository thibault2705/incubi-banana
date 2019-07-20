package com.banana.request.service;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;

import com.banana.configuration.service.ConfigurationService;
import com.banana.request.model.DataRequest;
import com.banana.request.model.DataResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataRequestService {

	private static String host = "";
	private static String account = "";
	private static final String SLASH = "/";
	private static final String CHECK_IN = "/checkin";
	private static final String CHECK_OUT = "/checkout";

	static {
		host = ConfigurationService.createInstance().getHost();
	    account = ConfigurationService.createInstance().getAccount();
	}

    public static DataRequestService createInstance() {
        return new DataRequestService();
    }

	public DataResponse checkStatus() {
		String api_url = host + SLASH + account;
		String jsonDataRequest = convertToJson(buildDataRequest());
		Response response = getResponseWithGetMethod(api_url, jsonDataRequest);
		return response.readEntity(DataResponse.class);
	}

    public DataResponse checkIn() throws Exception {
    	String api_url = host + SLASH + account + CHECK_IN;
        String jsonDataRequest = convertToJson(buildDataRequest());
        Response response = getResponseWithPostMethod(api_url, jsonDataRequest);
        return response.readEntity(DataResponse.class);
    }

    public DataResponse checkOut() throws Exception {
    	String api_url = host + SLASH + account + CHECK_OUT;
        String jsonDataRequest = convertToJson(buildDataRequest());
        Response response = getResponseWithPostMethod(api_url, jsonDataRequest);
        return response.readEntity(DataResponse.class);
    }

    private Response getResponseWithPostMethod(String api, String jsonDataRequest) {
    	Client client = createRestClient();
    	WebTarget target = client.target(api);
    	return target.request(MediaType.APPLICATION_JSON_TYPE)
    			.post(Entity.entity(jsonDataRequest, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
    }

    private Response getResponseWithGetMethod(String api, String jsonDataRequest) {
    	Client client = createRestClient();
    	WebTarget target = client.target(api);
    	return target.request(MediaType.APPLICATION_JSON_TYPE).get();
    }

	private static DataRequest buildDataRequest() {
		DataRequest dataRequest = new DataRequest();
        dataRequest.setLocaltime(new Date());
		return dataRequest;
	}

    private static Client createRestClient() {
        ClientConfig clientConfig = new ClientConfig();

        clientConfig.register(
                new LoggingFeature(
                        Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                        Level.INFO,
                        LoggingFeature.Verbosity.PAYLOAD_ANY,
                        5000));

        return ClientBuilder.newClient(clientConfig);
    }

    private static String convertToJson(DataRequest dataRequest) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(dataRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}