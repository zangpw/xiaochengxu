package com.spring.service;

import com.github.pagehelper.PageInfo;
import com.spring.model.ProductModel;

public interface ProductModelService extends BaseService<ProductModel>{
	public ProductModel getProductModelById(Long id);
	
	public Integer saveProductModel(ProductModel productModel);
	
	public Integer updateProductModel(ProductModel productModel);
	
	public PageInfo<ProductModel> getPageByProductName(Integer page, Integer rows,String productName);
}
