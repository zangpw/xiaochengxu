package com.spring.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.spring.model.Member;
import com.spring.model.ProductBrand;
import com.spring.model.SmsLog;
import com.spring.service.MemberService;
import com.spring.service.SmsLogService;
import com.spring.sms.JavaSmsApi;
import com.spring.util.Digests;
import com.spring.util.MySessionContext;
import com.spring.util.NumberUtils;
import com.spring.util.StringUtils;

@Controller
@RequestMapping("member")
public class MemberController {
	@Resource
	private MemberService memberService;

	@Resource
	private SmsLogService smsLogService;

	@RequestMapping(value={"getCode"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseBody
	  public JSONObject getCode(@RequestBody String str, HttpServletRequest request)
	  {
	    JSONObject jsonObj = new JSONObject();
	    JSONObject json = JSONObject.parseObject(str);
	    String mobile = (String)json.get("mobile");
	    Integer state = Integer.valueOf((String)json.get("state"));
	    try
	    {
	      if ((StringUtils.isMobile(mobile)) && (11 == mobile.length()))
	      {
	        Member member = new Member();
	        member.setMobile(mobile);
	        member = (Member)this.memberService.queryOne(member);
	        if ((member != null) && (state.intValue() == 1))
	        {
	          jsonObj.put("result", Integer.valueOf(-1));
	          jsonObj.put("msg", "用户已存在");
	          jsonObj.put("data", "[]");
	          return jsonObj;
	        }
	        if ((member == null) && (state.intValue() == 2))
	        {
	          jsonObj.put("result", Integer.valueOf(-1));
	          jsonObj.put("msg", "用户不存在");
	          jsonObj.put("data", "[]");
	          return jsonObj;
	        }
	        Integer valid = Integer.valueOf(NumberUtils.getRandomInt(99999));
	        HttpSession session = request.getSession();
	        session.setAttribute("validCode", valid);
	        session.setAttribute("recordTime", Long.valueOf(System.currentTimeMillis()));
	        session.setAttribute("mobile", mobile);
	        
//	        String result=JavaSmsApi.sendSms("46c21c55f577d38f177c0e38f1e21e6c","【云网】您的验证码是"+valid,mobile);
	        String result = "{\"code\":0,\"msg\":\"OK\",\"result\":{\"count\":1,\"fee\":0.05,\"sid\":27303046158}}";
	        
	        SmsLog smsLog = new SmsLog();
	        smsLog.setType(Short.valueOf((short)1));
	        smsLog.setContent(valid.toString());
	        smsLog.setPhoneNumber(mobile);
	        smsLog.setReturnCode(result);
	        this.smsLogService.saveSelective(smsLog);
	        json = JSONObject.parseObject(result);
	        System.out.println(result);
	        Object code = json.get("code");
	        Object msg = json.get("msg");
	        jsonObj.put("result", code);
	        jsonObj.put("msg", msg);
	        if ("0".equals(code.toString()))
	        {
	          json.clear();
	          json.put("verifyCode", valid);
	          MySessionContext myc = MySessionContext.getInstance();
	          myc.addSession(session);
	          json.put("sid", session.getId());
	          jsonObj.put("data", json);
	        }
	        else
	        {
	          jsonObj.put("data", "[]");
	        }
	      }
	      else
	      {
	        jsonObj.put("result", Integer.valueOf(-1));
	        jsonObj.put("msg", "手机号格式不正确");
	        jsonObj.put("data", "[]");
	      }
	    }
	    catch (Exception e)
	    {
	      System.err.println("出错了" + e);
	      e.printStackTrace();
	      jsonObj.put("result", Integer.valueOf(-1));
	      jsonObj.put("msg", "服务器异常");
	      jsonObj.put("data", "[]");
	    }
	    return jsonObj;
	  }
	  
	  @RequestMapping(value={"register"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseBody
	  public JSONObject register(@RequestBody String str, HttpServletRequest request)
	  {
	    JSONObject jsonObj = new JSONObject();
	    try
	    {
	      JSONObject json = JSONObject.parseObject(str);
	      String mobile = (String)json.get("mobile");
	      String password = (String)json.get("password");
	      String code = (String)json.get("code");
	      String sid = (String)json.get("sid");
	      Member member = new Member();
	      member.setMobile(mobile);
	      member = (Member)this.memberService.queryOne(member);
	      if (member != null)
	      {
	        jsonObj.put("result", Integer.valueOf(-1));
	        jsonObj.put("msg", "用户名已存在");
	        jsonObj.put("data", "[]");
	      }
	      else
	      {
	        MySessionContext myc = MySessionContext.getInstance();
	        HttpSession session = myc.getSession(sid);
	        Integer validCode = (Integer)session.getAttribute("validCode");
	        Long recordTime = (Long)session.getAttribute("recordTime");
	        String orderMobile = (String)session.getAttribute("mobile");
	        if (recordTime.longValue() > System.currentTimeMillis())
	        {
	          jsonObj.put("result", -1);
	          jsonObj.put("msg", "验证码超时");
	          jsonObj.put("data", "[]");
	          return jsonObj;
	        }
	        if ((!mobile.equals(orderMobile)) || 
	          (!code.equals(validCode.toString())))
	        {
	          jsonObj.put("result", Integer.valueOf(-1));
	          jsonObj.put("msg", "验证码错误");
	          jsonObj.put("data", "[]");
	          return jsonObj;
	        }
	        member = new Member();
	        member.setName(mobile);
	        member.setMobile(mobile);
	        member.setPassword(Digests.entryptPassword(password));
	        Integer num = this.memberService.saveSelective(member);
	        if (num.intValue() > 0)
	        {
	          myc.delSession(session);
	          jsonObj.put("result", Integer.valueOf(0));
	          jsonObj.put("msg", "注册成功");
	          session.invalidate();
	        }
	      }
	    }
	    catch (Exception e)
	    {
	      System.err.println("出错了" + e);
	      e.printStackTrace();
	      jsonObj.put("result", Integer.valueOf(-1));
	      jsonObj.put("msg", "服务器异常");
	      jsonObj.put("data", "[]");
	    }
	    return jsonObj;
	  }
	  
	  @RequestMapping(value={"login"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseBody
	  public JSONObject login(@RequestBody String str, HttpServletRequest request)
	  {
	    JSONObject jsonObj = new JSONObject();
	    try
	    {
	      JSONObject json = JSONObject.parseObject(str);
	      String mobile = (String)json.get("mobile");
	      String password = (String)json.get("password");
	      String sid = (String)json.get("sid");
	      Member member = new Member();
	      member.setName(mobile);
	      member.setDataFlag(Short.valueOf((short)1));
	      member = (Member)this.memberService.queryOne(member);
	      if (member == null)
	      {
	        jsonObj.put("result", Integer.valueOf(-1));
	        jsonObj.put("msg", "用户不存在");
	        jsonObj.put("data", "[]");
	      }
	      if (Digests.validatePassword(password, member.getPassword()))
	      {
	        MySessionContext myc = MySessionContext.getInstance();
	        HttpSession session = request.getSession();
	        session.setMaxInactiveInterval(60);
	        myc.addSession(session);
	        session.setAttribute("member", member);
	        jsonObj.put("result", Integer.valueOf(0));
	        jsonObj.put("msg", "登录成功");
	        jsonObj.put("data", "{\"sid\":\"" + session.getId() + "\"}");
	      }
	      else
	      {
	        jsonObj.put("result", Integer.valueOf(-1));
	        jsonObj.put("msg", "密码错误");
	        jsonObj.put("data", "[]");
	      }
	    }
	    catch (Exception e)
	    {
	      System.err.println("出错了" + e);
	      e.printStackTrace();
	      jsonObj.put("result", Integer.valueOf(-1));
	      jsonObj.put("msg", "服务器异常");
	      jsonObj.put("data", "[]");
	    }
	    return jsonObj;
	  }
	  
	  @RequestMapping({"updatePassWord"})
	  @ResponseBody
	  public JSONObject updatePassWord(@RequestBody String str)
	  {
	    JSONObject jsonObj = new JSONObject();
	    try
	    {
	      JSONObject json = JSONObject.parseObject(str);
	      String mobile = (String)json.get("mobile");
	      String password = (String)json.get("password");
	      String code = (String)json.get("code");
	      String sid = (String)json.get("sid");
	      Member member = new Member();
	      member.setMobile(mobile);
	      member = (Member)this.memberService.queryOne(member);
	      if (member == null)
	      {
	        jsonObj.put("result", Integer.valueOf(-1));
	        jsonObj.put("msg", "用户名不存在");
	        jsonObj.put("data", "[]");
	      }
	      else
	      {
	        MySessionContext myc = MySessionContext.getInstance();
	        HttpSession session = myc.getSession(sid);
	        Integer validCode = (Integer)session.getAttribute("validCode");
	        Long recordTime = (Long)session.getAttribute("recordTime");
	        String orderMobile = (String)session.getAttribute("mobile");
	        if (recordTime.longValue() > System.currentTimeMillis())
	        {
	          jsonObj.put("result", Integer.valueOf(-1));
	          jsonObj.put("msg", "验证码超时");
	          jsonObj.put("data", "[]");
	          return jsonObj;
	        }
	        if ((!mobile.equals(orderMobile)) || 
	          (!code.equals(validCode.toString())))
	        {
	          jsonObj.put("result", Integer.valueOf(-1));
	          jsonObj.put("msg", "验证码错误");
	          jsonObj.put("data", "[]");
	          return jsonObj;
	        }
	        member.setPassword(Digests.entryptPassword(password));
	        Integer num = this.memberService.updateSelective(member);
	        if (num.intValue() > 0)
	        {
	          myc.delSession(session);
	          jsonObj.put("result", Integer.valueOf(0));
	          jsonObj.put("msg", "修改成功");
	          session.invalidate();
	        }
	      }
	    }
	    catch (Exception e)
	    {
	      System.err.println("出错了" + e);
	      e.printStackTrace();
	      jsonObj.put("result", Integer.valueOf(-1));
	      jsonObj.put("msg", "服务器异常");
	      jsonObj.put("data", "[]");
	    }
	    return jsonObj;
	  }
	  
	  
	  @RequestMapping(value="memberPage",method=RequestMethod.GET)
		@ResponseBody
		public JSONObject productBrandPage(String name,Integer page,Integer rows){
			JSONObject jsonObj = new JSONObject();
			try{
				Member member=new Member();
				member.setDataFlag((short) 1);
				member.setName(name);
				PageInfo<Member> memberPage = memberService.queryPageListByWhere(page, rows, member);
				jsonObj.put("result", 0);
				jsonObj.put("msg", "获取成功");
				jsonObj.put("data",memberPage);
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
				Member member = memberService.queryById(id);
				jsonObj.put("result", 0);
				jsonObj.put("msg", "获取成功");
				jsonObj.put("data",member);
			}catch (Exception e) {
				System.err.println("出错了" + e);
				jsonObj.put("result", -1);
				jsonObj.put("msg", "服务器异常");
				jsonObj.put("data", "[]");
			}
			return jsonObj;
		}
	  
	  @RequestMapping(value="add",method=RequestMethod.POST)
		@ResponseBody
		public JSONObject add(@RequestBody Member member){
			JSONObject jsonObj = new JSONObject();
			try{
				member.setDataFlag((short) 1);
				Integer num = memberService.saveSelective(member);
				if(num>0){
					jsonObj.put("result", 0);
					jsonObj.put("msg", "添加成功");
				}
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
		public JSONObject update(@RequestBody Member member){
			JSONObject jsonObj = new JSONObject();
			try{
				member.setDataFlag((short) 1);
				Integer num = memberService.updateSelective(member);
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
		public JSONObject delete(@RequestBody Member member){
			JSONObject jsonObj = new JSONObject();
			try{
				member.setDataFlag((short) -1);
				Integer num = memberService.updateSelective(member);
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
		
		@RequestMapping(value="getBySid")
		@ResponseBody
		public JSONObject getBySid(String sid) {
			JSONObject jsonObj = new JSONObject();
			try{
				MySessionContext myc = MySessionContext.getInstance();
				HttpSession session = myc.getSession(sid);
				Member member = (Member) session.getAttribute("member");
				member = memberService.queryById(member.getId());
				jsonObj.put("result", 0);
				jsonObj.put("msg", "获取成功");
				jsonObj.put("data",member);
			}catch (Exception e) {
				System.err.println("出错了" + e);
				jsonObj.put("result", -1);
				jsonObj.put("msg", "服务器异常");
				jsonObj.put("data", "[]");
			}
			return jsonObj;
		}

}
