package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.spring.model.Order;
import com.spring.model.OrderVo;

public interface OrderMapper extends Mapper<Order> {
	public List<Order> getPageByTrueName(String name);

	public List<OrderVo> getOrderVoByState(@Param("states") List<Short> states, @Param("memberId") Long memberId,
			@Param("productId") Long productId,@Param("trueName") String trueName);
}
