package com.spring.mapper;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.spring.model.Order;

public interface OrderMapper extends Mapper<Order>{
	public List<Order> getPageByTrueName(String name);
}
