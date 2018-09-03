package com.spring.front.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.spring.model.LatestNews;
import com.spring.model.Member;
import com.spring.model.PropertyDetail;
import com.spring.model.Order;
import com.spring.service.OrderService;
import com.spring.sms.JavaSmsApi;
import com.spring.util.MySessionContext;
import com.spring.util.NumberUtils;

@Controller
@RequestMapping("order")
public class OrderController {
	@Resource
	private OrderService orderService;

	@RequestMapping(value = "submit")
	@ResponseBody
	public JSONObject submit(@RequestBody String str) {
		JSONObject jsonObj = new JSONObject();
		try {
			JSONObject json = JSONObject.parseObject(str);
			String trueName = (String) json.get("trueName");
			String mobile = (String) json.get("mobile");
			String code = (String) json.get("code");
			String address = (String) json.get("address");
			Long recoverMethodId = Long.valueOf((String) json.get("recoverMethodId"));
			Long recoverProductPriceId = Long.valueOf((String) json.get("recoverProductPriceId"));
			String trackingNumber = (String) json.get("trackingNumber");
			String sid = (String) json.get("sid");

			Long memberId = null;
			if (StringUtils.isNotEmpty(sid)) {
				MySessionContext myc = MySessionContext.getInstance();
				try {
					if (myc.getSession(sid) == null || myc.getSession(sid).getAttribute("member") != null) {
						memberId = ((Member) (myc.getSession(sid).getAttribute("member"))).getId();
					} else {
						jsonObj.put("result", -1);
						jsonObj.put("msg", "用户未登录");
						jsonObj.put("data", "[]");
						return jsonObj;
					}
				}catch (Exception e) {
					jsonObj.put("result", -1);
					jsonObj.put("msg", "用户未登录");
					jsonObj.put("data", "[]");
					return jsonObj;
				}
			} else {
				jsonObj.put("result", -1);
				jsonObj.put("msg", "用户未登录");
				jsonObj.put("data", "[]");
				return jsonObj;
			}

			Order order = new Order();
			order.setMemberId(memberId);
			order.setTrueName(trueName);
			order.setMobile(mobile);
			order.setAddress(address);
			order.setState((short) 1);
			order.setTrackingNumber(trackingNumber);
			order.setRecyclingMethodId(recoverMethodId);
			Integer num = orderService.submit(order, recoverProductPriceId);

			jsonObj.put("result", 0);
			jsonObj.put("msg", "提交成功");
			jsonObj.put("data", num);
		} catch (Exception e) {
			System.err.println("出错了" + e);
			e.printStackTrace();
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}

	@RequestMapping("getCode")
	@ResponseBody
	public JSONObject getCode(String mobile, String sid) {
		JSONObject jsonObj = new JSONObject();
		try {
			if (StringUtils.isNotEmpty(sid)) {
				MySessionContext myc = MySessionContext.getInstance();
				HttpSession session;
				Member member;
				try {
					session = myc.getSession(sid);
					member=(Member) session.getAttribute("member");
				}catch (Exception e) {
					jsonObj.put("result", -1);
					jsonObj.put("msg", "用户未登录");
					jsonObj.put("data", "[]");
					return jsonObj;
				}
				if (session == null ||member != null) {
					if ((com.spring.util.StringUtils.isMobile(mobile)) && (11 == mobile.length())) {
						Integer valid = Integer.valueOf(NumberUtils.getRandomInt(99999));
//						String result = JavaSmsApi.sendSms("46c21c55f577d38f177c0e38f1e21e6c", "【云网】您的验证码是" + valid,
//								mobile);
						String result = "{\"code\":0,\"msg\":\"OK\",\"result\":{\"count\":1,\"fee\":0.05,\"sid\":27303046158}}";
						JSONObject json = JSONObject.parseObject(result);
						Object code = json.get("code");
						Object msg = json.get("msg");
						jsonObj.put("result", code);
						jsonObj.put("msg", msg);

						if ("0".equals(code.toString())) {
							json.clear();
							json.put("verifyCode", valid);
							session.setAttribute("validCode", valid);
							myc.addSession(session);
							json.put("sid", session.getId());
							jsonObj.put("data", json);
						} else {
							jsonObj.put("data", "[]");
						}

						json.put("sid", session.getId());
						jsonObj.put("data", json);
					}
				} else {
					jsonObj.put("result", -1);
					jsonObj.put("msg", "用户未登录");
					jsonObj.put("data", "[]");
					return jsonObj;
				}
			} else {
				jsonObj.put("result", -1);
				jsonObj.put("msg", "用户未登录");
				jsonObj.put("data", "[]");
				return jsonObj;
			}
		} catch (Exception e) {
			 System.err.println("出错了" + e);
		      e.printStackTrace();
		      jsonObj.put("result", Integer.valueOf(-1));
		      jsonObj.put("msg", "服务器异常");
		      jsonObj.put("data", "[]");
		}
		return jsonObj;
	}

	
	@RequestMapping("getPage")
	@ResponseBody
	public JSONObject getPage(Long productId,Integer page,Integer rows) {
		JSONObject jsonObj = new JSONObject();
		try{
			Order order=new Order();
			order.setDataFlag((short) 1);
			PageInfo<Order> orderPage = orderService.queryPageListByWhere(page, rows, order);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data",orderPage);
		}catch (Exception e) {
			System.err.println("出错了" + e);
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
	
	@RequestMapping(value="get",method=RequestMethod.GET)
	@ResponseBody
	public JSONObject get(Long id){
		JSONObject jsonObj = new JSONObject();
		try{
			Order orders = orderService.queryById(id);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data",orders);
		}catch (Exception e) {
			System.err.println("出错了" + e);
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject update(@RequestBody Order order){
		JSONObject jsonObj = new JSONObject();
		try{
			order.setDataFlag((short) 1);
			Integer num = orderService.updateSelective(order);
			if(num>0) {
				jsonObj.put("result", 0);
				jsonObj.put("msg", "修改成功");
				jsonObj.put("data",num);
			}else {
				jsonObj.put("result", -1);
				jsonObj.put("msg", "修改失败");
				jsonObj.put("data",num);
			}
		}catch (Exception e) {
			System.err.println("出错了" + e);
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject delete(@RequestBody Order order){
		JSONObject jsonObj = new JSONObject();
		try{
			order.setDataFlag((short) -1);
			order.setState((short) 0);
			Integer num = orderService.updateSelective(order);
			if(num>0) {
				jsonObj.put("result", 0);
				jsonObj.put("msg", "删除成功");
				jsonObj.put("data",num);
			}else {
				jsonObj.put("result", -1);
				jsonObj.put("msg", "删除失败");
				jsonObj.put("data",num);
			}
		}catch (Exception e) {
			System.err.println("出错了" + e);
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
	
	@RequestMapping("getPageByTrueName")
	@ResponseBody
	public JSONObject getPageByTrueName(Integer page,Integer rows,String trueName) {
		JSONObject jsonObj = new JSONObject();
		try{
			PageInfo<Order> orders = orderService.getPageByTrueName(page,rows,trueName);
				jsonObj.put("result", 0);
				jsonObj.put("msg", "查询成功");
				jsonObj.put("data",orders);
		}catch (Exception e) {
			System.err.println("出错了" + e);
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
}
