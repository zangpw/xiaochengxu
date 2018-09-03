package com.spring.mapper;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.spring.model.ProductModel;

public interface ProductModelMapper extends Mapper<ProductModel>{
	public List<ProductModel> getListByProductName(String productName);
}
