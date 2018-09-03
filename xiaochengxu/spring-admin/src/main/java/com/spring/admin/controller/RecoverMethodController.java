package com.spring.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.spring.model.RecoverMethod;
import com.spring.model.RecoverProductPrice;
import com.spring.service.RecoverMethodService;
import com.spring.service.RecoverProductPriceService;

@Controller
@RequestMapping("recoverMethod")
public class RecoverMethodController {
	@Resource
	private RecoverProductPriceService recoverProductPriceService;
	
	@Resource
	private RecoverMethodService recoverMethodService;
	
	@RequestMapping(value="getPriceByRecoverProductPriceId",method=RequestMethod.GET)
	@ResponseBody
	public JSONObject getPriceByPropertyDetailId(Long recoverProductPriceId,String sid) {
		JSONObject jsonObj = new JSONObject();
		try {
			JSONObject json = new JSONObject();
			RecoverProductPrice recoverProductPrice = recoverProductPriceService.queryById(recoverProductPriceId);
			List<RecoverMethod> recoverMethods = recoverMethodService.queryAll();
			json.put("price", recoverProductPrice);
			json.put("recoverMethods", recoverMethods);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data", json);
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
