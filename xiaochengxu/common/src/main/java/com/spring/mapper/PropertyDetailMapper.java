package com.spring.mapper;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.spring.model.PropertyDetail;

public interface PropertyDetailMapper extends Mapper<PropertyDetail>{
	List<PropertyDetail> selectByIds(Long [] ids);
}
