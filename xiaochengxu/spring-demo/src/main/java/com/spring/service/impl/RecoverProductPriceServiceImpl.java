package com.spring.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mapper.ProductModelMapper;
import com.spring.mapper.ProductPropertieMapper;
import com.spring.mapper.PropertyDetailMapper;
import com.spring.model.ProductModel;
import com.spring.model.ProductPropertie;
import com.spring.model.PropertyDetail;
import com.spring.model.RecoverProductPrice;
import com.spring.model.RecoverProductPriceDetail;
import com.spring.model.RecoverProductPricePropertie;
import com.spring.service.RecoverProductPriceDetailService;
import com.spring.service.RecoverProductPricePropertieService;
import com.spring.service.RecoverProductPriceService;

@Service
public class RecoverProductPriceServiceImpl extends BaseServiceImpl<RecoverProductPrice>
		implements RecoverProductPriceService {
	@Autowired
	private PropertyDetailMapper PropertyDetailMapper;
	@Autowired
	private ProductModelMapper productModelMapper;
	@Autowired
	private ProductPropertieMapper productPropertieMapper;

	@Autowired
	private RecoverProductPricePropertieService recoverProductPricePropertieService;
	@Autowired
	private RecoverProductPriceDetailService recoverProductPriceDetailService;

	@Override
	public RecoverProductPrice getPriceByPropertyDetailId(Long[] detailIds, Long memberId) {
		List<PropertyDetail> propertyDetails = this.PropertyDetailMapper.selectByIds(detailIds);

		Long[] propertieIds = new Long[propertyDetails.size()];
		for (int i = 0; i < propertieIds.length; i++) {
			propertieIds[i] = propertyDetails.get(i).getProductPropertieId();
		}
		List<ProductPropertie> productProperties = productPropertieMapper.selectByIds(propertieIds);

		List<ProductPropertie> productPropertieList = new ArrayList<ProductPropertie>();
		for (ProductPropertie productPropertie : productProperties) {
			List<PropertyDetail> propertyDetaillList = new ArrayList<PropertyDetail>();
			for (PropertyDetail propertyDetail : propertyDetails) {
				if (productPropertie.getId() == propertyDetail.getProductPropertieId()) {
					propertyDetaillList.add(propertyDetail);
				}
			}
			productPropertie.setPropertyDetailList(propertyDetaillList);
			productPropertieList.add(productPropertie);
		}
		ProductModel productModel = (ProductModel) this.productModelMapper
				.selectByPrimaryKey(productProperties.get(0).getProductModelId());
		productModel.setProductPropertieList(productPropertieList);
		Double ratio = Double.valueOf(100.0D);
		for (PropertyDetail propertyDetail : propertyDetails) {
			ratio = Double.valueOf(ratio.doubleValue() - propertyDetail.getWeightRatio().doubleValue());
		}
		RecoverProductPrice recoverProductPrice = new RecoverProductPrice();
		recoverProductPrice.setProductModel(productModel);
		recoverProductPrice.setProductModelId(productModel.getId());
		recoverProductPrice.setRecoverProductPrice(productModel.getStandardPrice().divide(new BigDecimal(100))
				.multiply(new BigDecimal(ratio.doubleValue())).setScale(2, 5));
		recoverProductPrice.setMemberId(memberId);
		super.saveSelective(recoverProductPrice);

		for (ProductPropertie productPropertie : productProperties) {
			RecoverProductPricePropertie recoverProductPricePropertie = new RecoverProductPricePropertie();
			recoverProductPricePropertie.setRecoverProductPriceId(recoverProductPrice.getId());
			recoverProductPricePropertie.setProductPropertieId(productPropertie.getId());
			recoverProductPricePropertieService.saveSelective(recoverProductPricePropertie);
			for (PropertyDetail propertyDetail : propertyDetails) {
				if (propertyDetail.getProductPropertieId() == productPropertie.getId()) {
					RecoverProductPriceDetail recoverProductPriceDetail = new RecoverProductPriceDetail();
					recoverProductPriceDetail.setRecoverProductPricePropertieId(recoverProductPricePropertie.getId());
					recoverProductPriceDetail.setPropertyDetailId(propertyDetail.getId());
					recoverProductPriceDetailService.saveSelective(recoverProductPriceDetail);
				}
			}
		}

		return recoverProductPrice;
	}

}
