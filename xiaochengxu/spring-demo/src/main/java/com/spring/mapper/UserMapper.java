package com.spring.mapper;

import com.github.abel533.mapper.Mapper;
import com.spring.model.User;

public interface UserMapper extends Mapper<User>{
	
	public User selectaaa(String name);
}
