package com.spring.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.spring.model.Member;

public interface MemberService extends BaseService<Member>{
	public Integer addOverMoney(Long memberId,BigDecimal price);
	
	public Integer subtractOverMoney(Long memberId,BigDecimal price);
	
	public Integer addPoint(Long memberId,Integer point);
}
