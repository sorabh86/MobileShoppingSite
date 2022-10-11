package com.github.sorabh86.uigo.admin.users;

import java.util.List;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.stereotype.Component;

import com.github.sorabh86.uigo.entity.User;

@Component
public class MyHttpSessionBindingListener implements HttpSessionBindingListener {

    private User user; 
    private LoggedInUsers loggedInUsers;
    
    public MyHttpSessionBindingListener(User user, LoggedInUsers loggedInUsers) {
        this.user = user;
        this.loggedInUsers = loggedInUsers;
    }
    
    public MyHttpSessionBindingListener() {}

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        List<MyHttpSessionBindingListener> users = loggedInUsers.getUsers();
//        System.out.println(event.getValue());
        MyHttpSessionBindingListener user = 
        		(MyHttpSessionBindingListener) event.getValue();
        if (!users.contains(user)) {
            users.add(user);
        }
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        List<MyHttpSessionBindingListener> users = loggedInUsers.getUsers();
        MyHttpSessionBindingListener user = 
        		(MyHttpSessionBindingListener) event.getValue();
        if (users.contains(user)) {
            users.remove(user);
        }
    }

	public User getUser() {
		return user;
	}

	public void setUsername(User user) {
		this.user = user;
	}

	public LoggedInUsers getLoggedInUsers() {
		return loggedInUsers;
	}

	public void setLoggedInUsers(LoggedInUsers loggedInUsers) {
		this.loggedInUsers = loggedInUsers;
	}
}