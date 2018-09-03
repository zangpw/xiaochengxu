package com.spring.service;

import com.github.pagehelper.PageInfo;
import com.spring.model.Order;

public interface OrderService extends BaseService<Order> {
	public Integer submit(Order Order, Long recoverProductPriceId);

	public PageInfo<Order> getPageByTrueName(Integer page, Integer rows, String tureName);
}
