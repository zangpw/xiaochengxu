package com.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.mapper.ProductModelMapper;
import com.spring.mapper.ProductPropertieMapper;
import com.spring.mapper.PropertyDetailMapper;
import com.spring.model.ProductModel;
import com.spring.model.ProductPropertie;
import com.spring.model.PropertyDetail;
import com.spring.service.ProductModelService;
import com.spring.service.ProductPropertieService;
import com.spring.service.PropertyDetailService;

@Service
public class ProductModelServiceImpl extends BaseServiceImpl<ProductModel> implements ProductModelService {
	@Autowired
	private ProductPropertieService productPropertieService;
	@Autowired
	private PropertyDetailService propertyDetailService;
	@Autowired
	private ProductModelMapper productModelMapper;

	@Override
	public ProductModel getProductModelById(Long id) {
		ProductModel productModel = super.queryById(id);
		ProductPropertie productPropertie = new ProductPropertie();
		productPropertie.setProductModelId(productModel.getId());
		productPropertie.setDataFlag((short) 1);
		List<ProductPropertie> productProperties = productPropertieService.queryListByWhere(productPropertie);
		productModel.setProductPropertieList(productProperties);
		PropertyDetail propertyDetail = new PropertyDetail();
		for (ProductPropertie productPropertie1 : productProperties) {
			propertyDetail.setProductPropertieId(productPropertie1.getId());
			propertyDetail.setDataFlag((short) 1);
			List<PropertyDetail> propertyDetails = propertyDetailService.queryListByWhere(propertyDetail);
			productPropertie1.setPropertyDetailList(propertyDetails);
		}
		return productModel;
	}

	@Override
	public Integer saveProductModel(ProductModel productModel) {
		Integer num = super.saveSelective(productModel);
		List<ProductPropertie> productProperties = productModel.getProductPropertieList();
		for (ProductPropertie productPropertie1 : productProperties) {
			productPropertie1.setProductModelId(productModel.getId());
			productPropertieService.saveSelective(productPropertie1);
			List<PropertyDetail> propertyDetails = productPropertie1.getPropertyDetailList();
			for (PropertyDetail propertyDetail : propertyDetails) {
				propertyDetail.setProductPropertieId(productPropertie1.getId());
				propertyDetailService.saveSelective(propertyDetail);
			}
		}
		return num;
	}

	@Override
	public Integer updateProductModel(ProductModel productModel) {
		Integer num = super.updateSelective(productModel);
		ProductPropertie productPropertie = new ProductPropertie();
		productPropertie.setProductModelId(productModel.getId());
		productPropertie.setDataFlag((short) 1);
		List<ProductPropertie> productProperties = productPropertieService.queryListByWhere(productPropertie);
		for (ProductPropertie productPropertie1 : productModel.getProductPropertieList()) {
			if (productPropertie1.getId() == null) {
				productPropertie1.setProductModelId(productModel.getId());
				productPropertieService.saveSelective(productPropertie1);
				for (PropertyDetail propertyDetail : productPropertie1.getPropertyDetailList()) {
					propertyDetail.setProductPropertieId(productPropertie1.getId());
					propertyDetailService.saveSelective(propertyDetail);
				}
			}
		}

		for (ProductPropertie productPropertie2 : productProperties) {
			boolean flag = true;
			productPropertie.setId(productPropertie2.getId());
			productPropertie.setDataFlag((short) -1);
			for (ProductPropertie productPropertie1 : productModel.getProductPropertieList()) {
				if (productPropertie1.getId() == productPropertie2.getId()) {
					productPropertieService.updateSelective(productPropertie1);

					PropertyDetail propertyDetail = new PropertyDetail();
					propertyDetail.setProductPropertieId(productPropertie2.getId());
					propertyDetail.setDataFlag((short) 1);

					for (PropertyDetail propertyDetail3 : productPropertie1.getPropertyDetailList()) {
						if (propertyDetail3.getId() == null) {
							propertyDetail3.setProductPropertieId(productPropertie1.getId());
							propertyDetailService.saveSelective(propertyDetail3);
						}
					}

					List<PropertyDetail> propertyDetails = propertyDetailService.queryListByWhere(propertyDetail);
					for (PropertyDetail propertyDetail2 : propertyDetails) {
						boolean flag1 = true;
						propertyDetail.setId(propertyDetail2.getId());
						propertyDetail.setDataFlag((short) -1);
						for (PropertyDetail propertyDetail1 : productPropertie1.getPropertyDetailList()) {
							if (propertyDetail1.getId() == propertyDetail2.getId()) {
								propertyDetailService.updateSelective(propertyDetail1);
								flag1 = false;
							}
						}
						if (flag1) {
							propertyDetailService.updateSelective(propertyDetail);
						}
					}
					flag = false;
				}
			}
			if (flag) {
				productPropertieService.updateSelective(productPropertie);
				PropertyDetail propertyDetail = new PropertyDetail();
				propertyDetail.setProductPropertieId(productPropertie.getId());
				propertyDetail.setDataFlag((short) 1);
				List<PropertyDetail> propertyDetails = propertyDetailService.queryListByWhere(propertyDetail);
				for (PropertyDetail propertyDetail1 : propertyDetails) {
					propertyDetail1.setDataFlag((short) -1);
					propertyDetailService.updateSelective(propertyDetail1);
				}
			}
		}
		return num;
	}

	@Override
	public PageInfo<ProductModel> getPageByProductName(Integer page, Integer rows, String productName) {
		// 设置分页条件
		PageHelper.startPage(page, rows);
		List<ProductModel> list = productModelMapper.getListByProductName(productName);
		return new PageInfo<ProductModel>(list);
	}

}
