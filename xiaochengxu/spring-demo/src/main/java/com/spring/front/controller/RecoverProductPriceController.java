package com.spring.front.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spring.model.Member;
import com.spring.model.RecoverProductPrice;
import com.spring.service.RecoverProductPriceService;
import com.spring.util.MySessionContext;

@Controller
@RequestMapping("recoverProductPrice")	
public class RecoverProductPriceController {
	@Resource
	private RecoverProductPriceService recoverProductPriceService;
	
	@RequestMapping(value="getByPropertyDetailId",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getByPropertyDetailId(@RequestBody String str) {
		JSONObject jsonObj = new JSONObject();
		try {
			JSONObject json = JSONObject.parseObject(str);
			Object[] objects = ((JSONArray)json.get("detailIds")).toArray();
			Long [] detailIds =new Long[objects.length];
			for(int i=0;i<objects.length;i++) {
				int detailId=(Integer) objects[i];
				detailIds[i]=(long) detailId;
			}
			String sid = (String) json.get("sid");
			Long memberId=null;
			if(StringUtils.isNotEmpty(sid)) {
				MySessionContext myc = MySessionContext.getInstance();
				HttpSession session = myc.getSession(sid);
				Member member = (Member)session.getAttribute("member");
				if(member!=null) {
					memberId=member.getId();
				}
			}
			RecoverProductPrice recoverProductPrice = recoverProductPriceService.getPriceByPropertyDetailId(detailIds,memberId);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data", recoverProductPrice);
		} catch (Exception e) {
			System.err.println("出错了" + e);
			e.printStackTrace();
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
	
}
