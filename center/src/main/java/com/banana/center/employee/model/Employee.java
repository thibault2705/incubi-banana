package com.banana.center.employee.model;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.banana.center.enumeration.Status;

@XmlRootElement
public class Employee {
	private int id;
	private String account;
	private String localtime;
	@XmlTransient
	private SortedSet<Status> statuses;

	public Employee(String account, Status status) {
		this.account = account;
		this.statuses = new TreeSet<>();
		this.statuses.add(status);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getLocaltime() {
		return localtime;
	}

	public void setLocaltime(String localtime) {
		this.localtime = localtime;
	}

	public Set<Status> getStatuses() {
		return statuses;
	}

	public void setStatuses(TreeSet<Status> statuses) {
		this.statuses = statuses;
	}

    public Status getStatus() {
        return statuses.last();
    }
    
    @XmlElement(name = "status")
    public int getStatusAsInteger(){
        return getStatus().getValue();
    }
    
    public void active() {
        statuses.add(Status.ACTIVE);
    }

    public void checkin() {
    	statuses.add(Status.CHECKED_IN);
    }

    public void checkout() {
    	statuses.add(Status.CHECKED_OUT);
    }

    public void inactive() {
    	statuses.add(Status.INACTIVE);
    }
    
    

	@Override
	public String toString() {
		return account + " | " + statuses.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Employee employee = (Employee) o;

		if (id != employee.id) {
			return false;
		}
		if (!account.equals(employee.account)) {
			return false;
		}
		if (!localtime.equals(employee.localtime)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = account.hashCode();
		result = 31 * result + localtime.hashCode();
		result = 31 * result + id;
		return result;
	}
	
    public Set<Status> anyStatus() {
        return statuses;
    }
}
