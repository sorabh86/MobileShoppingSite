package com.github.sorabh86.uigo.admin.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class LoggedInUsers {
	
	private List<MyHttpSessionBindingListener> users;
	
	public LoggedInUsers() {
        users = new ArrayList<>();
    }

	public List<MyHttpSessionBindingListener> getUsers() {
		return users;
	}

	public void setUsers(List<MyHttpSessionBindingListener> users) {
		this.users = users;
	}
}
