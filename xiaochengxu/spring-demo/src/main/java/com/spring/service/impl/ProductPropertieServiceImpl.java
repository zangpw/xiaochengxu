package com.spring.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.spring.mapper.PropertyDetailMapper;
import com.spring.model.ProductPropertie;
import com.spring.model.PropertyDetail;
import com.spring.service.ProductPropertieService;

@Service
public class ProductPropertieServiceImpl extends BaseServiceImpl<ProductPropertie> implements ProductPropertieService {
	@Resource
	private PropertyDetailMapper propertyDetailMapper;

	@Override
	public PageInfo<ProductPropertie> queryPropertieAndDetailPageListByWhere(Integer page, Integer rows,
			ProductPropertie record) {
		PropertyDetail propertyDetail = new PropertyDetail();
		PageInfo<ProductPropertie> ProductPropertiePage = super.queryPageListByWhere(page, rows, record);
		for (ProductPropertie productPropertie : ProductPropertiePage.getList()) {
			propertyDetail.setProductPropertieId(productPropertie.getId());
			List<PropertyDetail> propertyDetailList = this.propertyDetailMapper.select(propertyDetail);
			productPropertie.setPropertyDetailList(propertyDetailList);
		}
		return ProductPropertiePage;
	}

}
