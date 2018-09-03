package com.spring.service;

import com.github.pagehelper.PageInfo;
import com.spring.model.Information;

public interface InformationService extends BaseService<Information>{
	public PageInfo<Information> queryPageOrderByCreateTime(Integer page, Integer rows, Information information);
}
