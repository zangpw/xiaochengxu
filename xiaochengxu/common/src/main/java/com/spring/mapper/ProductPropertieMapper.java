package com.spring.mapper;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.spring.model.ProductPropertie;

public interface ProductPropertieMapper extends Mapper<ProductPropertie>{
	List<ProductPropertie> selectByIds(Long[] ids);
}
