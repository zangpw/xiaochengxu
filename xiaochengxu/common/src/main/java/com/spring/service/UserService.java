package com.spring.service;

import com.spring.model.User;

public interface UserService extends BaseService<User>{
	void finda(User user);
}
