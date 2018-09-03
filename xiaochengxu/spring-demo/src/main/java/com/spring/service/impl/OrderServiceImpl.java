package com.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.mapper.OrderMapper;
import com.spring.mapper.RecoverProductPriceDetailMapper;
import com.spring.mapper.RecoverProductPriceMapper;
import com.spring.mapper.RecoverProductPricePropertieMapper;
import com.spring.model.Order;
import com.spring.model.OrderProduct;
import com.spring.model.OrderProductPropertie;
import com.spring.model.OrderProductPropertieDetail;
import com.spring.model.RecoverProductPrice;
import com.spring.model.RecoverProductPriceDetail;
import com.spring.model.RecoverProductPricePropertie;
import com.spring.service.OrderProductPropertieDetailService;
import com.spring.service.OrderProductPropertieService;
import com.spring.service.OrderProductService;
import com.spring.service.OrderService;
import com.spring.service.RecoverProductPricePropertieService;
import com.spring.service.RecoverProductPriceService;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {
	@Autowired
	private RecoverProductPriceMapper recoverProductPriceMapper;
	@Autowired
	private RecoverProductPricePropertieMapper recoverProductPricePropertieMapper;
	@Autowired
	private RecoverProductPriceDetailMapper recoverProductPriceDetailMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderProductService orderProductService;
	@Autowired
	private OrderProductPropertieService orderProductPropertieService;
	@Autowired
	private OrderProductPropertieDetailService orderProductPropertieDetailService;

	@Override
	public Integer submit(Order order, Long recoverProductPriceId) {
		RecoverProductPrice recoverProductPrice = (RecoverProductPrice) this.recoverProductPriceMapper
				.selectByPrimaryKey(recoverProductPriceId);
		order.setRecoverPrice(recoverProductPrice.getRecoverProductPrice());
		Integer num = super.saveSelective(order);
		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setOrderId(orderProduct.getId());
		orderProduct.setProductModelId(recoverProductPrice.getProductModelId());
		orderProductService.saveSelective(orderProduct);

		RecoverProductPricePropertie recoverProductPricePropertie = new RecoverProductPricePropertie();
		recoverProductPricePropertie.setRecoverProductPriceId(recoverProductPriceId);
		List<RecoverProductPricePropertie> recoverProductPriceProperties = recoverProductPricePropertieMapper
				.select(recoverProductPricePropertie);
		for (RecoverProductPricePropertie recoverProductPricePropertie1 : recoverProductPriceProperties) {
			OrderProductPropertie orderProductPropertie = new OrderProductPropertie();
			orderProductPropertie.setOrderProductId(recoverProductPricePropertie1.getProductPropertieId());
			orderProductPropertie.setOrderProductId(orderProduct.getId());
			orderProductPropertieService.saveSelective(orderProductPropertie);

			RecoverProductPriceDetail recoverProductPriceDetail = new RecoverProductPriceDetail();
			recoverProductPriceDetail
					.setRecoverProductPricePropertieId(recoverProductPricePropertie1.getRecoverProductPriceId());
			List<RecoverProductPriceDetail> recoverProductPriceDetails = recoverProductPriceDetailMapper
					.select(recoverProductPriceDetail);
			for (RecoverProductPriceDetail recoverProductPriceDetail1 : recoverProductPriceDetails) {
				OrderProductPropertieDetail orderProductPropertieDetail = new OrderProductPropertieDetail();
				orderProductPropertieDetail.setPropertyDetailId(recoverProductPriceDetail1.getPropertyDetailId());
				orderProductPropertieDetail.setRecoverOrderProductPropertieId(orderProductPropertie.getId());
				orderProductPropertieDetailService.saveSelective(orderProductPropertieDetail);
			}
		}

		return num;
	}

	@Override
	public PageInfo<Order> getPageByTrueName(Integer page, Integer rows, String tureName) {
		// 设置分页条件
		PageHelper.startPage(page, rows);
		List<Order> orderList = orderMapper.getPageByTrueName(tureName);
		return new PageInfo<Order>(orderList);
	}

}
