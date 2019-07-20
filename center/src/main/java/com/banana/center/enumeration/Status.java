package com.banana.center.enumeration;

import java.util.HashMap;
import java.util.Map;


public enum Status {
	ACTIVE(0),
	CHECKED_IN (1),
    CHECKED_OUT (2),
    INACTIVE (3)
    ;

	private static final Map<Integer, Status> lookup = new HashMap<>();
	
	static {
	    for (Status state: values()) {
	        lookup.put(state.getValue(), state);
	    }
	}
	
    private final int value;
    
    private Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Status fromValue(int value) {
        return lookup.get(value);
    }

}
