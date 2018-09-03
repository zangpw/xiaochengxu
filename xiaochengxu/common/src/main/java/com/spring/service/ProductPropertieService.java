package com.spring.service;

import com.github.pagehelper.PageInfo;
import com.spring.model.ProductPropertie;

public interface ProductPropertieService extends BaseService<ProductPropertie>{
	
	/**
     * 分页查询
     * 
     * @param page
     * @param rows
     * @param record
     * @return
     */
	public PageInfo<ProductPropertie> queryPropertieAndDetailPageListByWhere(Integer page, Integer rows, ProductPropertie record);

}
