package com.info.service;

import java.util.List;

import com.info.entity.User;

public interface IUserService {
	public User saveUser(User user);
	public List<User> getAllUsers();
	public User getUser(String userId);


}
