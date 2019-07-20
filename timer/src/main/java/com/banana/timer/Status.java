package com.banana.timer;

import java.util.HashMap;
import java.util.Map;

public enum Status {
	ACTIVE(0, "Active"),
	CHECKED_IN(1, "Check in"),
	CHECKED_OUT(2, "Check out"),
	INACTIVE (3, "In active")
	;

	private int id;
	private String name;

	private static final Map<Integer, Status> lookup = new HashMap<>();

	static {
	    for (Status status: Status.values()) {
	        lookup.put(status.getId(), status);
	    }
	}

	private Status(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public static Status getById(int id) {
		return lookup.get(id);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
