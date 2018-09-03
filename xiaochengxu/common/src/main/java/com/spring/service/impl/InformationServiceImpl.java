package com.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.mapper.InformationMapper;
import com.spring.model.Information;
import com.spring.service.InformationService;
@Service
public class InformationServiceImpl extends BaseServiceImpl<Information> implements InformationService{
	@Autowired
	private InformationMapper informationMapper;
	@Override
	public PageInfo<Information> queryPageOrderByCreateTime(Integer page, Integer rows, Information information) {
		// 设置分页条件
		 PageHelper.startPage(page, rows);
		 Example example = new Example(Information.class);
		 example.setOrderByClause("create_time DESC");	
		 example.createCriteria().andEqualTo("memberId", information.getMemberId());
		 List<Information> informationList = informationMapper.selectByExample(example);
		 return new PageInfo<Information>(informationList);
	}

}
