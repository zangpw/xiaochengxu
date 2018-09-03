package com.spring.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.spring.model.Member;
import com.spring.service.MemberService;

@Service
public class MemberServiceImpl extends BaseServiceImpl<Member> implements MemberService{

	@Override
	public Integer addOverMoney(Long memberId, BigDecimal price) {
		Member member1 = new Member();
		member1.setId(memberId);
		member1.setDataFlag((short) 1);
		Member member = super.queryOne(member1);
		Integer num = 0;
		if(member!=null){
			member.setDataFlag((short) 1);
			if(member.getOverMoney()!=null){
				member.setOverMoney(price.add(member.getOverMoney()));
			}else{
				member.setOverMoney(price);
			}
			num=super.updateSelective(member);
		}
		return num;
	}

	@Override
	public Integer subtractOverMoney(Long memberId, BigDecimal price) {
		Member member1 = new Member();
		member1.setId(memberId);
		member1.setDataFlag((short) 1);
		Member member = super.queryOne(member1);
		Integer num = 0;
		if(member!=null){
			member.setDataFlag((short) 1);
			if(member.getOverMoney()!=null){
				member.setOverMoney(member.getOverMoney().subtract(price));
			}else{
				member.setOverMoney(price);
			}
			num=super.updateSelective(member);
		}
		return num;
	}

	@Override
	public Integer addPoint(Long memberId, Integer point) {
		Member member1 = new Member();
		member1.setId(memberId);
		member1.setDataFlag((short) 1);
		Member member = super.queryOne(member1);
		Integer num = 0;
		if(member!=null){
			member.setDataFlag((short) 1);
			if(member.getOverMoney()!=null){
				member.setPoints(point+member.getPoints());
			}else{
				member.setPoints(point);
			}
			num=super.updateSelective(member);
		}
		return num;
	}

}
