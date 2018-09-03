package com.spring.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.spring.model.Member;
import com.spring.model.Order;
import com.spring.model.OrderVo;

public interface OrderService extends BaseService<Order> {
	public Integer submit(Order Order, Long recoverProductPriceId);

	public PageInfo<Order> getPageByTrueName(Integer page, Integer rows, String tureName);
	
	public PageInfo<OrderVo> getPageByState(Integer page, Integer rows,List<Short> states,Long memberId,Long productId,String trueName);

	/**
	 * 订单处理
	 * @param order
	 * @return
	 */
	public Integer processOrder(Order order);
	
	/**
	 * 通过订单id获取订单和用户信息
	 * @param orderId
	 * @return
	 */
	public Order getByOrderId(Long orderId);
}
