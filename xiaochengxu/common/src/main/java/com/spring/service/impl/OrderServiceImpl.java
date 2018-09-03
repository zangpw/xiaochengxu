package com.spring.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.mapper.OrderMapper;
import com.spring.mapper.ProductBrandMapper;
import com.spring.mapper.ProductModelMapper;
import com.spring.mapper.ProductPropertieMapper;
import com.spring.mapper.ProductTypeMapper;
import com.spring.mapper.PropertyDetailMapper;
import com.spring.mapper.RecoverProductPriceDetailMapper;
import com.spring.mapper.RecoverProductPriceMapper;
import com.spring.mapper.RecoverProductPricePropertieMapper;
import com.spring.model.Information;
import com.spring.model.Member;
import com.spring.model.Order;
import com.spring.model.OrderProduct;
import com.spring.model.OrderProductPropertie;
import com.spring.model.OrderProductPropertieDetail;
import com.spring.model.OrderVo;
import com.spring.model.ProductBrand;
import com.spring.model.ProductModel;
import com.spring.model.ProductPropertie;
import com.spring.model.ProductType;
import com.spring.model.PropertyDetail;
import com.spring.model.RecoverProductPrice;
import com.spring.model.RecoverProductPriceDetail;
import com.spring.model.RecoverProductPricePropertie;
import com.spring.service.InformationService;
import com.spring.service.MemberService;
import com.spring.service.OrderProductPropertieDetailService;
import com.spring.service.OrderProductPropertieService;
import com.spring.service.OrderProductService;
import com.spring.service.OrderService;

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
	private ProductBrandMapper productBrandMapper;
	@Autowired
	private ProductTypeMapper productTypeMapper;
	@Autowired
	private ProductModelMapper productModelMapper;
	@Autowired
	private ProductPropertieMapper productPropertieMapper;
	@Autowired
	private PropertyDetailMapper propertyDetailMapper;
	@Autowired
	private OrderProductService orderProductService;
	@Autowired
	private OrderProductPropertieService orderProductPropertieService;
	@Autowired
	private OrderProductPropertieDetailService orderProductPropertieDetailService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private InformationService informationService;

	@Override
	public Integer submit(Order order, Long recoverProductPriceId) {
		RecoverProductPrice recoverProductPrice = (RecoverProductPrice) this.recoverProductPriceMapper
				.selectByPrimaryKey(recoverProductPriceId);
		ProductModel productModel = productModelMapper.selectByPrimaryKey(recoverProductPrice.getProductModelId());
		ProductType productType = productTypeMapper.selectByPrimaryKey(productModel.getProductTypeId());
		ProductBrand productBrand = productBrandMapper.selectByPrimaryKey(productType.getBrandId());
		order.setProductId(productBrand.getProductId());
		order.setRecoverPrice(recoverProductPrice.getRecoverProductPrice());
		order.setOrderPrice(recoverProductPrice.getRecoverProductPrice());
		Integer num = super.saveSelective(order);
		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setOrderId(order.getId());
		orderProduct.setProductModelId(recoverProductPrice.getProductModelId());
		orderProductService.saveSelective(orderProduct);

		RecoverProductPricePropertie recoverProductPricePropertie = new RecoverProductPricePropertie();
		recoverProductPricePropertie.setRecoverProductPriceId(recoverProductPriceId);
		List<RecoverProductPricePropertie> recoverProductPriceProperties = recoverProductPricePropertieMapper
				.select(recoverProductPricePropertie);
		for (RecoverProductPricePropertie recoverProductPricePropertie1 : recoverProductPriceProperties) {
			OrderProductPropertie orderProductPropertie = new OrderProductPropertie();
			orderProductPropertie.setProductPropertieId(recoverProductPricePropertie1.getProductPropertieId());
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

	@Override
	public PageInfo<OrderVo> getPageByState(Integer page, Integer rows, List<Short> states, Long memberId,
			Long productId, String trueName) {
		PageHelper.startPage(page, rows);
		List<OrderVo> orderVoList = orderMapper.getOrderVoByState(states, memberId, productId, trueName);
		PageInfo<OrderVo> orderVoPage = new PageInfo<OrderVo>(orderVoList);

		OrderProduct orderProduct1 = new OrderProduct();
		for (OrderVo orderVo : orderVoPage.getList()) {
			orderProduct1.setOrderId(orderVo.getOrderId());
			orderProduct1.setDataFlag((short) 1);
			OrderProduct orderProduct = orderProductService.queryOne(orderProduct1);
			ProductModel productModel = productModelMapper.selectByPrimaryKey(orderProduct.getProductModelId());
			ProductType productType = productTypeMapper.selectByPrimaryKey(productModel.getProductTypeId());
			ProductBrand brand = productBrandMapper.selectByPrimaryKey(productType.getBrandId());
			orderVo.setBrandName(brand.getName());
			orderVo.setTypeName(productType.getName());
			orderVo.setModelName(productModel.getName());
			orderVo.setModelImg(productModel.getImg());
			OrderProductPropertie orderProductPropertie1 = new OrderProductPropertie();
			orderProductPropertie1.setOrderProductId(orderProduct.getId());
			orderProductPropertie1.setDataFlag((short) 1);
			List<ProductPropertie> productPropertieList = new ArrayList<ProductPropertie>();
			List<OrderProductPropertie> orderProductPropertieList = orderProductPropertieService
					.queryListByWhere(orderProductPropertie1);
			for (OrderProductPropertie orderProductPropertie : orderProductPropertieList) {
				ProductPropertie productPropertie = productPropertieMapper
						.selectByPrimaryKey(orderProductPropertie.getProductPropertieId());
				List<PropertyDetail> propertyDetailList = new ArrayList<PropertyDetail>();
				OrderProductPropertieDetail orderProductPropertieDetail1 = new OrderProductPropertieDetail();
				orderProductPropertieDetail1.setRecoverOrderProductPropertieId(orderProductPropertie.getId());
				orderProductPropertieDetail1.setDataFlag((short) 1);
				List<OrderProductPropertieDetail> orderProductPropertieDetails = orderProductPropertieDetailService
						.queryListByWhere(orderProductPropertieDetail1);
				for (OrderProductPropertieDetail orderProductPropertieDetail : orderProductPropertieDetails) {
					propertyDetailList.add(
							propertyDetailMapper.selectByPrimaryKey(orderProductPropertieDetail.getPropertyDetailId()));
				}
				productPropertie.setPropertyDetailList(propertyDetailList);
				productPropertieList.add(productPropertie);
			}
			orderVo.setProperties(productPropertieList);
		}
		return orderVoPage;
	}

	@Override
	public Integer processOrder(Order order) {
		// TODO Auto-generated method stub
		Order order1 = orderMapper.selectByPrimaryKey(order.getId());
		String content = null;
		if (order.getState() == 2 && order1.getState() == 1) {
			order1.setState(order.getState());
			order1.setConsultPrice(order.getConsultPrice());
			order1.setRemarks(order.getRemarks());
			content = "你的订单编号:{编号} 已经提交议价回收，请留意议价信息，如果您同意议价，可以点击同意议价，等待放款";
			content=content.replace("{编号}", order1.getOrderSn().toString());
		} else if (order.getState() == 3 && (order1.getState() == 1 || order1.getState() == 2)) {
			order1.setState(order.getState());
			order1.setEndCheckTime(new Date().getTime());
			if (order1.getConsultPrice() != null) {
				order1.setOrderPrice(order1.getConsultPrice());
				memberService.addOverMoney(order1.getMemberId(), order1.getRecoverPrice());
				memberService.addPoint(order1.getMemberId(), order1.getRecoverPrice().intValue());
			}
			content = "你的订单编号:{编号} 已经确认回收，请留意打款";
			content=content.replace("{编号}", order1.getOrderSn().toString());
		} else if (order.getState() == 4 && order1.getState() == 3) {
			order1.setState(order.getState());
			order1.setFinishTime(new Date().getTime());
			memberService.subtractOverMoney(order1.getMemberId(), order1.getRecoverPrice());
		} else if (order.getState() == 5 && (order1.getState() == 1 || order1.getState() == 2)) {
			order1.setState(order.getState());
			order1.setReturnTime(new Date().getTime());
		} else if (order.getState() == 6
				&& (order1.getState() == 1 || order1.getState() == 2 || order1.getState() == 5)) {
			order1.setState(order.getState());
			order1.setTrackingCompany(order.getTrackingCompany());
			order1.setTrackingNumber(order.getTrackingNumber());
			content = "你的订单编号{编号} 已经被系统做退回处理，请留意快件信息，快递公司为{快递公司}，快递单号为{快递单号}";
			content=content.replace("{快递公司}", order1.getTrackingCompany());
			content=content.replace("{快递单号}", order1.getTrackingNumber());
		}
		int num = orderMapper.updateByPrimaryKeySelective(order1);
		if (content != null) {
			Information information = new Information();
			information.setName("订单消息");
			information.setOrderId(order1.getId());
			information.setMemberId(order1.getMemberId());
			information.setContent(content);
			information.setIsRead((short) 0);
			informationService.saveSelective(information);
		}
		return num;
	}

	@Override
	public Order getByOrderId(Long orderId) {
		Order order = orderMapper.selectByPrimaryKey(orderId);
		Member member = memberService.queryById(order.getMemberId());
		order.setMember(member);
		return order;
	}

}
